package com.bkhb.EchoSphere.execption;

import org.springframework.util.StringUtils;

/**
 * 实体已存在
 *
 * @author bkhb
 * @version 1.0
 * @date 2024/3/26 23:15
 */
public class EntityExistException extends RuntimeException {

    public EntityExistException(Class clazz, String field, String val) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " existed";
    }
}
