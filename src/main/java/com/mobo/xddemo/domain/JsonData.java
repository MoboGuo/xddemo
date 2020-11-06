package com.mobo.xddemo.domain;

import com.mobo.xddemo.enums.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 统一返回规范
 *
 * @author Mobo
 * @create 2020-11-06-17:14
 */
@Data
public class JsonData<T> implements Serializable {

    /**
     * 状态码 0失败  1成功
     */
    private Integer code;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 描述信息
     */
    private String msg;

    public JsonData() {
        this.code = ResultCodeEnum.RESULT_SUCCESS.getCode();
        this.data = (T)new HashMap();
    }

    public JsonData(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 自定义返回值  用以临时协议的code
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T>JsonData customizeResult(Integer code, String msg){
        return new JsonData().setCode(code).setMsg(msg);
    }

    public static <T>JsonData customizeResult(Integer code, T data, String msg){
        return new JsonData().setCode(code).setData(data).setMsg(msg);
    }

    /**
     * 返回请求成功的json
     * @param msg
     * @param <T>
     * @return
     */
    public static <T>JsonData successResult(String msg){
        return (new JsonData()).setCode(ResultCodeEnum.RESULT_SUCCESS.getCode()).setMsg(msg);
    }

    public static <T>JsonData successResult(T data, String msg){
        return new JsonData().setCode(ResultCodeEnum.RESULT_SUCCESS.getCode()).setData(data).setMsg(msg);
    }

    /**
     * 返回请求失败的json
     * @param msg
     * @param <T>
     * @return
     */
    public static <T>JsonData failedResult(String msg){
        return new JsonData().setCode(ResultCodeEnum.RESULT_FAILURE.getCode()).setMsg(msg);
    }

    public static <T>JsonData failedResult(T data, String msg){
        return new JsonData().setCode(ResultCodeEnum.RESULT_FAILURE.getCode()).setData(data).setMsg(msg);
    }

    public JsonData<T> setData(T data) {
        if (data != null) {
            this.data = data;
        }
        return this;
    }

    public JsonData<T> setCode(final Integer code) {
        this.code = code;
        return this;
    }

    public JsonData<T> setMsg(final String msg) {
        this.msg = msg;
        return this;
    }

}
