package pro.sky.employeespringdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.employeespringdemo.dto.Employee;
import pro.sky.employeespringdemo.exception.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /*Получить Ф. И. О. всех сотрудников по отделам*/
    @Override
    public ArrayList<Employee> filterByDepartment(int departmentId) {
        return employeeService.getEmployees().values().stream()
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
        return employeeService.getEmployees().values().stream().sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
