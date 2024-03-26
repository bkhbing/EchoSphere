package com.bkhb.EchoSphere.result;

import java.lang.annotation.*;

/**
 * 不装饰controller相应
 *
 * @author bkhb
 * @version 1.0
 * @date 2024/3/26 22:57
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface IgnoredResultWrapper {
}
