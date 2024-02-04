package frontend.wheel;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WheelPageController implements Initializable {
    @FXML
    private Label Label1, Label2, Label3, Label4, Label5, Label6, PrizeNameLabel;

    @FXML
    private ArrayList<Label> employeeNumberLabels = new ArrayList<>();

    @FXML
    private ChoiceBox<String> PrizeChoiceBox;

    @FXML
    private Button StartButton;

    @FXML
    private ImageView PrizeImageView;

    private Timeline timeline = new Timeline();

    private Timeline randomBeforeTimeline = new Timeline();

    private String currentPrize = Database.getPrizes().get(0).getName();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpWheelLabels();
        choiceBoxSetUp();
        PrizeNameLabel.setText(currentPrize);
        renderLuckyNumber("000000");
    }

    public void randomWheel(ActionEvent event) throws IOException {
        String[] parts = PrizeChoiceBox.getValue().split("\\s+");

        int prizeIndex = Integer.parseInt(parts[1]);

        currentPrize = Database.getPrizes().get(prizeIndex - 1).getName();
        PrizeNameLabel.setText(currentPrize);
        int rounds = Database.getPrizes().get(prizeIndex - 1).getAmount();

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2), e -> {
            renderBefore();
            Wheel.printRemainEmployees();
            Employee employee = Wheel.randomOneRound(Database.getPrizes().get(prizeIndex - 1));
            if (employee != null) {
                renderLuckyNumber(employee.getId());
            } else {
                // Handle the case when employee is null, e.g., display a message or do nothing
                System.out.println("No employee selected for this round.");
            }
        }));

        timeline.setCycleCount(rounds);
        timeline.play();
    }


    private void renderBefore() {
        randomBeforeTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.1), e -> {
            renderLuckyNumber(Wheel.randomNull());
        }));

        randomBeforeTimeline.setCycleCount(5);
        randomBeforeTimeline.play();
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

    private void choiceBoxSetUp() {
        int n = Database.getPrizes().size();
        for(int i = 0; i < n; ++i) {
            String prizeIndex = String.valueOf(Database.getPrizes().get(i).getIndex());
            PrizeChoiceBox.getItems().add("Prize " + prizeIndex);
        }
    }
}
