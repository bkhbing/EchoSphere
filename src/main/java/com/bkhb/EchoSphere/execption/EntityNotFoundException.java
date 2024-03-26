package com.bkhb.EchoSphere.execption;

import org.springframework.util.StringUtils;

/**
 * 实体不存在
 *
 * @author bkhb
 * @version 1.0
 * @date 2024/3/26 23:15
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " does not exist";
    }
}
