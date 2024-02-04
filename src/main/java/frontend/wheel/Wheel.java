package frontend.wheel;

import java.util.ArrayList;
import java.util.Random;

public class Wheel {
    private static ArrayList<Employee> remainEmployees = new ArrayList<>(Database.getEmployees());

    public static ArrayList<Employee> getRemainEmployees() {
        return remainEmployees;
    }

    public static void printRemainEmployees() {
        for(int i = 0; i < remainEmployees.size(); ++i) {
            System.out.println("Employee" + String.valueOf(i));
        }
    }

    public static Employee randomOneRound(Prize prize) {
        Random random = new Random();

        if(!remainEmployees.isEmpty() && prize.getAmount() > 0) {
            int randomEmployeeIndex = random.nextInt(remainEmployees.size());
            Employee employee = remainEmployees.get(randomEmployeeIndex);

            employee.setPrize(prize);
            prize.decreaseAmount();
            remainEmployees.remove(randomEmployeeIndex);

            return employee;
        }

        return null;
    }

    public static String randomNull() {
        Random random = new Random();

        if(!Database.getEmployees().isEmpty()) {
            int randomEmployeeIndex = random.nextInt(Database.getEmployees().size());
            return Database.getEmployees().get(randomEmployeeIndex).getId();
        }

        return null;
    }
}
