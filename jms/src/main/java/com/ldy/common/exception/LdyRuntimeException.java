package com.ldy.common.exception;

import com.ldy.common.enums.LdyErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：自定义异常类
 */
public class LdyRuntimeException extends RuntimeException{
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());

    private String code;

    private String message;

    public LdyRuntimeException() {}
    public LdyRuntimeException(Throwable throwable) {
        super(throwable);
    }
    public LdyRuntimeException(String message) {
        super(message);
        this.message = message;
    }
    public LdyRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }
    public LdyRuntimeException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public LdyRuntimeException(LdyErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
