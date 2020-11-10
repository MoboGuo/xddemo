package com.mobo.xddemo.handler;

import com.mobo.xddemo.domain.JsonData;
import com.mobo.xddemo.domain.XdException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理控制器
 *
 * @author Mobo
 * @create 2020-11-10-11:36
 */
@RestControllerAdvice
public class XdExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    Object handlerException(Exception e, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error.html");
        modelAndView.addObject("msg", e.getMessage());
        return modelAndView;
    }
}
