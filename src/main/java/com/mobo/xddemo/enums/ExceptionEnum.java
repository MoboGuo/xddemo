package com.mobo.xddemo.enums;

/**
 * 自定义异常码值
 *
 * @author Mobo
 * @create 2020-12-02-17:52
 */
public class ExceptionEnum {


    private Integer code;
    private String message;

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
