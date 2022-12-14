package com.lp.first.framework.system;

import com.lp.first.framework.bean.SystemCode;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @author LP
 * @date 2018/5/1
 */
@ApiModel("控制层返回参数")
public class BaseResp<T> implements Serializable {

    private static final long serialVersionUID = 8175632095680303848L;

    private String code = SystemCode.SUCCESS_CODE;

    private String message = SystemCode.SUCCESS_MESSAGE;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public static <T> BaseResp ok() {
        return new BaseResp();
    }

    public static <T> BaseResp ok(T t) {
        BaseResp<T> baseResp = new BaseResp<>();
        baseResp.setData(t);
        return baseResp;
    }

    public static <T> BaseResp off() {
        BaseResp<T> baseResp = new BaseResp<>();
        baseResp.setCode(SystemCode.SERVER_ERROR_CODE);
        baseResp.setMessage(SystemCode.FAIL_MESSAGE);
        return baseResp;
    }

    public static <T> BaseResp off(T t) {
        BaseResp<T> baseResp = new BaseResp<>();
        baseResp.setData(t);
        baseResp.setCode(SystemCode.SERVER_ERROR_CODE);
        baseResp.setMessage(SystemCode.FAIL_MESSAGE);
        return baseResp;
    }

    public static <T> BaseResp error(String code,String message) {
        BaseResp<T> baseResp = new BaseResp<>();
        baseResp.setCode(code);
        baseResp.setMessage(message);
        return baseResp;
    }
}
