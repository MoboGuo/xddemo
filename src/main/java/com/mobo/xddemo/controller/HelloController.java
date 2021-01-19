package com.mobo.xddemo.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import com.mobo.xddemo.domain.JsonData;
import com.mobo.xddemo.domain.MessageXSend;
import com.mobo.xddemo.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.poi.excel.ExcelWriter;

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
        return JsonData.buildSuccess(null, "hello, world! 测试一下自动部署34");
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



    @GetMapping("/downloadExcel")
    public void downLoadFile(HttpServletResponse response) {
        List<User> employees = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            employees.add(new User(i + "18", "a" + i));
//        }

        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("age", "年龄");
        writer.merge(1, "员工信息表");
        writer.write(employees, true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");

        response.setHeader("Content-Disposition", "attachment;filename=" + "test.xls");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        IoUtil.close(out);
    }


}
