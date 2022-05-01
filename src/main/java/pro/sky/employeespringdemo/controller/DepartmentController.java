package pro.sky.employeespringdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import pro.sky.employeespringdemo.dto.Employee;
import pro.sky.employeespringdemo.exception.EmployeeIsPresentException;
import pro.sky.employeespringdemo.exception.EmployeeNotFoundException;
import pro.sky.employeespringdemo.service.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static pro.sky.employeespringdemo.controller.EmployeeController.getStringStringMap;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam("departmentId") int departmentId) {
        return departmentService.minSalary(departmentId);
    }

    @GetMapping(path = "/max-salary")
    public Employee maxSalary(@RequestParam("departmentId") int departmentId) {
        return departmentService.maxSalary(departmentId);
    }

    @GetMapping("/all")
    public ArrayList<Employee> allByDepartment(@RequestParam(value = "departmentId",
            required = false) Optional<Integer> departmentId) {
        if (departmentId.isPresent()) {
            return departmentService.filterByDepartment(departmentId.get());
        } else {
            return departmentService.employeeByDepartment();
        }
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleException404(EmployeeNotFoundException ex,
                                                                  HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(getMapResponseEntity(ex.getMessage(), request, status), status);
    }

    @ExceptionHandler(EmployeeIsPresentException.class)
    public ResponseEntity<Map<String, String>> handleException500(EmployeeIsPresentException ex,
                                                                  HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(getMapResponseEntity(ex.getMessage(), request, status), status);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleException500(MissingServletRequestParameterException ex,
                                                                  HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(getMapResponseEntity(ex.getMessage(), request, status), status);
    }

    private Map<String, String> getMapResponseEntity(String message, HttpServletRequest request, HttpStatus status) {
        return getStringStringMap(message, request, status);
    }
}
