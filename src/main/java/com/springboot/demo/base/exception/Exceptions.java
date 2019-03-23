package com.springboot.demo.base.exception;

public class Exceptions extends Exception {
    public static final CustomException LOGIN_FAILED = new CustomException(503, -1, "LDAP server connect fail!");
}
