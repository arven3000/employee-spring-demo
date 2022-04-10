package pro.sky.employeespringdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import pro.sky.employeespringdemo.dto.Employee;
import pro.sky.employeespringdemo.exception.EmployeeIsPresentException;
import pro.sky.employeespringdemo.exception.EmployeeNotFoundException;
import pro.sky.employeespringdemo.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Map<String, Employee> getEmployeesList() {
        return employeeService.getEmployees();
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName, @RequestParam("departmentId") int departmentId,
                                @RequestParam("salary") double salary) {
        try {
            return employeeService.addEmployee(firstName, lastName, departmentId, salary);
        } catch (EmployeeIsPresentException e) {
            throw new EmployeeIsPresentException(e.getMessage());
        }
    }

    @GetMapping("/remove")
    public Employee deleteEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName) {
        try {
            return employeeService.deleteEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            throw new EmployeeNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        try {
            return employeeService.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            throw new EmployeeNotFoundException(e.getMessage());
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
        Map<String, String> answer = new LinkedHashMap<>();
        answer.put("message ", message);
        answer.put("error ", status.getReasonPhrase());
        answer.put("status ", String.valueOf(status.value()));
        answer.put("timestamp ", LocalDateTime.now().toString());
        answer.put("path ", request.getRequestURI());
        return answer;
    }
}

