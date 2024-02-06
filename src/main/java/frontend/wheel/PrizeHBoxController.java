package frontend.wheel;

import frontend.backend.Employee;
import frontend.backend.Prize;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class PrizeHBoxController {
    @FXML
    private Label AmountLabel;

    @FXML
    private Label IdLabel;

    @FXML
    private Label ItemLabel;

    @FXML
    private Label NameLabel;

    public void setData(Prize prize) {
        IdLabel.setText(String.valueOf(prize.getIndex()));
        NameLabel.setText(prize.getName());
        ItemLabel.setText(prize.getItem());
        AmountLabel.setText(String.valueOf(prize.getAmount()));
    }

}
