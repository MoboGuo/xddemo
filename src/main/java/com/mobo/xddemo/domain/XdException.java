package com.mobo.xddemo.domain;

/**
 * 自定义异常类
 *
 * @author Mobo
 * @create 2020-11-10-11:19
 */
public class XdException extends RuntimeException {

    /**
     * 异常代码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;

    public XdException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
