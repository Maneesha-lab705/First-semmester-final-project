
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
import lk.ijse.fitcore.Model.ShowEmployeeAttendanceModel;
import lk.ijse.fitcore.Model.ShowMemberAttendanceModel;
import lk.ijse.fitcore.dto.ShowEmployeeAttend;
import lk.ijse.fitcore.dto.ShowMemberAttend;
import lk.ijse.fitcore.dto.tm.ShowEmployeeTm;
import lk.ijse.fitcore.dto.tm.ShowMemberTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ShowAttendsFormController implements Initializable {

    ObservableList<ShowEmployeeTm> oblist = FXCollections.observableArrayList();
    ObservableList<ShowMemberTm> oblist1 = FXCollections.observableArrayList();

    @FXML
    private AnchorPane root;

        @FXML
        private TableColumn<?, ?> tblEId;

        @FXML
        private TableColumn<?, ?> tblId;

        @FXML
        private TableView<ShowEmployeeTm> tblEmployeeCart;

         @FXML
         private TableView<ShowMemberTm> tblMemberCart;

        @FXML
        private TableColumn<?, ?> tblName;

        @FXML
        private TableColumn<?, ?> tblEName;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            loadEmployeeTable();
            setValuesForTable();
            loadMemberTable();
            selValueForMemberTbale();
        }



    void  loadEmployeeTable(){
            try {
                List<ShowEmployeeAttend> isget = ShowEmployeeAttendanceModel.getAll();

                for (ShowEmployeeAttend showEmployeeAttend :isget){
                    oblist.add(new ShowEmployeeTm(showEmployeeAttend.getEmployeeID(),showEmployeeAttend.getName()));

                }

                tblEmployeeCart.setItems(oblist);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        void  setValuesForTable(){

            tblEId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
            tblEName.setCellValueFactory(new PropertyValueFactory<>("name"));


        }

        void loadMemberTable(){
            try {
                List<ShowMemberAttend> all= ShowMemberAttendanceModel.getAll();

                for (ShowMemberAttend memberAttend :all){
                    oblist1.add(new ShowMemberTm(memberAttend.getMemberId(),memberAttend.getName()));
                }

                tblMemberCart.setItems(oblist1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private void selValueForMemberTbale() {
            tblId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
            tblName.setCellValueFactory(new PropertyValueFactory<>("name"));



          }


        @FXML
        void btnEmployeeONAction(ActionEvent event) throws IOException {
            URL resource = getClass().getResource("/view/attendens_form.fxml");
            assert resource != null;
            Parent load = FXMLLoader.load(resource);
            root.getChildren().clear();
            root.getChildren().add(load);

        }

        @FXML
        void btnMemberOnAction(ActionEvent event) throws IOException {
         URL resource =getClass().getResource("/view/memberAttends_form.fxml");
         assert resource !=null;
         Parent load =FXMLLoader.load(resource);
         root.getChildren().clear();
         root.getChildren().add(load);

        }

}


