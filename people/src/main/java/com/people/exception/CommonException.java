package com.people.exception;

import java.util.ArrayList;
import java.util.List;

public class CommonException extends RuntimeException {

    private int code;
    private String message;
    private Object data;

    public CommonException(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        List<Object> d = new ArrayList<>();
        d.add(data);
        this.data = d;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
