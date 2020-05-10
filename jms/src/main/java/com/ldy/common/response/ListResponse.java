package com.ldy.common.response;

import java.util.List;

/**
 * 描述：返回list集
 */
public class ListResponse implements Response {
    private List result;
    private String succ;
    private String msg;

    public ListResponse() {
    }

    public List getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = (List)result;
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
}
