package lk.ijse.fitcore.Controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import lk.ijse.fitcore.Model.ExerciseTypeModel;
import lk.ijse.fitcore.Model.ItemModel;
import lk.ijse.fitcore.dto.Item;
import lk.ijse.fitcore.dto.tm.ItemTm;
import lk.ijse.fitcore.dto.tm.OrderTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private JFXComboBox<String> comId;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtDiscountId;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private Label lblItemCode;

    @FXML
    private TableColumn<?, ?> colDiscount;


    @FXML
    private TableColumn<?, ?> colUnitPrice;


    @FXML
    private TableColumn<?, ?> colItemType;

    @FXML
    private TableView<ItemTm> tblItemCart;


    private ObservableList<ItemTm> obList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadItem();
        loadTable();
        setValuesforTable();

    }

    private void loadItem() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> codes = new ArrayList<>();
        codes.add("Equipment");
        codes.add("Supplement");

        for (String code : codes) {
            obList.add(code);
        }
        comId.setItems(obList);

    }
    private  void loadTable(){

        try {
            List<Item> isget=ItemModel.getAll();

            for (Item item :isget){
                obList.add(new ItemTm(
                        item.getItemCode(),
                        item.getDescription(),
                        item.getQntOnHand(),
                        item.getUnitPrice(),
                        item.getDiscount(),
                        item.getItemType()
                ));
            }
            tblItemCart.setItems(obList);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    void setValuesforTable() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colItemType.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

    }

    @FXML
    void ComOnAction(ActionEvent event) {
//        String typeOfItem=comId.getSelectionModel().getSelectedItem();
//
//        try {
//            Item item =ItemModel.serch(typeOfItem);
//            if (item!= null){
//                txtCode.setText(item.getItemCode());
//                txtDescription.setText(item.getDescription());
//                txtQtyOnHand.setText(String.valueOf(item.getQntOnHand()));
//                txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
//                txtDiscountId.setText(String.valueOf(item.getDiscount()));
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    void DeleteOnAction(ActionEvent event) {
        String code =txtCode.getText();

        try {
            boolean isDelete=ItemModel.delete(code);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"ITEM IS DROP..!!").show();
                tblItemCart.refresh();
                txtCode.clear();
                txtDescription.clear();
                txtQtyOnHand.clear();
                txtUnitPrice.clear();
                txtDiscountId.clear();
                comId.getSelectionModel().clearSelection();
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }

    }

    @FXML
    void ClearAllOnAction(ActionEvent event) {
        txtCode.clear();
        txtDescription.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
        txtDiscountId.clear();
        comId.getSelectionModel().clearSelection();

    }

    @FXML
    void SaveOnAction(ActionEvent event) {

        if (txtCode.getText().matches("^[I0-9]{4}$")) {
            String description = txtDescription.getText();
            int qntOnHand = Integer.parseInt(txtQtyOnHand.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            double discount = Double.parseDouble(txtDiscountId.getText());
            String itemType = String.valueOf(comId.getSelectionModel().getSelectedItem());

            Item item = new Item(txtCode.getText(), description, qntOnHand, unitPrice, discount, itemType);

            try {
                boolean isSave = ItemModel.save(item);
                if (isSave) {
                    new Alert(Alert.AlertType.CONFIRMATION, "ITEM SAVED....!").show();

                    tblItemCart.refresh();
                    txtCode.clear();
                    txtDescription.clear();
                    txtQtyOnHand.clear();
                    txtUnitPrice.clear();
                    txtDiscountId.clear();
                    comId.getSelectionModel().clearSelection();


                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.CONFIRMATION, "ITEM IS NOT SAVED....!").show();
            }
        }else {
            txtCode.setBackground(Background.fill(Color.RED));
        }

    }


    @FXML
    void UpdateOnAction(ActionEvent event) {
        String itemCode = txtCode.getText();
        String description = txtDescription.getText();
        int qntOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double discount = Double.parseDouble(txtDiscountId.getText());
        String itemType = String.valueOf(comId.getSelectionModel().getSelectedItem());

        Item item =new Item(itemCode,description,qntOnHand,unitPrice,discount,itemType);

        try {
            boolean isUpdate =ItemModel.update(item);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"ITEM IS UP DATED..!").show();
                txtCode.clear();
                txtDescription.clear();
                txtQtyOnHand.clear();
                txtUnitPrice.clear();
                txtDiscountId.clear();
                comId.getSelectionModel().clearSelection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void codeSearchOnAction(ActionEvent event) {
        if (txtCode.getText().matches("^[I0-9]{4}$")) {
            Item item = null;
            try {
                item = ItemModel.serchByCode(txtCode.getText());
                if (item != null) {
                    txtCode.setText(item.getItemCode());
                    txtDescription.setText(item.getDescription());
                    txtQtyOnHand.setText(String.valueOf(item.getQntOnHand()));
                    txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                    txtDiscountId.setText(String.valueOf(item.getDiscount()));
                    comId.setValue(item.getItemType());

                } else if (item == null) {
                    new Alert(Alert.AlertType.WARNING, "ITEM NOT FOUND...!!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else {
            new Alert(Alert.AlertType.WARNING, "WRONG ID!!").show();
        }
    }
}
