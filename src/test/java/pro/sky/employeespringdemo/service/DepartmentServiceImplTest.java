package pro.sky.employeespringdemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.employeespringdemo.dto.Employee;
import pro.sky.employeespringdemo.exception.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    private final Employee employee1 = new Employee("Мария", "Иванова", 1, 1000.0);
    private final Employee employee2 = new Employee("Сергей", "Петров", 2, 1200.0);
    private final Employee employee3 = new Employee("Ольга", "Смирнова", 1, 1100.0);

    private final Map<String, Employee> employeeMap = new HashMap<>();

    @BeforeEach
    public void setEmployeeMap() {
        employeeMap.put("Мария Иванова", employee1);
        employeeMap.put("Сергей Петров", employee2);
        employeeMap.put("Ольга Смирнова", employee3);
    }

    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl out;

    @Test
    void filterByDepartmentTest() {

        when(employeeServiceMock.getEmployees()).thenReturn(employeeMap);

        ArrayList<Employee> list = new ArrayList<>();
        list.add(employee3);
        list.add(employee1);

        ArrayList<Employee> employees = out.filterByDepartment(1);
        assertEquals(list, employees);

        verify(employeeServiceMock, times(1)).getEmployees();

        list.clear();
        list.add(employee2);

        employees = out.filterByDepartment(2);
        assertEquals(list, employees);

        verify(employeeServiceMock, times(2)).getEmployees();
    }

    @Test
    void minSalaryByDepartmentTest() {
        when(employeeServiceMock.getEmployees()).thenReturn(employeeMap);

        Employee employee = out.minSalary(1);
        assertEquals(employee1, employee);

        verify(employeeServiceMock, times(1)).getEmployees();

        employee = out.minSalary(2);
        assertEquals(employee2, employee);

        verify(employeeServiceMock, times(2)).getEmployees();
    }

    @Test
    void maxSalaryByDepartmentTest() {
        when(employeeServiceMock.getEmployees()).thenReturn(employeeMap);

        Employee employee = out.maxSalary(1);
        assertEquals(employee3, employee);

        verify(employeeServiceMock, times(1)).getEmployees();

        employee = out.maxSalary(2);
        assertEquals(employee2, employee);

        verify(employeeServiceMock, times(2)).getEmployees();
    }

    @Test
    void employeeByDepartment() {
        when(employeeServiceMock.getEmployees()).thenReturn(employeeMap);

        ArrayList<Employee> list = new ArrayList<>();
        list.add(employee3);
        list.add(employee1);
        list.add(employee2);

        ArrayList<Employee> employees = out.employeeByDepartment();
        assertEquals(list, employees);

        verify(employeeServiceMock, times(1)).getEmployees();
    }

    @Test
    void employeeNotFoundExceptionTest() {
        when(employeeServiceMock.getEmployees()).thenThrow(EmployeeNotFoundException.class);

        assertThrows(EmployeeNotFoundException.class, () -> out.minSalary(1));
        assertThrows(EmployeeNotFoundException.class, () -> out.maxSalary(2));
    }
}