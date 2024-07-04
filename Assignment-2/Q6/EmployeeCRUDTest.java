import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee {
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', department='" + department + "'}";
    }
}

class EmployeeCRUD {
    private List<Employee> employees = new ArrayList<>();

    // Create
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Read
    public Employee getEmployee(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    // Update
    public void updateEmployee(Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == employee.getId()) {
                employees.set(i, employee);
                return;
            }
        }
    }

    // Delete
    public void deleteEmployee(int id) {
        employees.removeIf(emp -> emp.getId() == id);
    }
}

public class EmployeeCRUDTest {
    public static void main(String[] args) {
        EmployeeCRUD crud = new EmployeeCRUD();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. View Employee by ID");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();
                    crud.addEmployee(new Employee(id, name, department));
                    System.out.println("Employee added successfully.");
                    break;
                case 2:
                    List<Employee> allEmployees = crud.getAllEmployees();
                    for (Employee emp : allEmployees) {
                        System.out.println(emp);
                    }
                    break;
                case 3:
                    System.out.print("Enter ID to view: ");
                    int viewId = scanner.nextInt();
                    Employee emp = crud.getEmployee(viewId);
                    if (emp != null) {
                        System.out.println(emp);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Employee updateEmp = crud.getEmployee(updateId);
                    if (updateEmp != null) {
                        System.out.print("Enter new Name: ");
                        updateEmp.setName(scanner.nextLine());
                        System.out.print("Enter new Department: ");
                        updateEmp.setDepartment(scanner.nextLine());
                        crud.updateEmployee(updateEmp);
                        System.out.println("Employee updated successfully.");
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = scanner.nextInt();
                    crud.deleteEmployee(deleteId);
                    System.out.println("Employee deleted successfully.");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}