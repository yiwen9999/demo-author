package com.hex.demoauthor.enums;

public enum ResultEnum {
    UN_KNOW_ERROR(-1, "未知错误"),
    ERROR_PARAM(101, "传递的参数错误"),
    OPERATOR_INFO_ERROR(102, "登录信息失效，请重新登录"),
    ERROR_LOGIN(103, "登录失败，请检查登录信息是否正确");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
