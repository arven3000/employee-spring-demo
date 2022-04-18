package pro.sky.employeespringdemo.service;

import pro.sky.employeespringdemo.dto.Employee;

import java.util.Map;

public interface EmployeeService {

    Map<String, Employee> getEmployees();

    Employee addEmployee(String firstName, String lastName, int department, double salary);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);
}
