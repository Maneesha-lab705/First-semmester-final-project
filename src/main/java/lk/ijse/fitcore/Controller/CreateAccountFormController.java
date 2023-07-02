package lk.ijse.fitcore.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fitcore.Model.UserModel;
import lk.ijse.fitcore.dto.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class CreateAccountFormController implements Initializable {
    private final static String URL = "jdbc:mysql://localhost:3306/Fitcore";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }


    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserId;

    @FXML
    void ExiteOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Loging_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("create account");
        stage.centerOnScreen();
    }


    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException {
        String id =txtUserId.getText();
        String name =txtName.getText();
        String nic =txtNic.getText();
        String  email = txtEmail.getText();
        String password = txtPassword.getText();

        User user = new User(id, name, nic, email, password);

        boolean isSave = UserModel.save(user);
        if (isSave){
            new Alert(Alert.AlertType.CONFIRMATION,"Save it..!").show();
            txtName.clear();
            txtUserId.clear();
            txtEmail.clear();
            txtPassword.clear();
            txtNic.clear();
        }





    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
