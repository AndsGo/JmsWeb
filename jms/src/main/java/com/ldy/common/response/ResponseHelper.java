package com.ldy.common.response;

import java.util.List;
import java.util.Map;

/**
 * 描述：返回集工具类
 */
public class ResponseHelper {
    public static final String OK = "ok";
    public static final String FAIL = "fail";

    private ResponseHelper() {
    }

    public static Response buildOk() {
        return new MsgResponse("ok");
    }


    public static Response buildOk(String msg) {
        return build("ok", (Object)null, msg);
    }

    public static Response buildOk(Object result) {
        return build("ok", result, (String)null);
    }

    private static Response build(String succ, Object result, String msg) {
        //I18nResponseUtil.checkAndSetting(result);
        Object resp;
        if(result instanceof List) {
            resp = new ListResponse();
        } else if(result instanceof Map) {
            resp = new MapResponse();
        } else if(result instanceof String) {
            resp = new MsgResponse();
        } else {
            resp = new ObjectResponse();
        }

        ((Response)resp).setResult(result);
        ((Response)resp).setSucc(succ);
        ((Response)resp).setMsg(msg);
        return (Response)resp;
    }

    public static Response buildFail(String msg) {
        return build("fail", (Object)null, msg);
    }

    public static Response buildFail(Object result, String msg) {
        return build("fail", result, msg);
    }
}
