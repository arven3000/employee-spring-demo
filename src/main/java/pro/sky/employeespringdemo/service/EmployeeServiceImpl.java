package pro.sky.employeespringdemo.service;

import org.springframework.stereotype.Service;
import pro.sky.employeespringdemo.dto.Employee;
import pro.sky.employeespringdemo.exception.EmployeeIsPresentException;
import pro.sky.employeespringdemo.exception.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Map<String, Employee> getEmployees() {
        return employees;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int departmentId, double salary) {
        String key = firstName + " " + lastName;
        if (employees.containsKey(key)) {
            throw new EmployeeIsPresentException("Сотрудник уже присутсвует.");
        }
        employees.put(key, new Employee(firstName, lastName, departmentId, salary));
        return employees.get(key);
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        if (employees.containsKey(key)) {
            Employee employee = employees.get(key);
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

    /*Получить Ф. И. О. всех сотрудников по отделам*/
    @Override
    public ArrayList<Employee> filterByDepartment(int departmentId) {
        return employees.values().stream()
                .filter(e -> e.getDepartment() == departmentId).collect(Collectors.toCollection(ArrayList::new));
    }

    /*Найти сотрудника с минимальной зарплатой по отделу*/
    @Override
    public Employee minSalary(int departmentId) {
        ArrayList<Employee> employeesByDepartment = filterByDepartment(departmentId);
        return minSalary(employeesByDepartment);
    }

    /*Найти сотрудника с минимальной зарплатой*/
    @Override
    public Employee minSalary(ArrayList<Employee> employees) {
        return employees.stream().min(Comparator.comparing(Employee::getSalary)).orElseThrow(() -> new EmployeeNotFoundException("Список пуст"));
    }

    /*Найти сотрудника с максимальной заплатой по отделу*/
    @Override
    public Employee maxSalary(int departmentId) {
        ArrayList<Employee> employeesByDepartment = filterByDepartment(departmentId);
        return maxSalary(employeesByDepartment);
    }

    /*Найти сотрудника с максимальной зарплатой*/
    @Override
    public Employee maxSalary(ArrayList<Employee> employees) {
        return employees.stream().max(Comparator.comparing(Employee::getSalary)).orElseThrow(() -> new EmployeeNotFoundException("Список пуст"));
    }


    /*Получить Ф. И. О. всех сотрудников по отделам(список отделов и их сотрудников)*/
    @Override
    public ArrayList<Employee> employeeByDepartment() {
        return employees.values().stream().sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
