package com.tailerbox.backend.intra.controller;

import com.tailerbox.backend.config.TailerBoxConstants;
import com.tailerbox.backend.intra.data.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = TailerBoxConstants.API_BASE_PATH + "/users")
@Validated
public class UserController {

    @Validated
    @PostMapping
    public ResponseEntity<HashMap<String, Object>> createUser(
            @RequestBody @Valid UserDto userDto,
            BindingResult bindingResult
    ) {

        List<ObjectError> errors = bindingResult.getAllErrors();
        HashMap<String, Object> user = new HashMap<>();
        user.put("id", 12L);
        WebMvcLinkBuilder listLink = WebMvcLinkBuilder.linkTo(UserController.class);
        WebMvcLinkBuilder selfLink = listLink.slash(user.get("id"));

        return ResponseEntity
                .created(selfLink.toUri())
                .body(user);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, ArrayList<String>>> constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        Map<String, ArrayList<String>> errors = new HashMap<>();

        if(constraintViolations != null) {
            for(ConstraintViolation<?> c : constraintViolations) {
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

    @GetMapping
    public String listUsers() {
        return "asdf";
    }

    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable("id") Long id) {
        return null;
    }

    @PutMapping(value = "/{id}")
    public String updateUser(@PathVariable("id") Long id) {
        return null;
    }

    @PatchMapping(value = "/{id}")
    public String patchUser(@PathVariable("id") Long id) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        return null;
    }

}
