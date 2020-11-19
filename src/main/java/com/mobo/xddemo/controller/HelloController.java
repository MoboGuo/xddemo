package com.mobo.xddemo.controller;

import com.mobo.xddemo.domain.JsonData;
import com.mobo.xddemo.domain.MessageXSend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * 测试
 *
 * @author Mobo
 * @create 2020-11-06-17:09
 */
@RestController
@RequestMapping("v1/test")
public class HelloController {

    @Resource
    private MessageXSend messageXSend;

    @GetMapping("/hello")
    public JsonData helloTest(){
        return JsonData.buildSuccess(null, "hello, world! 测试一下自动部署");
    }

    @GetMapping("/exception")
    public JsonData exceptionTest(){
        int i = 1/0;
        return JsonData.buildSuccess(null, "excetionTest");
    }

    @PostMapping("/message")
    public JsonData testSendMessage(){
        messageXSend.addTo("13623801642");
        messageXSend.setProject("YGZuD");
        String response= null;
        try {
            response = messageXSend.xsend();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("接口返回数据：" + response);
        return JsonData.buildSuccess(response, "访问成功");
    }
}
