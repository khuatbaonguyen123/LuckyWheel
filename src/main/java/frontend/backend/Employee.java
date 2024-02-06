package frontend.backend;

public class Employee {
    private String name;
    private String id;
    private String department;
    private Prize prize;

    public Employee(String name, String id, String department) {
        this.name = name;
        this.id = id;
        this.department = department;
        prize = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public Prize getPrize() {
        return prize;
    }
}
