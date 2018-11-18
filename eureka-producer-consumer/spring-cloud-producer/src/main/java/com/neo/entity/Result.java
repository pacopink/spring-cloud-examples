package com.neo.entity;

public class Result {
    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;
    public static final String MESSAGE_OK="OK";
    private int code;
    private String message;
    private Object extra;

    public Result(int code, String message, Object extra) {
        this.code = code;
        this.message = message;
        this.extra = extra;
    }

    public static Result makeSuccessResult(Object extra) {
        return new Result(SUCCESS, MESSAGE_OK, extra);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }


}
