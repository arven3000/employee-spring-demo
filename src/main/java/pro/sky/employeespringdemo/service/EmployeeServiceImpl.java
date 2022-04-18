package pro.sky.employeespringdemo.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.employeespringdemo.dto.Employee;
import pro.sky.employeespringdemo.exception.EmployeeIsPresentException;
import pro.sky.employeespringdemo.exception.EmployeeNotFoundException;
import pro.sky.employeespringdemo.exception.EmployeeTextDataException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Map<String, Employee> getEmployees() {
        return employees;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int departmentId, double salary) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new EmployeeTextDataException("Некорректные данные.");
        }
        String key = StringUtils.capitalize(firstName.toLowerCase(Locale.ROOT))
                + " " + StringUtils.capitalize(lastName.toLowerCase(Locale.ROOT));
        if (employees.containsKey(key)) {
            throw new EmployeeIsPresentException("Сотрудник уже присутсвует.");
        }
        employees.put(key, new Employee(StringUtils.capitalize(firstName.toLowerCase(Locale.ROOT)),
                StringUtils.capitalize(lastName.toLowerCase(Locale.ROOT)), departmentId, salary));
        return employees.get(key);
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new EmployeeTextDataException("Некорректные данные.");
        }
        String key = StringUtils.capitalize(firstName.toLowerCase(Locale.ROOT))
                + " " + StringUtils.capitalize(lastName.toLowerCase(Locale.ROOT));
        if (employees.containsKey(key)) {
            Employee employee = employees.get(key);
            employees.remove(key);
            return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new EmployeeTextDataException("Некорректные данные.");
        }
        String key = StringUtils.capitalize(firstName.toLowerCase(Locale.ROOT))
                + " " + StringUtils.capitalize(lastName.toLowerCase(Locale.ROOT));
        if (employees.containsKey(key)) {
            return employees.get(key);
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }
}
