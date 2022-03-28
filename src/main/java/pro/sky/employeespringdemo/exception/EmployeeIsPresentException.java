package pro.sky.employeespringdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeIsPresentException extends RuntimeException {
    public EmployeeIsPresentException(String message) {
        super(message);
    }
}
