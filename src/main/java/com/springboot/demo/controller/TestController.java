package com.springboot.demo.controller;

import com.springboot.demo.base.bean.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : LIUBOLUN
 * @date : 2019-03-23
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public Result test() {
        return Result.success();
    }

}
