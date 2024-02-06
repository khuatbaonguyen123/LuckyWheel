package frontend.wheel;

import frontend.backend.Employee;
import frontend.backend.Prize;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public abstract class BaseController {
    protected final ArrayList<Employee> employees = Common.wheel.getEmployees();
    protected final ArrayList<Prize> prizes = Common.wheel.getPrizes();
    protected static final ArrayList<Integer> currentLucky = new ArrayList<>();

    protected void switchToOtherPage(String fxmlFile, ActionEvent Event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node)Event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        double width = stage.getWidth();
        double height = stage.getHeight();

        stage.setScene(scene);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.show();
    }

    protected void ButtonSetUp(Button button, String filePath, double width, double height) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(filePath)));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        button.setGraphic(imageView);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
    }
}
