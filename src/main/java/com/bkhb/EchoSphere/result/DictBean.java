package com.bkhb.EchoSphere.result;

import lombok.Data;

/**
 * 字典bean
 * 只有code和text，可用于展示下拉框
 *
 * @author bkhb
 * @version 1.0
 * @date 2024/3/26 22:36
 */
@Data
public class DictBean<T> implements IDict<T> {
    private final T code;
    private final String text;
}
