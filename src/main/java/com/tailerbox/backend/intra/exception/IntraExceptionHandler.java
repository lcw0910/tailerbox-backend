package com.tailerbox.backend.intra.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice(basePackages = {
        "com.tailerbox.backend"
})
@Slf4j
public class IntraExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HashMap<String, Object>> exceptionHandler(Exception e) {
        log.error(e.getMessage());
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseBody.put("message", "Internal Server Error");
        return ResponseEntity.internalServerError().body(responseBody);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, ArrayList<String>>> constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        Map<String, ArrayList<String>> errors = new HashMap<>();

        if (constraintViolations != null) {
            for (ConstraintViolation<?> c : constraintViolations) {
                String pathStr = c.getPropertyPath().toString();
                String[] paths = pathStr.split("\\.");
                String path = paths.length > 0 ? paths[paths.length - 1] : paths[0];
//                Path fieldName = c.getPropertyPath();
//                Object requestedValue = c.getInvalidValue();
                String message = c.getMessage();
                if (errors.get(path) == null) {
                    ArrayList<String> test = new ArrayList<>();
                    test.add(message);
                    errors.put(path, test);
                } else {
                    errors.get(path).add(message);
                }
            }
        }
        return ResponseEntity.unprocessableEntity().body(errors);
    }
}
