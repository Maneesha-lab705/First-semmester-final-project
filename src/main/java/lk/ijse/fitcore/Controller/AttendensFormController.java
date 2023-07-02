package lk.ijse.fitcore.Controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.fitcore.Model.EmployeeAttendanceModel;
import lk.ijse.fitcore.Model.EmployeeModel;
import lk.ijse.fitcore.dto.EmployeeAttendance;
import lk.ijse.fitcore.dto.tm.EmployeeAttendanceTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AttendensFormController implements Initializable {


    @FXML
    private JFXComboBox<String> comEmployee;

    @FXML
    private Label lblTime;

    @FXML
    private Label lbldate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<?, ?> tblAttendens;

    @FXML
    private TableColumn<?, ?> tblDate;

    @FXML
    private TableColumn<?, ?> tblId;

    @FXML
    private TableView<lk.ijse.fitcore.dto.tm.EmployeeAttendanceTm> tblPorA;

    @FXML
    private TableColumn<?, ?> tblTime;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private JFXComboBox<String> comPORA;

    @FXML
    private TextField txtPRorAb;
    ObservableList<EmployeeAttendanceTm> oblist =FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbldate.setText(String.valueOf(LocalDate.now()));
        lblTime.setText(String.valueOf(LocalTime.now()));
        loadEmployeeId();
        setvaluefortable();loadprosentORabsent();

    }

    private void loadprosentORabsent() {
        List<String> type =new ArrayList<>();
        ObservableList<String> oblist =FXCollections.observableArrayList();

        type.add("PRESENT");
        type.add("ABSENT");

        for (String types :type){
            oblist.add(types);
        }
        comPORA.setItems(oblist);
    }

    void  loadEmployeeId(){
        try {
            List<String> id = EmployeeModel.getId();
            ObservableList<String> oblist= FXCollections.observableArrayList();

            for (String ids :id){
                oblist.add(ids);
            }
            comEmployee.setItems(oblist);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String employeeId =comEmployee.getSelectionModel().getSelectedItem();
        String date =lbldate.getText();
        String time =lblTime.getText();
        String isAttend =comPORA.getSelectionModel().getSelectedItem();

        EmployeeAttendance employeeAttendance=new EmployeeAttendance(employeeId,date,time,isAttend);

        try {
            boolean isSave = EmployeeAttendanceModel.save(employeeAttendance);
            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION,"IS ATTEND...!!").show();
                txtEmployeeId.clear();
                comEmployee.getSelectionModel().clearSelection();
                comPORA.getSelectionModel().clearSelection();
            }
        } catch (SQLException e) {

            new Alert(Alert.AlertType.CONFIRMATION,"IS NOT ATTEND...!!").show();
        }


    }

    @FXML
    void btnScanOnAction(ActionEvent event) {

    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource =getClass().getResource("/view/showAttends_form.fxml");
        assert resource!=null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void comEmployeeOnAction(ActionEvent actionEvent) {
    }


    @FXML
    void keyUpOnAction(KeyEvent event) {


            try {
                List<EmployeeAttendance> employee = EmployeeAttendanceModel.getall(txtEmployeeId.getText());
                EmployeeAttendanceTm attendanceTm = null;
                oblist.clear();

                for (EmployeeAttendance attendance : employee) {

                    oblist.add(new EmployeeAttendanceTm(attendance.getEmployeeId(), attendance.getDate(), attendance.getTime(), attendance.getIsAttendance()));

                }

                tblPorA.setItems(oblist);


            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    void  setvaluefortable(){
        tblId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        tblAttendens.setCellValueFactory(new PropertyValueFactory<>("isAttendance"));
    }

}
