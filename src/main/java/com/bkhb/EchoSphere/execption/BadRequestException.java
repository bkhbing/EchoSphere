package com.bkhb.EchoSphere.execption;

import com.bkhb.EchoSphere.result.BaseResultCodeEnum;
import lombok.Getter;


/**
 * 通用异常
 *
 * @author bkhb
 * @version 1.0
 * @date 2024/3/26 23:08
 */
@Getter
public class BadRequestException extends RuntimeException{

    private Integer status = BaseResultCodeEnum.BAD_REQUEST.getCode();

    public BadRequestException(String msg){
        super(msg);
    }

    public BadRequestException(BaseResultCodeEnum resultCode){
        super(resultCode.getMessage());
        this.status = resultCode.getCode();
    }
}
