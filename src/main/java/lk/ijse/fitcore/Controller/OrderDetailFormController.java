
package lk.ijse.fitcore.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.fitcore.Model.OrderDetailModel;
import lk.ijse.fitcore.dto.OrderDetail;
import lk.ijse.fitcore.dto.tm.OrderDetailTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class OrderDetailFormController implements Initializable {

    @FXML
    private AnchorPane root;

        @FXML
        private TableColumn<?, ?> tblAmount;

        @FXML
        private TableColumn<?, ?> tblMembers;

        @FXML
        private TableColumn<?, ?> tblOrder;

        @FXML
        private TableColumn<?, ?> tblTime;

        @FXML
        private TableColumn<?, ?> tbldate;

        @FXML
        private TableColumn<?, ?> tblothers;

         @FXML

         private TableView<OrderDetailTm> tblOrderDetailCart;

        ObservableList<OrderDetailTm> oblist= FXCollections.observableArrayList();

        @FXML
        void btnBackOnAction(ActionEvent event) throws IOException {
            URL resource =getClass().getResource("/view/order_form.fxml");
            assert resource!=null;

            Parent load = FXMLLoader.load(resource);
            root.getChildren().clear();
            root.getChildren().add(load);

        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        loadTabledetail();
    }

    private void loadTabledetail() {
            tblOrder.setCellValueFactory(new PropertyValueFactory<>("orderID"));
            tblAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            tbldate.setCellValueFactory(new PropertyValueFactory<>("date"));
            tblTime.setCellValueFactory(new PropertyValueFactory<>("time"));
            tblMembers.setCellValueFactory(new PropertyValueFactory<>("memberId"));
            tblothers.setCellValueFactory(new PropertyValueFactory<>("orthers"));
    }

    private void loadTable() {
        try {
            List<OrderDetail> getall = OrderDetailModel.getAll();


            for (OrderDetail orderDetail :getall){
                oblist.add(new OrderDetailTm(orderDetail.getOrderID(),
                        orderDetail.getAmount(),
                        orderDetail.getDate(),
                        orderDetail.getTime(),
                        orderDetail.getMemberId(),
                        orderDetail.getOrthers()
                ));
                tblOrderDetailCart.setItems(oblist);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}


