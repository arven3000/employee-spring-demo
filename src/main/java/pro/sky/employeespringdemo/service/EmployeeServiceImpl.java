package pro.sky.employeespringdemo.service;

import org.springframework.stereotype.Service;
import pro.sky.employeespringdemo.dto.Employee;
import pro.sky.employeespringdemo.exception.EmployeeIsPresentException;
import pro.sky.employeespringdemo.exception.EmployeeNotFoundException;

import java.util.ArrayList;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final ArrayList<Employee> employees = new ArrayList<>();

    public EmployeeServiceImpl() {
        super();
    }

    @Override
    public ArrayList<Employee> getEmployeesList() {
        return employees;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {

        Employee employee = new Employee(firstName, lastName);

        if (employees.contains(employee)) {
            throw new EmployeeIsPresentException("Сотрудник уже существует.");
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {

        Employee employee = new Employee(firstName, lastName);

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).equals(employee)) {
                employees.remove(i);
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee emp = new Employee(firstName, lastName);
        if (employees.contains(emp)) {
            return emp;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден.");
        }
    }
}
