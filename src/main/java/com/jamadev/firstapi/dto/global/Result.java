package com.jamadev.firstapi.dto.global;

import lombok.Getter;
import lombok.Setter;

public class Result<T> {
    @Getter
    @Setter
    private boolean success;

    @Setter
    @Getter
    private Object errors;

    @Setter
    @Getter
    private String message;

    @Setter
    @Getter
    private T data;

    @Getter
    @Setter
    private int status;

    @Setter
    @Getter
    private String version;

    @Setter
    @Getter
    private static String API_VERSION;


    public Result() {
        this.version = API_VERSION;
    }

    public Result(boolean success, String message, T data, Object errors, int status) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.status = status;
        this.version = API_VERSION;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(true, "Operation successful", data, null, 200);
    }

    public static <T> Result<T> failure(String message, int status) {
        return new Result<T>(false, message, null, null, status);
    }
}
