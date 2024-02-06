package frontend.wheel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeePageController extends BaseController implements Initializable {
    @FXML
    private VBox EmployeeContainer;

    @FXML
    private Button ExitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUp();
    }

    private void setUp() {
        int size = employees.size();

        try{
            for(int i = 0; i < size; ++i) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EmployeeHBox.fxml"));
                HBox hBox = fxmlLoader.load();

                EmployeeHBoxController controller = fxmlLoader.getController();
                controller.setData(employees.get(i));

                EmployeeContainer.getChildren().add(hBox);
            }
        } catch (IOException e) {

        }
    }

    public void switchToWheelPage(ActionEvent event) throws IOException {
        switchToOtherPage("wheel.fxml", event);
    }
}
