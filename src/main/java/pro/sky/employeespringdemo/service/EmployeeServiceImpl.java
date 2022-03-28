package pro.sky.employeespringdemo.service;

import org.springframework.stereotype.Service;
import pro.sky.employeespringdemo.dto.Employee;
import pro.sky.employeespringdemo.exception.EmployeeArrayIsFullException;
import pro.sky.employeespringdemo.exception.EmployeeIsPresentException;
import pro.sky.employeespringdemo.exception.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Employee[] employees = new Employee[10];

    {
        employees[0] = new Employee("Max", "Miller");
        employees[1] = new Employee("Petr", "Sidorov");
        employees[4] = new Employee("Maria", "Ivanova");
        employees[3] = new Employee("Sergey", "Petrov");
        employees[2] = new Employee("Olga", "Semenova");

    }

    public EmployeeServiceImpl() {
        super();
    }

    @Override
    public Employee[] employeesList() {
        return employees;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {

        Employee employee = new Employee(firstName, lastName);

        if (find(employee)) {
            throw new EmployeeIsPresentException("Сотрудник уже существует.");
        }
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = employee;
                return employees[i];
            }
        }
        throw new EmployeeArrayIsFullException("Список переполнен.");
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {

        Employee employee = new Employee(firstName, lastName);

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                continue;
            }
            if (employees[i].equals(employee)) {
                employees[i] = null;
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee emp = new Employee(firstName, lastName);
        if (find(emp)) {
            return emp;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден.");
        }
    }

    private boolean find(Employee emp) {
        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }
            if (employee.equals(emp)) {
                return true;
            }
        }
        return false;
    }
}
