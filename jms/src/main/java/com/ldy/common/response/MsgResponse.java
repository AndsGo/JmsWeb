package com.ldy.common.response;

/**
 * 描述：msg数据返回集
 */
public class MsgResponse implements Response {
    private String result;
    private String succ;
    private String msg;

    public MsgResponse() {
        this.succ = "";
    }

    public MsgResponse(String succ) {
        this.succ = succ;
    }

    public String getResult() {
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
        this.result = result.toString();
    }
}
