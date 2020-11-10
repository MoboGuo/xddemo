package com.mobo.xddemo.controller;

import com.mobo.xddemo.domain.JsonData;
import com.mobo.xddemo.domain.XdException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author Mobo
 * @create 2020-11-06-17:09
 */
@RestController
@RequestMapping("v1/test")
public class HelloController {

    @GetMapping("/hello")
    public JsonData helloTest(){
        return JsonData.buildSuccess(null, "hello, world!");
    }

    @GetMapping("/exception")
    public JsonData exceptionTest(){
        int i = 1/0;
        return JsonData.buildSuccess(null, "excetionTest");
    }
}
