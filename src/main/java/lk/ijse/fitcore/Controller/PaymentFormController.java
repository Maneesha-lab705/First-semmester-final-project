package lk.ijse.fitcore.Controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.fitcore.Model.PaymentModel;
import lk.ijse.fitcore.dto.Payment;
import lk.ijse.fitcore.dto.tm.PaymentTm;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {

    @FXML
    private JFXComboBox<String> comPayment;

    @FXML
    private JFXComboBox<String> comMemberId;

    @FXML
    private JFXComboBox<String> comExType;


    @FXML
    private AnchorPane root;

    @FXML
    private Label lblTime;

    @FXML
    private Label lbtDate;


    @FXML
    private TableColumn<?, ?> tblAmount;

    @FXML
    private TableColumn<?, ?> tblMember;

    @FXML
    private TableColumn<?, ?> tblPayment;

    @FXML
    private TableView<PaymentTm> tblPaymentCart;

    @FXML
    private TableColumn<?, ?> tblTime;

    @FXML
    private TableColumn<?, ?> tbldate;

    @FXML
    private TableColumn<?, ?> tblexType;


    @FXML
    private TextField txtPayment;


    @FXML
    private TextField txtamount;

    @FXML
    private TableColumn<?, ?> tblTyp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadNextPaymentId();
        loadPaymentType();
        loadTable();
        loadMemberId();
        setValuesforTable();
        loadExTYpe();
        lblTime.setText(String.valueOf(LocalTime.now()));
        lbtDate.setText(String.valueOf(LocalDate.now()));
    }


    @FXML
    void btnPayOnAction(ActionEvent event) {

        String id =txtPayment.getText();
        String date =lbtDate.getText();
        String time =lblTime.getText();
        double amount = Double.parseDouble(txtamount.getText());
        String paymentType = String.valueOf(comPayment.getSelectionModel().getSelectedItem());
       String extype =comExType.getSelectionModel().getSelectedItem();
        String memberId =comMemberId.getSelectionModel().getSelectedItem();



        try {
            boolean isPayed1 =PaymentModel.serchId(memberId);
            if (isPayed1){
                new Alert(Alert.AlertType.ERROR,"").show();
            }else {
                Payment payment =new Payment(id,date,time,amount,paymentType,extype,memberId);

                try {
                    boolean isPayed = PaymentModel.payed(payment);
                    if (isPayed) {
                        new Alert(Alert.AlertType.CONFIRMATION, "YOU PAYED ...!!").show();
                        loadTable();
                        tblPaymentCart.refresh();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void loadTable() {
        ObservableList<PaymentTm>oblist =FXCollections.observableArrayList();
        try {
            List<Payment> getAll=PaymentModel.getALl();

            for (Payment payment :getAll){
                oblist.add(new PaymentTm(
                        payment.getPaymentId(),
                        payment.getDate(),
                        payment.getTime(),
                        payment.getAmount(),
                        payment.getPaymentype(),
                        payment.getExtype(),
                        payment.getMemberId()
                ));
                tblPaymentCart.setItems(oblist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setValuesforTable() {
        tblMember.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        tblPayment.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        tblAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tbldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        tblTyp.setCellValueFactory(new PropertyValueFactory<>("paymentype"));
        tblexType.setCellValueFactory(new PropertyValueFactory<>("extype"));

    }

    private void loadPaymentType() {
        List<String> type =new ArrayList<>();
        ObservableList<String> oblist = FXCollections.observableArrayList();
        type.add("Monthly");
        type.add("Six Month");
        type.add("One Year");

        for (String types :type){
            oblist.add(types);
        }
        comPayment.setItems(oblist);
    }

    void loadNextPaymentId(){
        try {
            String nextPaymenId = PaymentModel.getNextId();
            txtPayment.setText(nextPaymenId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void loadMemberId(){
        try {
            List<String> memberId =PaymentModel.getMemberId();
            ObservableList<String> oblist=FXCollections.observableArrayList();

            for (String id :memberId){
                oblist.add(id);

            }
            comMemberId.setItems(oblist);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void  loadExTYpe(){
        try {
            List<String> type =PaymentModel.getType();
            ObservableList<String> oblist =FXCollections.observableArrayList();

            for (String types :type){
                oblist.add(types);
            }
            comExType.setItems(oblist);

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
