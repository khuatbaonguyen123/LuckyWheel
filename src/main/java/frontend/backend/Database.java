package frontend.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Database {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static ArrayList<Prize> prizes = new ArrayList<>();

    public static ArrayList<Employee> getEmployees() {
        if(employees.isEmpty()) {
            loadEmployees("src/main/resources/frontend/Database/Employees.txt");
        }
        return employees;
    }

    public static ArrayList<Prize> getPrizes() {
        if(prizes.isEmpty()) {
            loadPrizes("src/main/resources/frontend/Database/Prizes.txt");
        }
        return prizes;
    }

    private static void loadPrizes(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] data = new String[5]; // Reuse the same array

            while ((line = reader.readLine()) != null) {
                data = line.split(",");
                String name = data[0].trim();
                int index = Integer.parseInt(data[1].trim());
                int amount = Integer.parseInt(data[2].trim());
                String imagePath = data[3].trim();
                String item = data[4].trim();

                // Process the prize data immediately (e.g., create a Prize object)
                prizes.add(new Prize(name, index, amount, imagePath, item));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadEmployees(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] data = new String[3]; // Reuse the same array

            while ((line = reader.readLine()) != null) {
                data = line.split(",");
                String name = data[0].trim();
                String id = data[1].trim();
                String department = data[2].trim();

                employees.add(new Employee(name, id, department));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
