package com.ldy.common.exception;

import java.io.Serializable;

/**
 * 描述：异常code定义
 */
public class LdyErrorCode implements Serializable {
    public static final LdyErrorCode NOT_FOUND = new LdyErrorCode("404", "资源未找到！");

    public static final LdyErrorCode SERVER_ERROR = new LdyErrorCode("500", "服务器内部错误！");

    public static final LdyErrorCode BAD_REQUEST = new LdyErrorCode("400", "非法请求！");

    public static final LdyErrorCode ILLEGAL_ARGUMENTS = new LdyErrorCode("4000001", "参数非法");

    public static final LdyErrorCode DELETE_FORBIDDEN = new LdyErrorCode("4000002", "禁止删除资源");

    public static final LdyErrorCode SOURCE_EXISTS = new LdyErrorCode("4000003", "资源已存在");

    private String code;

    private String message;

    public LdyErrorCode(String code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
}
