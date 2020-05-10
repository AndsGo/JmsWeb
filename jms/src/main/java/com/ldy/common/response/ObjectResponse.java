package com.ldy.common.response;

/**
 * 描述：object数据返回集
 */
public class ObjectResponse implements Response{
    private Object result;
    private String succ;
    private String msg;

    public ObjectResponse() {
    }

    public ObjectResponse(String succ) {
        this.succ = succ;
    }

    public Object getResult() {
        return this.result;
    }

    public String getSucc() {
        return this.succ;
    }

    public void setSucc(String succ) {
        this.succ = succ;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
