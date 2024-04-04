package com.bkhb.EchoSphere.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum GenderEnum {
    Male(1, "男"),
    Female(2, "女"),
    Unknown(0, "未知");

    @EnumValue
    private final int code;

    @JsonValue
    private final String desc;

    GenderEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}