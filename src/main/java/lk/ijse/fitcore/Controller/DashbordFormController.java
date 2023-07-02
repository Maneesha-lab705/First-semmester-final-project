package lk.ijse.fitcore.Controller;



import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class DashbordFormController implements Initializable {

    @FXML
    private JFXButton btnreport;


    @FXML
    private Label lblEmail;

    @FXML
    private Label labalUserName;


    @FXML
    private AnchorPane LoadContex;

    @FXML
    private Label lbldat;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTriners;

    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbldat.setText(String.valueOf(LocalDate.now()));
        lblTime.setText(String.valueOf(LocalTime.now()));
        lblEmail.setText("maneeshadilshan7089@gmail.com");
        labalUserName.setText("dilshan");
    }

    @FXML
  public void MamberOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/member_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContex.getChildren().clear();
        LoadContex.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), LoadContex);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void HomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashbord_form.fxml"))));
        window.centerOnScreen();
        window.show();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), root);
        transition.setFromX(window.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void EmployeeOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/employee_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContex.getChildren().clear();
        LoadContex.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), LoadContex);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void ItemOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/item_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContex.getChildren().clear();
        LoadContex.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), LoadContex);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void OrderOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/order_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContex.getChildren().clear();
        LoadContex.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), LoadContex);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void PaymentOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/payment_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContex.getChildren().clear();
        LoadContex.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), LoadContex);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void TrinerOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/triner_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContex.getChildren().clear();
        LoadContex.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), LoadContex);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void LogoutOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Loging_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.centerOnScreen();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), root);
        transition.setFromX(stage.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnShedulOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/createShedule_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContex.getChildren().clear();
        LoadContex.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), LoadContex);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void AttendensOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/showAttends_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContex.getChildren().clear();
        LoadContex.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), LoadContex);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnFbOnAction(ActionEvent event) {

    }

    @FXML
    void btnGooglOnAction(ActionEvent event) {

    }

    @FXML
    void btnMailOnAction(ActionEvent event) {

    }

    public void btnExerciseOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/exercise_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContex.getChildren().clear();
        LoadContex.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), LoadContex);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
        //new Flip(LoadContex).play();
    }

    public void btnReportOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/Report_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContex.getChildren().clear();
        LoadContex.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), LoadContex);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
        

    }
}
