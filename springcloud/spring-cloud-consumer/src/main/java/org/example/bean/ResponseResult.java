package org.example.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    private int errorCode;

    public ResponseResult() {}

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    //全局异常处理专用
    public static <T> ResponseResult<T> of(int resultCode, int errorCode, String errorMsg) {
        ResponseResult<T> response = new ResponseResult<>();
        response.setCode(resultCode);
        response.setErrorCode(errorCode);
        response.setMsg(errorMsg);
        return response;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T>ResponseResult ok(T data) {
        return new ResponseResult(ReturnMessage.SUCCESE, ReturnMessage.SUCCESE_MESSAGE, data);
    }

    public static <T>ResponseResult ok() {
        return new ResponseResult(ReturnMessage.SUCCESE, ReturnMessage.SUCCESE_MESSAGE);
    }

    public static <T>ResponseResult ok(T data, String message) {
        return new ResponseResult(ReturnMessage.SUCCESE, message, data);
    }

    public static <T>ResponseResult of() {
        ResponseResult res = new ResponseResult();
        res.setCode(ReturnMessage.FAIL);
        return res;
    }

    public static <T>ResponseResult of(T data) {
        return new ResponseResult(ReturnMessage.FAIL, ReturnMessage.FAIL_MESSAGE, data);
    }

    public static <T>ResponseResult of(T data, String message) {
        return new ResponseResult(ReturnMessage.FAIL, message, data);
    }

    public static <T>ResponseResult of(int code, String message) {
        return new ResponseResult(code, message, null);
    }
}
