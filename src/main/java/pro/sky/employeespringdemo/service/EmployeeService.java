package pro.sky.employeespringdemo.service;

import pro.sky.employeespringdemo.dto.Employee;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName);
    Employee deleteEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
}
