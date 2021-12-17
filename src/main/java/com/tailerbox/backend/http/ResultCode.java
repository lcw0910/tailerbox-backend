package com.tailerbox.backend.http;

public enum ResultCode {

    INVALID_PARAMS("Invalid Request Parameter"),

    SUCCESS("Success"),

    FAILED( "Failed");


    ResultCode(String message) {
//        this.code = code;
        this.message = message;
    }

//    private final String code;

    private final String message;

    private static final ResultCode[] VALUES;

    static {
        VALUES = values();
    }

    public String value() {
        return this.message;
    }

    public String message() {
        return this.message;
    }

    public String toString() {
        return name();
    }


}
