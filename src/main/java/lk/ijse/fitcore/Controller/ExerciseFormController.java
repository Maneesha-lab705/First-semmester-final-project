package lk.ijse.fitcore.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import lk.ijse.fitcore.Model.ExerciseTypeModel;
import lk.ijse.fitcore.dto.Exercize;

import java.sql.SQLException;

public class ExerciseFormController {

    @FXML
    private TextField txtExId;

    @FXML
    private TextField txtExNameId;

    @FXML
    private TextField txtHourlyId;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id =txtExId.getText();

        try {
            boolean isDelete=ExerciseTypeModel.delete(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"IS DELETED.!").show();
                txtHourlyId.clear();
                txtExId.clear();
                txtExNameId.clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"NOT DELETED.!").show();
        }


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (txtExId.getText().matches("^[E0-9]{4}$")) {
            String id = txtExId.getText();
            String name = txtExNameId.getText();
            double fee = Double.parseDouble(txtHourlyId.getText());

            Exercize exercize = new Exercize(id, name, fee);

            try {
                boolean isSave = ExerciseTypeModel.save(exercize);
                if (isSave) {
                    new Alert(Alert.AlertType.CONFIRMATION, "SAVED..!!").show();
                    txtExId.setBackground(Background.fill(Color.GREENYELLOW));
                    txtExNameId.setBackground(Background.fill(Color.GREENYELLOW));
                    txtHourlyId.setBackground(Background.fill(Color.GREENYELLOW));
                    txtExId.clear();
                    txtHourlyId.clear();
                    txtExNameId.clear();
                } else if (isSave == false) {
                    new Alert(Alert.AlertType.ERROR, "THIS EXERCISE TYPE ARLYDAY ADDED.").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "NOT SAVE").show();
            }
        }else {
            txtExId.setBackground(Background.fill(Color.RED));
        }

    }

    @FXML
    void btnSerchOnAction(ActionEvent event) {

        if (txtExId.getText().matches("^[E0-9]{4}$")) {

            try {
                Exercize exercize = ExerciseTypeModel.serch(txtExId.getText());
                if (exercize != null) {
                    txtExId.setText(exercize.getExtypeId());
                    txtExNameId.setText(exercize.getExName());
                    txtHourlyId.setText(String.valueOf(exercize.getFee()));

                } else if (exercize == null) {
                    new Alert(Alert.AlertType.ERROR, "NOT FOUNDED.!!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "NOT UPDATE.!!").show();
            }
        }else {
            txtExId.setBackground(Background.fill(Color.RED));
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id =txtExId.getText();
        String name =txtExNameId.getText();
        double fee = Double.parseDouble(txtHourlyId.getText());

        Exercize exercize =new Exercize(id,name,fee);

        try {
            boolean isUpdate=ExerciseTypeModel.update(exercize);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"IS UPDATE").show();
                txtExNameId.clear();
                txtHourlyId.clear();
                txtExId.clear();
            }
        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR,"NOT UPDATED.!").show();
        }

    }

    public void clickOnAction(MouseEvent mouseEvent) {
        txtExId.setBackground(Background.fill(Color.GREEN));
    }
}
