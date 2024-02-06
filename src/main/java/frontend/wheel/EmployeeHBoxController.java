package frontend.wheel;

import frontend.backend.Employee;
import frontend.backend.Prize;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.jar.Attributes;

public class EmployeeHBoxController {
    @FXML
    private Label DepartmentLabel;

    @FXML
    private Label IdLabel;

    @FXML
    private Label NameLabel;

    @FXML
    private Label PrizeLabel;

    public void setData(Employee employee) {
        IdLabel.setText(employee.getId());
        NameLabel.setText(employee.getName());
        DepartmentLabel.setText(employee.getDepartment());
        if(employee.getPrize() != null) {
            PrizeLabel.setText(employee.getPrize().getName());
            PrizeLabel.setTextFill(Color.RED);
        } else {
            PrizeLabel.setText("Prize: None");
        }
    }

}
