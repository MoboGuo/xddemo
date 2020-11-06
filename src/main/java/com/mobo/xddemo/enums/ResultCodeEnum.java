package com.mobo.xddemo.enums;

public enum ResultCodeEnum {
    /**
     * 操作成功
     */
    RESULT_SUCCESS(1, "操作成功"),
    /**
     * 操作失败
     */
    RESULT_FAILURE(0, "操作失败");

    private Integer code;
    private String value;

    ResultCodeEnum(int code, String value) {
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
