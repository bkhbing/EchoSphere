package com.bkhb.EchoSphere.result;

/**
 * TODO
 *
 * @author bkhb
 * @version 1.0
 * @date 2024/3/26 22:34
 */
public enum BaseResultCodeEnum implements IResultCode, IDict<Integer> {

    /**
     * 执行成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 错误的请求
     */
    BAD_REQUEST(400, "请求错误"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR(500, "系统异常"),

    /**
     * 外部接口调用异常
     */
    INTERFACE_SYSTEM_ERROR(1001, "外部接口调用异常"),

    /**
     * 业务连接处理超时
     */
    CONNECT_TIME_OUT(1002, "系统超时"),

    /**
     * 参数为空
     */
    NULL_ARGUMENT(1003, "参数为空"),

    /**
     * 非法参数
     */
    ILLEGAL_ARGUMENT(1004, "参数不合法"),

    /**
     * 非法请求
     */
    ILLEGAL_REQUEST(1005, "非法请求"),

    /**
     * 非法配置
     */
    ILLEGAL_CONFIGURATION(1006, "配置不合法"),

    /**
     * 非法状态
     */
    ILLEGAL_STATE(1007, "状态不合法"),

    /**
     * 错误的枚举编码
     */
    ENUM_CODE_ERROR(1008, "错误的枚举编码"),

    /**
     * 逻辑错误
     */
    LOGIC_ERROR(1009, "逻辑错误"),

    /**
     * 并发异常
     */
    CONCURRENT_ERROR(1010, "并发异常"),

    /**
     * 非法操作
     */
    ILLEGAL_OPERATION(1011, "非法操作"),

    /**
     * 重复操作
     */
    REPETITIVE_OPERATION(1012, "重复操作"),

    /**
     * 无操作权限
     */
    NO_OPERATE_PERMISSION(1013, "无操作权限"),

    /**
     * 资源不存在
     */
    RESOURCE_NOT_FOUND(1014, "资源不存在"),

    /**
     * 资源已存在
     */
    RESOURCE_ALREADY_EXIST(1015, "资源已存在"),

    /**
     * 类型不匹配
     */
    TYPE_UN_MATCH(1016, "类型不匹配"),

    /**
     * 文件不存在
     */
    FILE_NOT_EXIST(1017, "文件不存在"),

    /**
     * 请求被限流
     */
    LIMIT_BLOCK(1018, "请求限流阻断"),

    /**
     * token 失效
     */
    TOKEN_EXPIRE(1019, "token过期"),

    /**
     * 业务处理异常
     */
    BIZ_ERROR(1020, "业务处理异常"),

    /**
     * token
     */
    TOKEN_FAIL(1021, "token_fail"),

    /**
     * request
     */
    REQUEST_EXCEPTION(1022, "request_exception"),

    /**
     * 接口限流降级
     */
    BLOCK_EXCEPTION(1023, "接口限流降级"),

    /**
     * 用户名或密码错误
     */
    USERNAME_OR_PASSWORD_ERROR(1024, "用户名或密码错误"),
    USER_DISABLED(1025, "用户未激活"),
    ;


    /**
     * 枚举编号
     */
    private final Integer code;

    /**
     * 枚举详情
     */
    private final String message;


    /**
     * 构造方法
     *
     * @param code    枚举编号
     * @param message 枚举详情
     */
    BaseResultCodeEnum(Integer code, String message) {
        init(code, message);
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
