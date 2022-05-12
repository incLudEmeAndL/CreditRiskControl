package com.chenwei.csust.entity;

public class ApiResult<T> {
    /**
     * 状态码 请求成功返回200
     * 错误返回其他
     */
    private int code;

    /**
     * 返回错误信息
     */
    private String message;

    /**
     * 返回成功响应数据
     */
    private T data;

    public ApiResult() {
    }

    public ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
