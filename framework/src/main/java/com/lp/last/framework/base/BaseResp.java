package com.lp.last.framework.base;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseResp<T> {

    private Integer code;

    private String msg;

    private T data;

    public static <T>BaseResp ok() {
        return new BaseResp()
                .setCode(RespCode.SUCCESE)
                .setMsg(RespCode.SUCCESE_MESSAGE);
    }

    public static <T>BaseResp ok(T t) {
        return new BaseResp()
                .setCode(RespCode.SUCCESE)
                .setMsg(RespCode.SUCCESE_MESSAGE)
                .setData(t);
    }

    public static <T>BaseResp of() {
        return new BaseResp()
                .setCode(RespCode.FAIL)
                .setMsg(RespCode.FAIL_MESSAGE);
    }

    public static <T>BaseResp of(String message) {
        return new BaseResp()
                .setCode(RespCode.FAIL)
                .setMsg(message);
    }

    public static <T>BaseResp of(int code, String message) {
        return new BaseResp()
                .setCode(code)
                .setMsg(message);
    }
}
