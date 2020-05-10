package com.ldy.common.response;

/**
 * 描述：数据返回集接口
 */
public interface Response {
    String getSucc();

    void setSucc(String var1);

    String getMsg();

    void setMsg(String var1);

    Object getResult();

    void setResult(Object var1);
}
