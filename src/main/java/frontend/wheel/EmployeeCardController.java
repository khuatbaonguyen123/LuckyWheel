package frontend.wheel;

import frontend.backend.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EmployeeCardController {
    @FXML
    private Label EmployeeIdLabel;

    @FXML
    private Label EmployeeNameLabel;

    private Employee employee;

    public void setData(Employee employee) {
        this.employee = employee;
        EmployeeIdLabel.setText(employee.getId());
        EmployeeNameLabel.setText(employee.getName());
    }
}
