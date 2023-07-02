package lk.ijse.fitcore.Controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import lk.ijse.fitcore.Model.EmployeeModel;
import lk.ijse.fitcore.dto.Employee;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    @FXML
    private JFXComboBox<String> ComJobId;

    @FXML
    private TextField textId;

    @FXML
    private TextField textName;

    @FXML
    private TextField textName1;


    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContac;
    @FXML
    private TextField txtNic;
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadJobRole();
    }


    @FXML
    void ComOnAction(ActionEvent event) {

    }

    @FXML
    void DeleteOnAction(ActionEvent event) {
        String id =textId.getText();
        try {
            boolean isDelete =EmployeeModel.delete(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"EMPLOYEE IS DELETED...!").show();
                textId.clear();
                textName.clear();
                textName1.clear();
                txtAddress.clear();
                txtNic.clear();
                txtContac.clear();
                ComJobId.getSelectionModel().clearSelection();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"EMPLOYEE IS NOTE DELETED...").show();
        }

    }

    @FXML
    void EmployeeNicOnAction(ActionEvent event) {
        String nic = txtNic.getText();
        Employee employee =null;

        try {
            employee =EmployeeModel.serchNic(nic);
            if (employee != null){
                textId.setText(employee.getId());
                textName.setText(employee.getFirstName());
                textName1.setText(employee.getLastName());
                txtAddress.setText(employee.getAddress());
                txtNic.setText(employee.getNic());
                txtContac.setText(String.valueOf(employee.getContact()));
                ComJobId.setValue(employee.getRole());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void EmployeeSerchIdOnAction(ActionEvent event) {

    if (textId.getText().matches("^[E0-9]{4}$")) {
        Employee employee = null;
        try {
            employee = EmployeeModel.serch(textId.getText());
            if (employee == null) {
                new Alert(Alert.AlertType.ERROR, "EMPLOYEE NOT HAVE").show();
            } else if (employee != null) {
                textName.setText(employee.getFirstName());
                textName1.setText(employee.getLastName());
                txtAddress.setText(employee.getAddress());
                txtNic.setText(employee.getNic());
                txtContac.setText(String.valueOf(employee.getContact()));
                ComJobId.getSelectionModel().select(employee.getRole());
            }

        } catch (SQLException e) {
           new  Alert(Alert.AlertType.ERROR,"HAVE NOT EMPLOYEE THIS ID.!!").show();
        }
    }else {
        new Alert(Alert.AlertType.ERROR,"WRONG ID!").show();
    }


    }

    @FXML
    void SaveOnAction(ActionEvent event) {
        if (conformId() ) {
            if (conformContact() ) {
                if (conformNic()) {
                    String firstName = textName.getText();
                    String lastName = textName1.getText();
                    String address = txtAddress.getText();
                    String nic = txtNic.getText();
                    int contact = Integer.parseInt(txtContac.getText());
                    String role = ComJobId.getSelectionModel().getSelectedItem();

                    Employee employee = new Employee(textId.getText(), firstName, lastName, address, nic, contact, role);

                    try {
                        boolean isSave = EmployeeModel.save(employee);
                        if (isSave) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Employee Added..").show();
                            clearAll();

                        } else if (!isSave) {
                            new Alert(Alert.AlertType.ERROR, "THIS EMPLOYEE IS ALLREDY ADDED..").show();

                        }

                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.WARNING, "SQL ERROR..").show();
                    }
                }
            }else {
                conformNic();
            }
        }else {
            conformNic();
            conformContact();
        }
    }



    private boolean conformId() {
        if (textId.getText().matches("^[E0-9]{4}$")) {
            return true;
        }else {
            textId.setBackground(Background.fill(Color.RED));

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
        if (txtContac.getText().matches("^[0-9]{10}$")) {
            return true;
        } else {
            txtContac.setBackground(Background.fill(Color.RED));

        }
        return false;
    }



    public void LoadJobRole() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> codes = new ArrayList<>();
        codes.add("Sales Representative");
        codes.add("cleaner");
        codes.add("Maintenance Technician");
        codes.add("Cashier");

        for (String code : codes) {
            obList.add(code);
        }
        ComJobId.setItems(obList);
    }

    @FXML
    void ClearOnAction(ActionEvent event) {
        textId.clear();
        textName.clear();
        textName1.clear();
        txtAddress.clear();
        txtNic.clear();
        txtContac.clear();
        ComJobId.getSelectionModel().clearSelection();


    }

    @FXML
    void UpdateOnAction(ActionEvent event) {

        if (conformId()) {
            if (conformContact() ) {
                if (conformNic()) {
                    String id = textId.getText();
                    String firstName = textName.getText();
                    String lastName = textName1.getText();
                    String address = txtAddress.getText();
                    String nic = txtNic.getText();
                    int contact = Integer.parseInt(txtContac.getText());
                    String role = ComJobId.getSelectionModel().getSelectedItem();

                    Employee employee = new Employee(id, firstName, lastName, address, nic, contact, role);

                    try {
                        boolean isUpdate = EmployeeModel.update(employee);
                        if (isUpdate) {
                            new Alert(Alert.AlertType.CONFIRMATION, "EMPLOYEE IS UPDATED....!").show();
                            clearAll();
                        }
                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, "EMPLOYEE IS NOT UPDATED....!").show();
                    }
                }
            }else {
                conformNic();
            }
        }else {
            conformNic();
            conformContact();
        }


    }
    void clearAll(){
        textId.clear();
        textName.clear();
        textName1.clear();
        txtAddress.clear();
        txtNic.clear();
        txtContac.clear();
        ComJobId.getSelectionModel().clearSelection();
    }


    public void AddresssClickOnAction(MouseEvent mouseEvent) {
        txtAddress.setBackground(Background.fill(Color.GOLD));
    }

    public void idClickOnAction(MouseEvent mouseEvent) {
        textId.setBackground(Background.fill(Color.GOLD));
    }

    public void fNameOnAction(MouseEvent mouseEvent) {
        textName.setBackground(Background.fill(Color.GOLD));
    }

    public void contactClickOnAction(MouseEvent mouseEvent) {
        txtContac.setBackground(Background.fill(Color.GOLD));
    }

    public void lNameOnAction(MouseEvent mouseEvent) {
        textName1.setBackground(Background.fill(Color.GOLD));
    }

    public void nicClickOnAction(MouseEvent mouseEvent) {
        txtNic.setBackground(Background.fill(Color.GOLD));
    }

    public void keyPressOnAction(KeyEvent keyEvent) {
        ComJobId.setBackground(Background.fill(Color.GOLD));
    }
}
