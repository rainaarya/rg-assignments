package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeDAO employeeDAO = (EmployeeDAO) context.getBean("employeeDAO");

        // Create
        Employee emp1 = new Employee();
        emp1.setName("John Doe");
        emp1.setDepartment("IT");

        Employee emp2 = new Employee();
        emp2.setName("Jane Doe");
        emp2.setDepartment("Finance");

        employeeDAO.create(emp1);
        employeeDAO.create(emp2);

        // Read
        Employee fetchedEmp = employeeDAO.getEmployee(1);
        System.out.println("Fetched: " + fetchedEmp.getName() + ", " + fetchedEmp.getDepartment());

        // Update
        fetchedEmp.setDepartment("HR");
        employeeDAO.update(fetchedEmp);

        // Read
        Employee fetchedEmp2 = employeeDAO.getEmployee(1);
        System.out.println("Fetched: " + fetchedEmp2.getName() + ", " + fetchedEmp2.getDepartment());

        // Delete
        employeeDAO.delete(1);
    }
}