package frontend.wheel;

import frontend.backend.Prize;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResultPageController extends BaseController implements Initializable {
    @FXML
    private Button ExitButton;

    @FXML
    private Label PrizeNameLabel;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private StackPane stack;

    private Prize prize;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPrize(Prize prize) {
        this.prize = prize;
        PrizeNameLabel.setText(prize.getName());
        gridPaneSetUp();
    }

    public void switchToWheelPage(ActionEvent event) throws IOException {
        switchToOtherPage("wheel.fxml", event);
    }

    private void createGridPane() {
        // create new constraints for columns and set their percentage
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        columnConstraints.setFillWidth(true);

        // create new constraints for row and set their percentage
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        rowConstraints.setFillHeight(true);

        // don't set preferred size or anything on gridpane
        grid = new GridPane();
        grid.getRowConstraints().add(rowConstraints);
        grid.getColumnConstraints().add(columnConstraints);
    }

    public void deleteResult(ActionEvent event) throws IOException {
        int size = currentLucky.size();
        for (int i = 0; i < size; ++i) {
            employees.get(currentLucky.get(i)).setPrize(null);
            Common.wheel.addRemainEmployees(currentLucky.get(i));
            prize.increaseAmount();
        }
        prize.decreaseCurrentRound();
        switchToOtherPage("wheel.fxml", event);
    }

    private void gridPaneSetUp() {
        createGridPane();

        int column = 0;
        int row = 1;

        int size = employees.size();

        try {
            for (int i = 0; i < size; ++i) {
                if (employees.get(i).getPrize() == prize) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("EmployeeCard.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    EmployeeCardController employeeCardController = fxmlLoader.getController();
                    employeeCardController.setData(employees.get(i));

                    if(column == 5) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(5));
                }
            }
            stack.getChildren().add(grid);
            StackPane.setAlignment(grid, Pos.TOP_CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
