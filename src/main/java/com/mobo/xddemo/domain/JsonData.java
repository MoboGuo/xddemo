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
public class JsonData implements Serializable {

    /**
     * 状态码 0失败  1成功
     */
    private Integer code;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 描述信息
     */
    private String msg;

    public JsonData() {
    }

    public JsonData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 成功，传入数据
     * @return
     */
    public static JsonData buildSuccess() {
        return new JsonData(ResultCodeEnum.RESULT_SUCCESS.getCode(), null, null);
    }

    /**
     * 成功，传入数据
     * @param data 数据
     * @return
     */
    public static JsonData buildSuccess(Object data) {
        return new JsonData(ResultCodeEnum.RESULT_SUCCESS.getCode(), data, null);
    }

    public static JsonData buildSuccess(String msg) {
        return new JsonData(ResultCodeEnum.RESULT_SUCCESS.getCode(), null, msg);
    }

    /**
     * 失败，传入描述信息
     * @param msg 通知信息
     * @return
     */
    public static JsonData buildError(String msg) {
        return new JsonData(ResultCodeEnum.RESULT_FAILURE.getCode(), null, msg);
    }

    /**
     * 失败，传入描述信息,状态码
     * @param msg 通知信息
     * @param code 码值
     * @return
     */
    public static JsonData buildError(String msg, Integer code) {
        return new JsonData(code, null, msg);
    }

    /**
     * 成功，传入数据,及描述信息
     * @param data 数据
     * @param msg 通知信息
     * @return
     */
    public static JsonData buildSuccess(Object data, String msg) {
        return new JsonData(ResultCodeEnum.RESULT_SUCCESS.getCode(), data, msg);
    }

    /**
     * 成功，传入数据,及状态码
     * @param data 数据
     * @param code 返回信息
     * @return
     */
    public static JsonData buildSuccess(Object data, int code) {
        return new JsonData(code, data, null);
    }
}
