package pro.sky.employeespringdemo.service;

import pro.sky.employeespringdemo.dto.Employee;

import java.util.ArrayList;

public interface EmployeeService {
    ArrayList<Employee> getEmployeesList();
    Employee addEmployee(String firstName, String lastName);
    Employee deleteEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
}
