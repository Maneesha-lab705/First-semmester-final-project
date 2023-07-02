package lk.ijse.fitcore.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.fitcore.Model.ExerciseTypeModel;
import lk.ijse.fitcore.Model.ShedulModel;
import lk.ijse.fitcore.dto.Schedule;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CreateSheduleFormController implements Initializable {

    @FXML
    private JFXComboBox<String> exId;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextArea txtAriaId;

    @FXML
    private TextField txtShedulId;

    @FXML
    private TextField txtSnameID;

    @FXML
    private Label lblex;

    @FXML
    private TextField txtTimeId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadExId();
        serchAll();
        loadShedeulID();
    }

    private void loadShedeulID() {
        try {
            String shedulId = ShedulModel.getShedulId();
            txtShedulId.setText(shedulId);



        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDelete = ShedulModel.delete(txtShedulId.getText());
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"SCHEDULE IS DELETED!!! ").show();
                clearAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnGetAllOnAction(ActionEvent event) {

        String exTypeId =exId.getSelectionModel().getSelectedItem();

        try {
            Schedule shedul =ShedulModel.getAll(exTypeId);

            if (shedul != null){
                txtShedulId.setText(shedul.getScheduled());
                txtSnameID.setText(shedul.getName());
                txtTimeId.setText(String.valueOf(shedul.getTime()));
                txtAriaId.setText(shedul.getDescription());
                exId.setValue(shedul.getExId());
            }else if (shedul ==null){
                new Alert(Alert.AlertType.ERROR,"SHEDUL NOT FOUND!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String shadulId =txtShedulId.getText();
       String time = txtTimeId.getText();
        String  description =txtAriaId.getText();
        String name=txtSnameID.getText();
        String  exID =exId.getSelectionModel().getSelectedItem();

        Schedule shedul =new Schedule(shadulId,name,time,description,exID);

        try {
            boolean isSave =ShedulModel.save2(shedul);
            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION,"YOUR SHEDUL IS SAVED..!!").show();
                clearAll();
            }
        } catch (SQLException e) {
              e.printStackTrace();
        }
    }
    void serchAll(){


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String shadulId =txtShedulId.getText();
        String time= txtTimeId.getText();
        String  description =txtAriaId.getText();
        String name=txtSnameID.getText();
        String  exID =exId.getSelectionModel().getSelectedItem();

        Schedule shedul =new Schedule(shadulId,name,time,description,exID);

        try {
            boolean isUpdate=ShedulModel.updateAll(shedul);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"IS UPDATED!!!").show();
                clearAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exTypeOnAction(ActionEvent event) {

        String exid =exId.getSelectionModel().getSelectedItem();

        try {
            String name =ShedulModel.getExName(exid);
            lblex.setText(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void loadExId(){
        try {
            List<String> exType = ExerciseTypeModel.getTypes();
            ObservableList<String> obList = FXCollections.observableArrayList();

            for (String extypes :exType){
                obList.add(extypes);
            }
            exId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void clearAll(){
        exId.getSelectionModel().clearSelection();
        txtShedulId.clear();
        txtSnameID.clear();
        txtTimeId.clear();
        txtAriaId.clear();
    }


}
