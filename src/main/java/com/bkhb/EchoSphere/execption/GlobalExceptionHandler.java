package com.bkhb.EchoSphere.execption;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.hutool.core.exceptions.ExceptionUtil;
import com.bkhb.EchoSphere.result.BaseResultCodeEnum;
import com.bkhb.EchoSphere.result.IResultCode;
import com.bkhb.EchoSphere.result.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常拦截
 *
 * @author bkhb
 * @version 1.0
 * @date 2024/3/26 23:15
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResultWrapper<IResultCode> handleException(Throwable e){
        // 打印堆栈信息
        log.error(ExceptionUtil.stacktraceToString(e));
        return ResultWrapper.fail(BaseResultCodeEnum.SYSTEM_ERROR);
    }
    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResultWrapper<IResultCode> badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ExceptionUtil.stacktraceToString(e));
        return ResultWrapper.fail(e.getStatus(),e.getMessage());
    }

    /**
     * 处理 EntityExist
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityExistException.class)
    public ResultWrapper<IResultCode> entityExistException(EntityExistException e) {
        // 打印堆栈信息
        log.error(ExceptionUtil.stacktraceToString(e));
        return ResultWrapper.fail(BaseResultCodeEnum.RESOURCE_ALREADY_EXIST);
    }

    /**
     * 处理 EntityNotFound
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResultWrapper<IResultCode> entityNotFoundException(EntityNotFoundException e) {
        // 打印堆栈信息
        log.error(ExceptionUtil.stacktraceToString(e));
        return ResultWrapper.fail(BaseResultCodeEnum.RESOURCE_NOT_FOUND);
    }

    /**
     * 处理 NotLoginException
     * @param e
     * @return
     */
    @ExceptionHandler(value = NotLoginException.class)
    public ResultWrapper<IResultCode> notLoginException(NotLoginException e) {
        // 打印堆栈信息
        log.error(ExceptionUtil.stacktraceToString(e));
        return ResultWrapper.fail(BaseResultCodeEnum.TOKEN_FAIL);
    }

    /**
     * 处理 NotPermissionException
     * @param e
     * @return
     */
    @ExceptionHandler(value = NotPermissionException.class)
    public ResultWrapper<IResultCode> notPermissionException(NotPermissionException e) {
        // 打印堆栈信息
        log.error(ExceptionUtil.stacktraceToString(e));
        return ResultWrapper.fail(BaseResultCodeEnum.NO_OPERATE_PERMISSION);
    }

    /**
     * 处理 NotPermissionException
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResultWrapper<IResultCode> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        // 打印堆栈信息
        log.error(ExceptionUtil.stacktraceToString(e));
        return ResultWrapper.fail(BaseResultCodeEnum.ILLEGAL_ARGUMENT);
    }
}
