package frontend.wheel;

import frontend.backend.Employee;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WheelPageController extends BaseController implements Initializable{
    @FXML
    private Label Label1, Label2, Label3, Label4, Label5, Label6, PrizeNameLabel, ItemLabel;

    @FXML
    private ArrayList<Label> employeeNumberLabels = new ArrayList<>();

    @FXML
    private ChoiceBox<String> PrizeChoiceBox;

    @FXML
    private Button StartButton, SettingButton;

    @FXML
    private Button EmployeeButton;

    @FXML
    private Button HomePageButton;

    @FXML
    private Button PrizeButton;

    @FXML
    private HBox PopUpContainer;

    @FXML
    private ImageView PrizeImageView;

    private Timeline timeline = new Timeline();

    private int currentPrize;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpWheelLabels();
        choiceBoxSetUp();
        renderLuckyNumber("000000");
        buttonsSetUp();
        setUpInvisible();
    }

    public void randomWheel(ActionEvent event) throws IOException {
        timeline.getKeyFrames().clear();
        currentLucky.clear();
        int amount = prizes.get(currentPrize).getAmount();

        int rounds = getMin(amount, Common.wheel.getRemainEmployees().size(), 20);

        System.out.println(amount);
        System.out.println(Common.wheel.getRemainEmployees().size());

        for(int j = 0; j < 6; ++j) {
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(j * 0.1), e -> {
                renderLuckyNumber(Common.wheel.randomSixTimes());
            });

            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.6), e -> {
            renderOneWheel();
        }));

        KeyFrame keyFrameDelay = new KeyFrame(
                Duration.seconds(2),
                e -> {} // No action
        );
        timeline.getKeyFrames().add(keyFrameDelay);

        timeline.setCycleCount(rounds);

        timeline.setOnFinished(e -> {
            try {
                prizes.get(currentPrize).increaseCurrentRound();
                switchToResultPage(event);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        timeline.play();

    }

    private void buttonsSetUp() {
        ButtonSetUp(SettingButton, Common.HomeButtonImagePath, 40, 40);
    }

    private void renderOneWheel() {
        Integer index = Common.wheel.randomOneRound(prizes.get(currentPrize));
        currentLucky.add(index);
        Employee employee = employees.get(index);
        employee.setPrize(prizes.get(currentPrize));
        renderLuckyNumber(employee.getId());
        System.out.println(employee.getId());
    }

    private void switchToResultPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("result.fxml"));
        Parent root = fxmlLoader.load();

        ResultPageController controller = fxmlLoader.getController();
        controller.setPrize(prizes.get(currentPrize));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        double width = stage.getWidth();
        double height = stage.getHeight();

        stage.setScene(scene);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.show();
    }

    public void switchToPrizePage(ActionEvent event) throws IOException {
        switchToOtherPage("PrizePage.fxml", event);
    }

    public void switchToHomePage(ActionEvent event) throws IOException {
        switchToOtherPage("HomePage.fxml", event);
    }

    public void switchToEmployeePage(ActionEvent event) throws IOException {
        switchToOtherPage("EmployeePage.fxml", event);
    }

    private void setUpInvisible() {
        StartButton.setVisible(false);
        PrizeImageView.setVisible(false);
        PopUpContainer.setVisible(false);
    }

    public void settingPopUp() {
        PopUpContainer.setVisible(true);
    }

    public void closePopUp() {
        PopUpContainer.setVisible(false);
    }

    private void setUpWheelLabels() {
        employeeNumberLabels.add(Label1);
        employeeNumberLabels.add(Label2);
        employeeNumberLabels.add(Label3);
        employeeNumberLabels.add(Label4);
        employeeNumberLabels.add(Label5);
        employeeNumberLabels.add(Label6);
    }

    private void renderLuckyNumber(String luckyNumber) {
        for (int i = 0; i < 6; ++i) {
            employeeNumberLabels.get(i).setText(String.valueOf(luckyNumber.charAt(i)));
        }
    }

    private int getMin(int a, int b, int c) {
        int result = Math.min(a, b);
        if(c < result) {
            result = c;
        }

        return result;
    }

    private void setStartButton() {
        if(prizes.get(currentPrize).getAmount() > 0 && !Common.wheel.getRemainEmployees().isEmpty()) {
            String round = String.valueOf(prizes.get(currentPrize).getCurrentRound());
            StartButton.setText("Lần " + round);
            StartButton.setVisible(true);
        } else {
            StartButton.setVisible(false);
        }
    }

    private void choiceBoxSetUp() {
        int n = prizes.size();
        for(int i = 0; i < n; ++i) {
            String prizeIndex = String.valueOf(prizes.get(i).getIndex());
            PrizeChoiceBox.getItems().add("Prize " + prizeIndex);
        }

        PrizeChoiceBox.setOnAction(e -> {
            String[] parts = PrizeChoiceBox.getValue().split("\\s+");
            int index = Integer.parseInt(parts[1]) - 1;
            PrizeNameLabel.setText(prizes.get(index).getName());
            ItemLabel.setText(prizes.get(index).getItem());
            currentPrize = index;
            PrizeImageView.setImage(prizes.get(currentPrize).getImage());
            PrizeImageView.setVisible(true);
            setStartButton();
        });
    }
}
