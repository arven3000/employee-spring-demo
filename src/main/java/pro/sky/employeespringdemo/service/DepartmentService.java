package pro.sky.employeespringdemo.service;

import pro.sky.employeespringdemo.dto.Employee;

import java.util.ArrayList;

public interface DepartmentService {
    /*Получить Ф. И. О. всех сотрудников по отделам*/
    ArrayList<Employee> filterByDepartment(int departmentId);

    /*Найти сотрудника с минимальной зарплатой по отделу*/
    Employee minSalary(int departmentId);

    /*Найти сотрудника с минимальной зарплатой*/
    Employee minSalary(ArrayList<Employee> employees);

    /*Найти сотрудника с максимальной заплатой по отделу*/
    Employee maxSalary(int departmentId);

    /*Найти сотрудника с максимальной зарплатой*/
    Employee maxSalary(ArrayList<Employee> employees);

    /*Получить Ф. И. О. всех сотрудников по отделам(список отделов и их сотрудников)*/
    ArrayList<Employee> employeeByDepartment();
}
