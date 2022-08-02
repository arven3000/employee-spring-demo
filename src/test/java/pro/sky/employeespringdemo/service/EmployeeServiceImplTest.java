package pro.sky.employeespringdemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.employeespringdemo.dto.Employee;
import pro.sky.employeespringdemo.exception.EmployeeIsPresentException;
import pro.sky.employeespringdemo.exception.EmployeeNotFoundException;
import pro.sky.employeespringdemo.exception.EmployeeTextDataException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private final Employee employee1 = new Employee("Мария", "Иванова", 1, 1000.0);
    private final Employee employee2 = new Employee("Сергей", "Петров", 2, 1200.0);
    private final Employee employee3 = new Employee("Ольга", "Смирнова", 1, 1100.0);

    private EmployeeService employeeService;

    @BeforeEach
    public void setEmployee() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    void addAndGetEmployeeTest() {
        employeeService.addEmployee(employee1.getFirstName(), employee1.getLastname(), employee1.getDepartment(), employee1.getSalary());

        assertEquals(1, employeeService.getEmployees().size());

        Map<String,Employee> employeeMap = new HashMap<>();
        employeeMap.put("Мария Иванова", employee1);

        assertEquals(employeeMap, employeeService.getEmployees());

        employeeMap.put("Сергей Петров", employee2);
        employeeMap.put("Ольга Смирнова", employee3);

        employeeService.addEmployee(employee2.getFirstName(), employee2.getLastname(), employee2.getDepartment(), employee2.getSalary());
        employeeService.addEmployee(employee3.getFirstName(), employee3.getLastname(), employee3.getDepartment(), employee3.getSalary());

        assertEquals(employeeMap, employeeService.getEmployees());
        assertEquals(3, employeeService.getEmployees().size());
        assertNotNull(employeeService.getEmployees());
    }

    @Test
    void deleteEmployeeTest() {
        employeeService.addEmployee(employee1.getFirstName(), employee1.getLastname(), employee1.getDepartment(), employee1.getSalary());
        employeeService.addEmployee(employee2.getFirstName(), employee2.getLastname(), employee2.getDepartment(), employee2.getSalary());
        employeeService.addEmployee(employee3.getFirstName(), employee3.getLastname(), employee3.getDepartment(), employee3.getSalary());

        assertEquals(3, employeeService.getEmployees().size());

        employeeService.deleteEmployee("мариЯ", "Иванова");

        assertEquals(2, employeeService.getEmployees().size());

        employeeService.deleteEmployee("ольга", "сМирнова");

        assertEquals(1, employeeService.getEmployees().size());


        Map<String,Employee> employeeMap = new HashMap<>();
        employeeMap.put("Сергей Петров", employee2);

        assertEquals(employeeMap, employeeService.getEmployees());
    }

    @Test
    void findEmployeeTest() {
        employeeService.addEmployee(employee1.getFirstName(), employee1.getLastname(), employee1.getDepartment(), employee1.getSalary());
        employeeService.addEmployee(employee2.getFirstName(), employee2.getLastname(), employee2.getDepartment(), employee2.getSalary());
        employeeService.addEmployee(employee3.getFirstName(), employee3.getLastname(), employee3.getDepartment(), employee3.getSalary());

        assertEquals(3, employeeService.getEmployees().size());

        assertEquals(employee2, employeeService.findEmployee("сергей", "петров"));

        assertEquals(employee1, employeeService.findEmployee("мариЯ", "Иванова"));

        assertEquals(employee3, employeeService.findEmployee("ольга", "сМирнова"));

    }

    @Test
    void employeeDataExceptionTest() {
        assertThrows(EmployeeTextDataException.class, () -> employeeService.addEmployee("Mast4er", "Lomaster", 1, 100.0));
        assertThrows(EmployeeTextDataException.class, () -> employeeService.addEmployee("Master", "Lomas_ter", 1, 100.0));
        assertThrows(EmployeeTextDataException.class, () -> employeeService.deleteEmployee("Master?", "Lomaster"));
        assertThrows(EmployeeTextDataException.class, () -> employeeService.findEmployee("Master", "@Lomaster"));
    }

    @Test
    void employeePresentExceptionTest() {
        employeeService.addEmployee(employee1.getFirstName(), employee1.getLastname(), employee1.getDepartment(), employee1.getSalary());
        assertThrows(EmployeeIsPresentException.class, () -> employeeService.addEmployee("Мария", "Иванова", 1, 1000.0));
    }

    @Test
    void employeeNotFoundExceptionTest() {
        employeeService.addEmployee(employee1.getFirstName(), employee1.getLastname(), employee1.getDepartment(), employee1.getSalary());
        employeeService.addEmployee(employee2.getFirstName(), employee2.getLastname(), employee2.getDepartment(), employee2.getSalary());
        employeeService.addEmployee(employee3.getFirstName(), employee3.getLastname(), employee3.getDepartment(), employee3.getSalary());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee("Master", "Lomaster"));
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("Master", "Lomaster"));
    }
}