package com.bkhb.EchoSphere.result;

import lombok.Getter;
import lombok.Setter;

/**
 * 响应数据的统一封装
 *
 * @author bkhb
 * @version 1.0
 * @date 2024/3/26 22:32
 */
@Setter
@Getter
public class ResultWrapper<T> {
    private Integer code;
    private String message;
    private String traceId;
    private T data;

    public ResultWrapper() {
        this(BaseResultCodeEnum.SUCCESS.getCode(), BaseResultCodeEnum.SUCCESS.getMessage());
    }

    public ResultWrapper(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultWrapper(IResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMessage());
    }

    public ResultWrapper(IResultCode resultCode, T data) {
        this(resultCode);
        this.data = data;
    }

    public ResultWrapper(Integer code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    public static <T> ResultWrapper<T> success(T data) {
        ResultWrapper<T> objectDataResult = new ResultWrapper<>();
        objectDataResult.setData(data);
        return objectDataResult;
    }

    public static <T> ResultWrapper<T> success() {
        ResultWrapper<T> objectDataResult = new ResultWrapper<>();
        objectDataResult.setData(null);
        return objectDataResult;
    }

    public static <T> ResultWrapper<T> fail(IResultCode resultCode, T t) {
        return new ResultWrapper<>(resultCode.getCode(), resultCode.getMessage(), t);
    }

    public static <T> ResultWrapper<T> fail(Integer code, String message, T t) {
        return new ResultWrapper<>(code, message, t);
    }

    public static <T> ResultWrapper<T> fail(IResultCode resultCode) {
        return new ResultWrapper<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    public static <T> ResultWrapper<T> fail(Integer code, String message) {
        return new ResultWrapper<>(code, message, null);
    }
}