package com.bkhb.EchoSphere.result;

/**
 * @author bkhb
 * @version 1.0
 * @date 2024/3/26 22:33
 */
public interface IResultCode {
    /**
     * 返回的错误码.
     *
     * @return Integer
     */
    Integer getCode();

    /**
     * 返回的错误信息.
     *
     * @return String
     */
    String getMessage();
}