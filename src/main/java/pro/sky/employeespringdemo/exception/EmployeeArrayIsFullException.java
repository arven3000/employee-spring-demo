package pro.sky.employeespringdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeArrayIsFullException extends RuntimeException {
    public EmployeeArrayIsFullException(String message) {
        super(message);
    }
}
