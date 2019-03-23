package com.springboot.demo.base.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.demo.constants.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * api返回结果包装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private int code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success() {
        return new Result(Constant.YES, Constant.SUCCESS_RETURN);
    }

    public static Result success(Object data) {
        return new Result(Constant.YES, Constant.SUCCESS_RETURN, data);
    }

    public static Result fail() {
        return new Result(Constant.NO, Constant.FAIL_RETURN);
    }

}
