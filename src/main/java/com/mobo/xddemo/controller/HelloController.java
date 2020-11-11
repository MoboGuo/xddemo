package com.mobo.xddemo.controller;

import com.mobo.xddemo.domain.JsonData;
import com.mobo.xddemo.domain.XdException;
import com.submail.config.AppConfig;
import com.submail.lib.MESSAGEXsend;
import com.submail.utils.ConfigLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@PropertySource({"classpath:xddemo.properties"})
public class HelloController {

    @Value("${message.submail.appid}")
    private String appId;
    @Value("${message.submail.appkey}")
    private String appKey;
    @Value("${message.submail.signType}")
    private String signType;

    @GetMapping("/hello")
    public JsonData helloTest(){
        return JsonData.buildSuccess(null, "hello, world!");
    }

    @GetMapping("/exception")
    public JsonData exceptionTest(){
        int i = 1/0;
        return JsonData.buildSuccess(null, "excetionTest");
    }

    @PostMapping("/message")
    public JsonData testSendMessage(){
        AppConfig appConfig = ConfigLoader.createConfig(appId, appKey, signType);
        MESSAGEXsend submail = new MESSAGEXsend(appConfig);
        submail.addTo("13623801642");
        submail.setProject("YGZuD");
//        submail.addVar("code", "张三");
        String response= null;
        try {
            response = submail.xsend();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("接口返回数据：" + response);
        return JsonData.buildSuccess(response, "访问成功");
    }
}
