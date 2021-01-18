package com.mobo.xddemo.handler;

import com.mobo.xddemo.domain.JsonData;
import com.mobo.xddemo.domain.XdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 异常处理控制器
 *
 * @author Mobo
 * @create 2020-11-10-11:36
 */
@RestControllerAdvice
@Slf4j
public class XdExceptionHandler {
    /**
     * 返回固定的错误页面
     * @param e
     * @param request
     * @return
     */
//    @ExceptionHandler(value = Exception.class)
//    Object handlerException(Exception e, HttpServletRequest request) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("error.html");
//        modelAndView.addObject("msg", e.getMessage());
//        return modelAndView;
//    }

    /**
     * 未知错误，返回json
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public JsonData defaultErrorHandler(Exception e)  {
        log.error("记录到一个异常信息:{}",e);
        e.printStackTrace();
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return JsonData.buildError("违反数据库,唯一约束规则");
        }
        if (e instanceof org.springframework.http.converter.HttpMessageNotReadableException) {
            return JsonData.buildError("请求参数为空");
        }
        /**
         * 增加自定义异常捕获
         */
        if(e instanceof XdException){
            return JsonData.buildError(e.getMessage());
        }
        return JsonData.buildError("网络不稳定，请稍后重试哦~");
    }


}
