package pro.sky.employeespringdemo.service;

import pro.sky.employeespringdemo.dto.Employee;

import java.util.ArrayList;
import java.util.Map;

public interface EmployeeService {
    Map<String, Employee> getEmployees();

    ArrayList<Employee> filterByDepartment(int departmentId);

    Employee minSalary(ArrayList<Employee> employees);

    Employee minSalary(int departmentId);

    Employee addEmployee(String firstName, String lastName, int department, double salary);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Employee maxSalary(int departmentId);

    Employee maxSalary(ArrayList<Employee> employees);

    ArrayList<Employee> employeeByDepartment();
}
