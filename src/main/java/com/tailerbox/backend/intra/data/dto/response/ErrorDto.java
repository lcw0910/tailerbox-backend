package com.tailerbox.backend.intra.data.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tailerbox.backend.http.ResultCode;
import lombok.*;

import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto extends BaseResponseDto{

    private HashMap<String, Object> errors = new HashMap<>();

    public ErrorDto() {
        this.setMessage(ResultCode.FAILED.message());
    }

    public ErrorDto(HashMap<String, Object> errors) {
        this.errors = errors;
        this.setMessage(ResultCode.FAILED.message());
    }

    public ErrorDto(HashMap<String, Object> errors, ResultCode resultCode) {
        this.errors = errors;
        this.setMessage(resultCode.message());
    }

    public void setErrors(HashMap<String, Object> errors) {
        this.errors = errors;
    }

    public void putError(String key, Object value) {
        this.errors.put(key, value);
    }

    public HashMap<String, Object> getErrors() {
        if (this.errors.size() == 0) {
            return null;
        }
        return this.errors;
    }

}
