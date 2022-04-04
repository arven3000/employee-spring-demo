package pro.sky.employeespringdemo.service;

import org.springframework.stereotype.Service;
import pro.sky.employeespringdemo.dto.Employee;
import pro.sky.employeespringdemo.exception.EmployeeIsPresentException;
import pro.sky.employeespringdemo.exception.EmployeeNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    public EmployeeServiceImpl() {
        super();
    }

    @Override
    public Map<String, Employee> getEmployees() {
        return employees;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        if (employees.containsKey(key)) {
            throw new EmployeeIsPresentException("Сотрудник уже присутсвует.");
        }
        employees.put(key, new Employee(firstName, lastName));
        return employees.get(key);
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(key)) {
            employees.remove(key);
            return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        if (employees.containsKey(key)) {
            return employees.get(key);
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }
}
