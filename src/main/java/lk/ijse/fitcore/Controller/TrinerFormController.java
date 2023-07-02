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
import lk.ijse.fitcore.Model.TrainerModel;
import lk.ijse.fitcore.dto.Trainer;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TrinerFormController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadExTypeId();
    }


    @FXML
    private JFXComboBox<String> ComExTypeId;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNic;
    @FXML
    private Label extypeLabal;


    @FXML
    private TextField txtAddress;

    @FXML
    void DeleteOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            boolean isDelete = TrainerModel.delete(id);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "THIS TRAINER IS DELETED...!").show();
                txtId.clear();
                txtFirstName.clear();
                txtLastName.clear();
                txtNic.clear();
                txtAddress.clear();
                txtContact.clear();
                ComExTypeId.getSelectionModel().select(" ");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "THIS TRAINER IS NOT DELETED...!").show();
        }

    }


    private void LoadExTypeId() {
        try {
            List<String> types = ExerciseTypeModel.getTypes();
            ObservableList<String> obList = FXCollections.observableArrayList();

            for (String type : types) {
                obList.add(type);
            }

            ComExTypeId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "ERROR!!!");
        }


    }


    @FXML
    void SaveOnAction(ActionEvent event) {

        if (conformId() ) {
            if (conformNic() ) {
                if (conformContact()) {

                    String firstName = txtFirstName.getText();
                    String lastName = txtLastName.getText();
                    String nic = txtNic.getText();
                    String address = txtAddress.getText();
                    String extypeId = ComExTypeId.getSelectionModel().getSelectedItem();
                    int contact = Integer.parseInt(txtContact.getText());


                    Trainer trainer = new Trainer(txtId.getText(), firstName, lastName, nic, address, contact, extypeId);

                    try {
                        boolean isSave = TrainerModel.save(trainer);
                        if (isSave) {
                            new Alert(Alert.AlertType.CONFIRMATION, "TRAINER Is Save..!").show();
                           clear();

                        }
                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, "TRAINER IS NOT SAVED...").show();
                    }
                }else {
                    conformNic();
                }
            }else {
                conformContact();
            }
        }else {
            conformNic();
            conformContact();
        }
    }

    private boolean conformId() {
        if (txtId.getText().matches("^[T0-9]{4}$")) {
            return true;
        }else {
            txtId.setBackground(Background.fill(Color.RED));

        }
        return false;
    }

    private boolean conformNic() {
        if (txtNic.getText().matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$")){
            return true;
        }else {
            txtNic.setBackground(Background.fill(Color.RED));

        }
        return false;
    }

    private boolean conformContact() {
        if (txtContact.getText().matches("^[0-9]{10}$")) {
            return true;
        } else {
            txtContact.setBackground(Background.fill(Color.RED));

        }
        return false;
    }

    @FXML
    void SerchOnAction(ActionEvent event) {
        if (txtId.getText().matches("^[T0-9]{4}$")){
            Trainer trainer =null;

            try {
                trainer =TrainerModel.serch(txtId.getText());
                if (trainer != null){
                    txtFirstName.setText(trainer.getFirstName());
                    txtLastName.setText(trainer.getLastName());
                    txtNic.setText(trainer.getNic());
                    txtAddress.setText(trainer.getAddress());
                    txtContact.setText(String.valueOf(trainer.getContact()));
                    ComExTypeId.setValue(trainer.getExtypeId());
                }else  if (trainer ==null){
                    new Alert(Alert.AlertType.ERROR,"TRAINER NOT  FOUND..!").show();

                }

            } catch (SQLException e) {
                new  Alert(Alert.AlertType.ERROR,"TRAINER NOT  FOUND..!").show();
            }

        }else {
            new Alert(Alert.AlertType.ERROR,"WRONG ID").show();
        }

    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String nic = txtNic.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtContact.getText());
        String exType = ComExTypeId.getSelectionModel().getSelectedItem();

        Trainer trainer = new Trainer(id,firstName,lastName,nic,address,contact,exType);

        try {
            boolean isUpdate = TrainerModel.update(trainer);
            if(isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"TRAINER IS UPDATED..!").show();
            clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"TRAINER IS NOT UPDATED..!").show();
        }

    }


    public void ComTypeOnAction(ActionEvent actionEvent) {
      String id =  ComExTypeId.getSelectionModel().getSelectedItem();

        try {
            String description = TrainerModel.getDescription(id);
            extypeLabal.setText(description);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ClearOnAction(ActionEvent actionEvent) {
     txtId.clear();
     txtFirstName.clear();
     txtLastName.clear();
     txtNic.clear();
     txtAddress.clear();
     txtContact.clear();
     ComExTypeId.getSelectionModel().select( " ");
    }
    void clear(){
        txtId.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtNic.clear();
        txtAddress.clear();
        txtContact.clear();
        ComExTypeId.getSelectionModel().select( " ");
    }

    public void idclickOnAction(MouseEvent mouseEvent) {
        txtId.setBackground(Background.fill(Color.GOLD));
    }

    public void lNameClickOnAction(MouseEvent mouseEvent) {
        txtLastName.setBackground(Background.fill(Color.GOLD));
    }

    public void contactClickOnAction(MouseEvent mouseEvent) {
        txtContact.setBackground(Background.fill(Color.GOLD));
    }

    public void nicClickOnAction(MouseEvent mouseEvent) {
        txtNic.setBackground(Background.fill(Color.GOLD));
    }

    public void fNameClickOnAction(MouseEvent mouseEvent) {
        txtFirstName.setBackground(Background.fill(Color.GOLD));
    }

    public void addressClickOnAction(MouseEvent mouseEvent) {
        txtAddress.setBackground(Background.fill(Color.GOLD));
    }
}
