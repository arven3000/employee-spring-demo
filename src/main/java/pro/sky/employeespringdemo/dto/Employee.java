package pro.sky.employeespringdemo.dto;

import java.util.Objects;

public class Employee {

    private final String firstName;
    private final String lastname;
    private int department;
    private double salary;

    public Employee(String firstName, String lastname, int department, double salary) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.department = department;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        if (department < 1 || department > 5) {
            throw new IllegalArgumentException("Департамент задан не верно");
        }
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Зарплата не может быть отрицательной");
        }
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && Double.compare(employee.salary, salary) == 0 && firstName.equals(employee.firstName) && lastname.equals(employee.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastname, department, salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }
}