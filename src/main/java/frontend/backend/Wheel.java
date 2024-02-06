package frontend.backend;

import java.util.ArrayList;
import java.util.Random;

public class Wheel {
    private final ArrayList<Employee> employees;
    private final ArrayList<Prize> prizes;
    private ArrayList<Integer> remainEmployees;

    public Wheel() {
        employees = Database.getEmployees();
        prizes = Database.getPrizes();
        initialRemainEmployees();
    }

    public ArrayList<Integer> getRemainEmployees() {
        return remainEmployees;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Prize> getPrizes() {
        return prizes;
    }

    private void initialRemainEmployees() {
        remainEmployees = new ArrayList<>();
        for(int i = 0; i < employees.size(); ++i) {
            remainEmployees.add(i);
        }
    }

    //Return the index of the employee after one round
    public Integer randomOneRound(Prize prize) {
        Random random = new Random();

        if(!remainEmployees.isEmpty() && prize.getAmount() > 0) {
            int randomEmployeeIndex = random.nextInt(remainEmployees.size());
            Integer index = remainEmployees.get(randomEmployeeIndex);

            remainEmployees.remove(randomEmployeeIndex);
            prize.decreaseAmount();

            return index;
        }

        return null;
    }

    public String randomSixTimes() {
        Random random = new Random();

        int randomIndex = random.nextInt(employees.size());

        return employees.get(randomIndex).getId();
    }

    public void addRemainEmployees(Integer index) {
        remainEmployees.add(index);
    }
}
