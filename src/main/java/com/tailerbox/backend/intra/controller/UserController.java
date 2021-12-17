package com.tailerbox.backend.intra.controller;

import com.tailerbox.backend.http.ResultCode;
import com.tailerbox.backend.intra.data.dto.UserDto;
import com.tailerbox.backend.intra.data.dto.response.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/users")
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

    @GetMapping
    public ResponseEntity<ErrorDto> listUsers() {
        ErrorDto errorDto = new ErrorDto();
        errorDto.putError("foo", "asdfasdfasdf");
        errorDto.setMessage("this is error message");
        return ResponseEntity.badRequest().body(errorDto);
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
