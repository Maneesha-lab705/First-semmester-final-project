package lk.ijse.fitcore.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.fitcore.Model.ItemModel;
import lk.ijse.fitcore.Model.MemberModel;
import lk.ijse.fitcore.Model.OrderModel;
import lk.ijse.fitcore.Model.PlaceOrderModel;
import lk.ijse.fitcore.db.DBConnection;
import lk.ijse.fitcore.dto.Item;
import lk.ijse.fitcore.dto.OrderDto;
import lk.ijse.fitcore.dto.tm.OrderTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class OrderFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private JFXComboBox<String> cmbMemberId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colDiscription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> coltotal;

    @FXML
    private Label lbl;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblMemberName;

    @FXML
    private Label lblOrderDate;


    @FXML
    private JFXButton btnOrders;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtOtherBuyers;

    @FXML
    private TextField txtQty;

    @FXML
    private Label lblnetotal;


    @FXML
    private Label lblDescount;

    @FXML
    private TableView<OrderTm> tblCart;

    @FXML
    private JFXComboBox<String> comOthers;

    @FXML
    private Label lblDate;


    @FXML
    private Label lblTime;

    private ObservableList<OrderTm> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loradCustomerId();
        nextOrderId();
        loadItemCOde();
        setValueForTable();
        loadOtherBuyers();
        lblDate.setText(String.valueOf(LocalDate.now()));
        lblTime.setText(String.valueOf(LocalTime.now()));

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code =cmbItemCode.getSelectionModel().getSelectedItem();
        String description =lblDescription.getText();

        Double discount = 0.0;
        if (cmbMemberId.getSelectionModel().getSelectedItem() != null){
            discount = Double.valueOf(lblDescount.getText());
        }

        Double unitPrice = Double.valueOf(lblUnitPrice.getText());
        Integer qty = Integer.valueOf(txtQty.getText());

        Double subtot =qty*unitPrice;
        Double discountAmount =subtot * (discount /100.0);
        Double total =subtot -discountAmount;

        Button btnRemove = new Button("Remove");
        btnRemove.setCursor(Cursor.HAND);

        if (!obList.isEmpty()){
            for (int i = 0; i < tblCart.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    qty += (Integer) colQty.getCellData(i);
                    subtot = qty*unitPrice;
                    total =subtot -discountAmount;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);

                    tblCart.refresh();
                    netTotal();
                    return;

                }
            }
        }

        setRemoveBtnOnAction(btnRemove);

            OrderTm tm = new OrderTm(code,description,qty,unitPrice,discount,total,btnRemove);
            obList.add(tm);

            tblCart.setItems(obList);
            txtQty.clear();

            netTotal();
    }

    private void setRemoveBtnOnAction(Button btnRemove) {
        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                int index = tblCart.getSelectionModel().getSelectedIndex();
                obList.remove(index);

                tblCart.refresh();
            }
        });
    }

    void  setValueForTable(){

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDiscription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/member_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws JRException {
        String orderId =lblOrderId.getText();
       String others = comOthers.getSelectionModel().getSelectedItem();
        String memberId =cmbMemberId.getSelectionModel().getSelectedItem();
        String netTotal =lblnetotal.getText();
        String date =lblDate.getText();
        String time= lblTime.getText();
         List<OrderDto> orderDtoList =new ArrayList<>();

         if (memberId != null) {
             for (int i = 0; i < tblCart.getItems().size(); i++) {
                 OrderTm tm = obList.get(i);

                 OrderDto orderDto = new OrderDto(tm.getCode(), tm.getQty(), tm.getUnitPrice(), tm.getTotal());
                 orderDtoList.add(orderDto);
             }
             try {
                 boolean isSave = PlaceOrderModel.save(orderId, memberId, netTotal,date,time, orderDtoList);
                 if (!isSave) {
                     new Alert(Alert.AlertType.CONFIRMATION, "YOUR ORDER IS REDDY>>>").show();
                     addBill();
                     clear();
                 }
             } catch (SQLException | IOException e) {
                 new Alert(Alert.AlertType.ERROR, "YOUR ORDER NOT REDDY>>>").show();
             }
         }
         if (memberId == null && others != null){
             for (int i = 0; i < tblCart.getItems().size(); i++) {
                 OrderTm tm = obList.get(i);

                 OrderDto orderDto = new OrderDto(tm.getCode(), tm.getQty(), tm.getUnitPrice(), tm.getTotal());
                 orderDtoList.add(orderDto);
             }
             try {
                 boolean isSave = PlaceOrderModel.save1(orderId, date,time,others, netTotal, orderDtoList);
                 if (!isSave) {
                     new Alert(Alert.AlertType.CONFIRMATION, "YOUR ORDER IS REDDY>>>").show();
                     addBill();
                     clear();

                 }
             } catch (SQLException | JRException | IOException e) {
                 e.printStackTrace();
                 new Alert(Alert.AlertType.ERROR, "YOUR ORDER NOT REDDY>>>").show();
             }
         }

    }

    private void addBill()throws JRException, SQLException {

        String id = lblOrderId.getText();
        JasperDesign load = null;
        load = JRXmlLoader.load(new File("C:\\Final Project\\Project fitCore\\src\\main\\resources\\report\\Orders.jrxml"));
        JRDesignQuery newQuery = new JRDesignQuery();
        String sql="SELECT i.Description as name, i.Unit_Price as Unit_Price, od.Item_Qty , od.Total as Total FROM item i INNER JOIN order_detail od ON i.Item_Code = od.Item_Code WHERE od.Order_Id ='"+id+"'";
        newQuery.setText(sql);
        load.setQuery(newQuery);
        JasperReport js = JasperCompileManager.compileReport(load);
        HashMap<String,Object> hm=new HashMap<>();
        hm.put("printOrder","Name");
        JasperPrint jp = JasperFillManager.fillReport(js, null, DBConnection.getInstance().getConnection());
        JasperViewer viewer = new JasperViewer(jp, false);
        viewer.show();
    }

    void  clear() throws IOException {
        URL resource = getClass().getResource("/view/order_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }



     void netTotal(){

        double netTotal = 0.0;
         for (int i = 0; i < tblCart.getItems().size(); i++) {
             netTotal += (Double) coltotal.getCellData(i);
         }
    lblnetotal.setText(String.valueOf(netTotal));
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String itemId=cmbItemCode.getSelectionModel().getSelectedItem();

        try {
            Item item =ItemModel.getDiscription(itemId);
            if (item != null){
                lblDescription.setText(item.getDescription());
                lblUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                lblQtyOnHand.setText(String.valueOf(item.getQntOnHand()));
                lblDescount.setText(String.valueOf(item.getDiscount()));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private  void loadItemCOde(){
        try {
            List<String> itemCode = ItemModel.getItemId();
            ObservableList<String> oblist =FXCollections.observableArrayList();

            for (String id : itemCode){
                oblist.add(id);
            }
            cmbItemCode.setItems(oblist);
        } catch (SQLException e) {
           e.printStackTrace();
        }


    }
    void loadOtherBuyers(){
        ObservableList<String> observableList= FXCollections.observableArrayList();
        List<String> orthers =new ArrayList<>();
        orthers.add("CUSTOMER");

        for (String other :orthers){
            observableList.add(other);

        }
        comOthers.setItems(observableList);



    }

    @FXML
    void cmbMemberOnAction(ActionEvent event) throws SQLException {
        String custId=cmbMemberId.getSelectionModel().getSelectedItem();

        String name =MemberModel.getMemberName(custId);
        lblMemberName.setText(name);

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }
    private  void loradCustomerId(){
        try {
            List<String> ids = MemberModel.getId();
            ObservableList<String > oblist = FXCollections.observableArrayList();

            for (String id :ids){
                oblist.add(id);
            }
            cmbMemberId.setItems(oblist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private  void  nextOrderId(){
        try {
            String nextId = OrderModel.nextOrderID();
            lblOrderId.setText(nextId);
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }


    public void comOthersOnAction(ActionEvent actionEvent) {

    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource =getClass().getResource("/view/OrderDetail_form.fxml");
        assert resource!=null;

        Parent load =FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);



    }
}
