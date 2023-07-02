package lk.ijse.fitcore.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fitcore.Model.UserModel;
import lk.ijse.fitcore.db.DBConnection;
import lk.ijse.fitcore.dto.User;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogingFormController implements Initializable {



    @FXML
    private AnchorPane root;


    @FXML
    private TextField txtUserName;


    @FXML
    private ProgressBar porBar;

    @FXML
    private PasswordField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    @FXML
    void CreateAccountOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("/view/createAccount_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("create account");
        stage.centerOnScreen();

    }

    @FXML
    void ForgetOnAction(ActionEvent event) {

    }

    @FXML
    void LoginOnAction(ActionEvent event) throws IOException {

        String name = txtUserName.getText();
        String password = txtPassword.getText();
        try {
            boolean isConform = UserModel.search(name, password);
            if (isConform) {
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashbord_form.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("dash bord");
                stage.centerOnScreen();
            }
            if (!isConform) {
                new Alert(Alert.AlertType.ERROR, "WRONG USER OR PASSWORD!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void btnFacebookOnAction(ActionEvent actionEvent) {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI uri = new URI("https://web.facebook.com/");
            desktop.browse(uri);
        } catch (Exception ex) {
            ex.printStackTrace();}

    }

    public void btnGoogleOnAction(ActionEvent actionEvent) {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI uri = new URI("https://www.google.com/");
            desktop.browse(uri);
        } catch (Exception ex) {
            ex.printStackTrace();
}

    }

    public void proBarOnAction(MouseEvent mouseEvent) {
        porBar.setProgress(1.5);

    }


    public void txtUserNameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
        String name = txtUserName.getText();
        String password = txtPassword.getText();
        try {
            boolean isConform  = UserModel.search(name,password);
            if (isConform){
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashbord_form.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("create account");
                stage.centerOnScreen();
            }
            if (!isConform){
                new Alert(Alert.AlertType.ERROR,"WRONG USER OR PASSWORD!").show();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
