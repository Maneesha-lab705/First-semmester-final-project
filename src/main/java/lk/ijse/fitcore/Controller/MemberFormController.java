package lk.ijse.fitcore.Controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import lk.ijse.fitcore.Model.ExerciseTypeModel;
import lk.ijse.fitcore.Model.MemberModel;
import lk.ijse.fitcore.Model.SaveMemberMOdel;
import lk.ijse.fitcore.dto.Member;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberFormController implements Initializable {

    @FXML
    private JFXComboBox<String> ComGenderId;

    @FXML
    private JFXComboBox<String> comPayment;

    @FXML
    private JFXComboBox<String> comType;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField textId;

    @FXML
    private TextField textName;

    @FXML
    private TextField textName1;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtNic;

    @FXML
    private Label lblExName;


    @FXML
    private Label lblcheckId;

    @FXML
    private Label lblCheckNic;


    @FXML
    private Label lblcheckContact;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadGender();
        LoadPayment();
        LoadExerciseType();
    }
    @FXML
    void ComExTypeOnAction(ActionEvent event) {
        String Typename =comType.getSelectionModel().getSelectedItem();

        try {
            String name=MemberModel.getExTypeName(Typename);
            lblExName.setText(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void ComGenderOnAction(ActionEvent event) {

    }

    @FXML
    void ComPaymentOnAction(ActionEvent event) {

    }

    @FXML
    void DeleteOnAction(ActionEvent event) {
        String id=textId.getText();

        try {
            boolean isDelete= MemberModel.delete(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION," MEMBER IS DELETED!!.").show();
                textId.clear();
                textName.clear();
                textName1.clear();
                ComGenderId.getSelectionModel().clearSelection();
                txtAddress.clear();
                txtNic.clear();
                txtContact.clear();
                comType.getSelectionModel().clearSelection();
                comPayment.getSelectionModel().clearSelection();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"MEMBER IS NOT DELETED...!!!");
        }
    }
    @FXML
    void NicOnAction(ActionEvent event) {
        String nic = txtNic.getText();
        Member member = null;
        try {
            member = MemberModel.sharchNic(nic);
            if (member != null){
                textId.setText(member.getId());
                textName.setText(member.getFirstName());
                textName1.setText(member.getLastName());
                txtAddress.setText(member.getAddress());
                txtContact.setText(String.valueOf(member.getContact()));
                ComGenderId.getSelectionModel().select(member.getGender());
                comPayment.getSelectionModel().select(member.getPaymentype());
            }
            String exType = MemberModel.getMemberWithTypeNic(member);

            if (exType != null){
                comType.getSelectionModel().select(exType);
            }else {
                new Alert(Alert.AlertType.ERROR, "MEMBER NOT FOUND!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "SOMETHING HAPPENED!!!").show();
        }
    }

    @FXML
    void SaveOnAction(ActionEvent event) {

        if (conformId()) {
             if (conformContact()) {
                if (conformNic()) {
                    String firstName = textName.getText();
                    String lastName = textName1.getText();
                    String gender = ComGenderId.getValue();
                    String address = txtAddress.getText();
                    String nic = txtNic.getText();
                    int contact = Integer.parseInt(txtContact.getText());
                    String exType = comType.getSelectionModel().getSelectedItem();
                    String paymentType = String.valueOf(comPayment.getValue());

                    Member member = new Member(textId.getText(), firstName, lastName, nic, gender, address, contact, paymentType);
                    try {
                        boolean isSave = SaveMemberMOdel.save(member, exType);
                        if (isSave) {
                            new Alert(Alert.AlertType.CONFIRMATION, "MEMBER IS SAVED...!").show();
                            clear();


                        } else if (isSave == false) {
                            new Alert(Alert.AlertType.WARNING, "THIS MEMBER ALREADY SAVED!!!").show();
                            lblcheckId.setText("");
                        }

                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, "SORRY").show();

                    }
                }

            }else {
                 conformNic();
             }
        }else {
            conformContact();
            conformNic();
        }
    }
    private boolean conformId() {
        if (textId.getText().matches("^[M0-9]{4}$")) {
            return true;
        }else {
            textId.setBackground(Background.fill(Color.RED));
            lblcheckId.setText("Incorrect Id!!");
        }
        return false;
    }

    private boolean conformNic() {
        if (txtNic.getText().matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$")){
            return true;
        }else {
            txtNic.setBackground(Background.fill(Color.RED));
            lblCheckNic.setText("Incorrect Nic");
        }
        return false;
    }

    private boolean conformContact() {
        if (txtContact.getText().matches("^[0-9]{10}$")) {
            return true;
        } else {
            txtContact.setBackground(Background.fill(Color.RED));
            lblcheckContact.setText("Incorrect Contact");
        }
        return false;
    }


    @FXML
    void SerchIdOnAction(ActionEvent event) {
        if (conformId()) {
            try {
                Member member = MemberModel.sharch(textId.getText());
                if (member != null) {
                    textId.setText(member.getId());
                    textName.setText(member.getFirstName());
                    textName1.setText(member.getLastName());
                    txtAddress.setText(member.getAddress());
                    txtContact.setText(String.valueOf(member.getContact()));
                    txtNic.setText(member.getNic());
                    ComGenderId.getSelectionModel().select(member.getGender());
                    comPayment.getSelectionModel().select(member.getPaymentype());

                    String exType = MemberModel.getMemberWithType(member);

                    if (exType != null) {
                        comType.getSelectionModel().select(exType);
                    }

                } else if (member == null) {
                    new Alert(Alert.AlertType.ERROR, "THIS MEMBER NOT FOUND!!!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @FXML
    void ClearOnAction(ActionEvent event) {
        textId.clear();
        textName.clear();
        textName1.clear();
        ComGenderId.getSelectionModel().clearSelection();
        txtAddress.clear();
        txtNic.clear();
        txtContact.clear();
        comType.getSelectionModel().clearSelection();
        comPayment.getSelectionModel().clearSelection();
    }

    void clear(){
        textId.clear();
        textName.clear();
        textName1.clear();
        ComGenderId.getSelectionModel().clearSelection();
        txtAddress.clear();
        txtNic.clear();
        txtContact.clear();
        comType.getSelectionModel().clearSelection();
        comPayment.getSelectionModel().clearSelection();
        lblCheckNic.setText("");
        lblcheckContact.setText("");
        lblCheckNic.setText("");

    }

    @FXML
    void updateOnAction(ActionEvent event) {

        if (conformId() || !conformId()) {
            if (conformNic()) {
                String id = textId.getText();
                String firstName = textName.getText();
                String lastName = textName1.getText();
                String nic = txtNic.getText();
                String gender = ComGenderId.getSelectionModel().getSelectedItem();
                String address = txtAddress.getText();
                int contact = Integer.parseInt(String.valueOf(Integer.parseInt(txtContact.getText())));
                String payType = comPayment.getSelectionModel().getSelectedItem();
                String type = comType.getSelectionModel().getSelectedItem();

                Member member = new Member(id, firstName, lastName, nic, gender, address, contact, payType);
                try {
                    boolean isUpdate = SaveMemberMOdel.update(member, type);
                    if (isUpdate) {
                        new Alert(Alert.AlertType.CONFIRMATION, "MEMBER IS UPDATED..!!").show();
                        clear();
                        lblCheckNic.setText("");

                    }
                } catch (SQLException throwables) {
                    new Alert(Alert.AlertType.ERROR, "oppss !!").show();
                }
            }
        }
    }







    void LoadGender(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> codes = new ArrayList<>();
        codes.add("Mail");
        codes.add("Femail");

        for (String code : codes) {
            obList.add(code);
        }
        ComGenderId.setItems(obList);
    }

    void LoadPayment(){
        ObservableList<String> observableList =FXCollections.observableArrayList();
        List<String> payment =new ArrayList<>();
        payment.add("Monthly membership fee");
        payment.add("Annual membership fee");
        payment.add("Personal training fee");

        for (String pay: payment){
            observableList.add(pay);
            comPayment.setItems(observableList);
        }
    }
    void  LoadExerciseType(){
        try {
            List<String > type = ExerciseTypeModel.getTypes();
            ObservableList<String> obList =FXCollections.observableArrayList();


            for (String types :type){
                obList.add(types);
            }
            comType.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void idClickOnAction(MouseEvent mouseEvent) {
        textId.setBackground(Background.fill(Color.GOLD));
        lblcheckId.setText("");
    }

    public void contatcClickOnAction(MouseEvent mouseEvent) {
        txtContact.setBackground(Background.fill(Color.GOLD));
        lblcheckContact.setText("");
    }

    public void nicClickOnAction(MouseEvent mouseEvent) {
        txtNic.setBackground(Background.fill(Color.GOLD));
        lblCheckNic.setText("");
    }

    public void firstNameClickOnAction(MouseEvent mouseEvent) {
        textName.setBackground(Background.fill(Color.GOLD));
    }

    public void addressClickOnAction(MouseEvent mouseEvent) {
        txtAddress.setBackground(Background.fill(Color.GOLD));
    }

    public void lastNameClickOnAction(MouseEvent mouseEvent) {
        textName1.setBackground(Background.fill(Color.GOLD));
    }
}
