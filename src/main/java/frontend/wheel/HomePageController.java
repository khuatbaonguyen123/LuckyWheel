package frontend.wheel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController extends BaseController{

    @FXML
    private Button EmployeeButton;

    @FXML
    private Button PlayButton;

    @FXML
    private Button PrizeButton;

    @FXML
    private Button QuitButton;

    public void switchToWheelPage(ActionEvent event) throws IOException {
        switchToOtherPage("wheel.fxml", event);
    }

    public void switchToEmployeePage(ActionEvent event) throws IOException {
        switchToOtherPage("EmployeePage.fxml", event);
    }

    public void switchToPrizePage(ActionEvent event) throws IOException {
        switchToOtherPage("PrizePage.fxml", event);
    }

    public void logOut(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit the program!");
        alert.setContentText("Do you really wanna exit?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

}