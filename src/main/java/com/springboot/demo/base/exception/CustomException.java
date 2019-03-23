package com.springboot.demo.base.exception;

/**
 * 异常封装类
 */
public class CustomException extends Exception {
    private int httpCode;  //对应http返回码
    private int code;  //异常对应的返回码
    private String message;  //异常对应的描述信息

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public CustomException(int httpCode, int code, String message) {
        super();
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
