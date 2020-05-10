package com.ldy.controller;

import com.baidu.speech.restapi.common.TransApi;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@RestController("/translate")
public class TranslateController {
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20180612000175393";
    private static final String SECURITY_KEY = "gdiZAZRZ32XnLxpIiPQg";
    public static void main(String[] args) {
//        String[] split = "aa.ss?sss!ff;kk。aa。ss？sss！ff；kk".split("\\.|\\?|\\!|\\;|\\。|\\？|\\！|\\；");
//        for (int i = 0; i <split.length ; i++) {
//            System.out.println(split[i]);
//        }
        try {
            TransApi api = new TransApi(APP_ID, SECURITY_KEY);

            String query = "高度600米";
            System.out.println(api.getTransResult(query, "auto", "en"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("TransApi"+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 翻译到中文
     * @param input
     * @return
     */
    @RequestMapping(value="/getTranslateZH/{input}")
    @ResponseBody
    public String getZH(@PathVariable("input") String input) {
        try {
            TransApi api = new TransApi(APP_ID, SECURITY_KEY);

            String query = input;
            return api.getTransResult(query, "auto", "zh");
        } catch (UnsupportedEncodingException e) {
            System.out.println("TransApi"+e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 翻译到英文
     * @param input
     * @return
     */
    @RequestMapping(value="/getTranslateEN/{input}")
    @ResponseBody
    public String getEN(@PathVariable("input") String input) {
        try {
            TransApi api = new TransApi(APP_ID, SECURITY_KEY);
            String query = input;
            return api.getTransResult(query, "auto", "en");
        } catch (UnsupportedEncodingException e) {
            System.out.println("TransApi"+e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

}
