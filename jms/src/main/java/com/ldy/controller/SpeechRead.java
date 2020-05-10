package com.ldy.controller;

import com.baidu.speech.restapi.common.ConnUtil;
import com.baidu.speech.restapi.common.DemoException;
import com.baidu.speech.restapi.common.TokenHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController("/speech")
public class SpeechRead {
    public static void main(String[] args) {
        String[] split = "aa.ss?sss!ff;kk。aa。ss？sss！ff；kk".split("\\.|\\?|\\!|\\;|\\。|\\？|\\！|\\；");
        for (int i = 0; i <split.length ; i++) {
            System.out.println(split[i]);
        }

    }
    //  填写网页上申请的appkey 如 $apiKey="g8eBUMSokVB1BHGmgxxxxxx"
    private final String appKey = "ZGOXrlcShnMHrmNmnWdb9gkI";

    // 填写网页上申请的APP SECRET 如 $secretKey="94dc99566550d87f8fa8ece112xxxxx"
    private final String secretKey = "8a73f83edfd238a9546662c4aa9db2ce";


    // text 的内容为"欢迎使用百度语音合成"的urlencode,utf-8 编码
    // 可以百度搜索"urlencode"
    // private final String text = "欢迎使用百度语音";

    // 发音人选择, 0为普通女声，1为普通男生，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
    private final int per = 4;
    // 语速，取值0-9，默认为5中语速
    private final int spd = 5;
    // 音调，取值0-9，默认为5中语调
    private final int pit = 5;
    // 音量，取值0-9，默认为5中音量
    private final int vol = 5;

    public final String url = "http://tsn.baidu.com/text2audio"; // 可以使用https

    private String cuid = "11329294";

    @RequestMapping(value = "/read",method = RequestMethod.GET)
    @ResponseBody
    private void run(@RequestParam("text") String text, HttpServletResponse response) throws Exception ,IOException, DemoException {
        if(StringUtils.isEmpty(text)){
            System.err.println("没有检测到语音内容");
            return;
        }
        System.out.println(text);
        String[] texts = text.split("\\.|\\?|\\!|\\;|\\。|\\？|\\！|\\；");

        ServletOutputStream outputStream = response.getOutputStream();
        for (int i=0;i<texts.length;i++){
            text=texts[i];
            if(StringUtils.isEmpty(text)){
                continue;
            }
            TokenHolder holder = new TokenHolder(appKey, secretKey, TokenHolder.ASR_SCOPE);
            holder.resfresh();
            String token = holder.getToken();

            String url2 = url + "?tex=" + ConnUtil.urlEncode(text);
            url2 += "&per=" + per;
            url2 += "&spd=" + spd;
            url2 += "&pit=" + pit;
            url2 += "&vol=" + vol;
            url2 += "&cuid=" + cuid;
            url2 += "&tok=" + token;
            url2 += "&lan=zh&ctp=1";
            //System.out.println(url2); // 反馈请带上此url，浏览器上可以测试
            HttpURLConnection conn = (HttpURLConnection) new URL(url2).openConnection();
            conn.setConnectTimeout(5000);
            String contentType = conn.getContentType();
            if (contentType.contains("mp3")) {
                byte[] bytes = ConnUtil.getResponseBytes(conn);

                outputStream.write(bytes);

//            File file = new File("result.mp3"); // 打开mp3文件即可播放
//            // System.out.println( file.getAbsolutePath());
//            FileOutputStream os = new FileOutputStream(file);
//            os.write(bytes);
//            os.close();
//            System.out.println("mp3 file write to " + file.getAbsolutePath());
            } else {
                System.err.println("ERROR: content-type= " + contentType);
                String res  = ConnUtil.getResponseString(conn);
                System.err.println(res);
                outputStream.close();
            }
        }
        outputStream.close();
    }
}
