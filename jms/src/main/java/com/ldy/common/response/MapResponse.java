package com.ldy.common.response;

import java.util.Map;

/**
 * 描述：map数据返回集
 */
public class MapResponse implements Response{
    private Map result;
    private String succ;
    private String msg;

    public MapResponse() {
    }

    public Map getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = (Map)result;
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
