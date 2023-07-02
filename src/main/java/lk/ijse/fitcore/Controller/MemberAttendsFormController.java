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
import lk.ijse.fitcore.Model.MemberAttendModel;
import lk.ijse.fitcore.dto.MemberAttendane;
import lk.ijse.fitcore.dto.tm.MemberAttendanceTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MemberAttendsFormController implements Initializable{

    @FXML
    private JFXComboBox<String> comMember;

    @FXML
    private Label lblTime;

    @FXML
    private Label lbldate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<?, ?> tblAttendens;

    @FXML
    private TableColumn<?, ?> tblDateId;

    @FXML
    private TableColumn<?, ?> tblId;

    @FXML
    private TableColumn<?, ?> tblNameId;

    @FXML
    private TableView<MemberAttendanceTm> tblPorA;

    @FXML
    private TableColumn<?, ?> tblTimeId;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtMember;

    @FXML
    private TextField txtPRorAb;

    @FXML
    private JFXComboBox<String> comPORA;

    ObservableList<MemberAttendanceTm> oblist=FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMemberd();
        loadTbale();
        loadProsentORAbsent();

        lbldate.setText(String.valueOf(LocalDate.now()));
        lblTime.setText(String.valueOf(LocalTime.now()));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        URL resource =getClass().getResource("/view/showAttends_form.fxml");
        assert resource!=null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = String.valueOf(comMember.getSelectionModel().getSelectedItem());
        String date=lbldate.getText();
        String time =lblTime.getText();
        String isAttend =comPORA.getSelectionModel().getSelectedItem();

        MemberAttendane memberAttendane =new MemberAttendane(id,date,time,isAttend);

        try {
            boolean isSave= MemberAttendModel.save(memberAttendane);
            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION,"MEMBER IS ATTEND..!!").show();
                comMember.getSelectionModel().clearSelection();
                comPORA.getSelectionModel().clearSelection();
                txtMember.clear();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    void  loadMemberd(){
        try {
            List<String> id=MemberAttendModel.getIds();
            ObservableList<String> oblist = FXCollections.observableArrayList();

            for (String ids :id){
                oblist.add(ids);
            }
            comMember.setItems(oblist);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void  loadProsentORAbsent(){
        List<String> types =new ArrayList<>();
        ObservableList<String> oblist=FXCollections.observableArrayList();

        types.add("PRESENT");
        types.add("ABSENT");

        for (String type :types){
            oblist.add(type);
        }
        comPORA.setItems(oblist);
    }

    @FXML
    void serchEmployeeOnAction(KeyEvent event) {

    }


    @FXML
    void serchTypeOnAction(KeyEvent event) {


            try {
                List<MemberAttendane> Attendanes = MemberAttendModel.getAll(txtMember.getText());
                MemberAttendanceTm memberAttendanceTm = null;
                oblist.clear();

                for (MemberAttendane memberAttendane : Attendanes) {

                    oblist.add(new MemberAttendanceTm(memberAttendane.getMemberId(), memberAttendane.getDate(), memberAttendane.getTime(), memberAttendane.getIsAttendance()));

                }
                tblPorA.setItems(oblist);

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "THIS MEMBER NOT CAME TO DAY..!").show();
            }

    }

    private void loadTbale() {
        tblId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        tblDateId.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblTimeId.setCellValueFactory(new PropertyValueFactory<>("time"));
        tblAttendens.setCellValueFactory(new PropertyValueFactory<>("isAttendance"));
    }






}
