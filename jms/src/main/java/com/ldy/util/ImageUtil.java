package com.ldy.util;

import com.mysql.jdbc.log.Log;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Collections;
import java.util.Date;

/**
 * Created by 80002946 on 2019/1/11.
 * 图片操作工具类
 */

public class ImageUtil {
    private ImageUtil(){
    }
    
    public static void copyFileRename(InputStream in, String fileName,String myFilePath) throws IOException {
        FileOutputStream fs = new FileOutputStream(myFilePath + fileName);
        byte[] buffer = new byte[1024 * 1024];
        int byteread = 0;
        while ((byteread = in.read(buffer)) != -1) {
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        fs.close();
        in.close();
    }

    //压缩照片
    public static Boolean compressImage(MultipartFile source_file, String target_path) throws IOException {
        try {
            int maxWidth = 1560;//限制最大宽
            int maxHeight = 2100;//限制最大高
            //获得文件源
            InputStream ins = source_file.getInputStream();
            File file = new File(source_file.getOriginalFilename());
            inputStreamToFile(ins, file);
            Image img = ImageIO.read(file);
            int originWidth = img.getWidth(null);
            int originHeight = img.getHeight(null);
            int targetWidth = originWidth;//目标宽
            int targetHeight = originHeight;//目标高
            //宽或者高超过最大上限时进行压缩
            if (originWidth > maxWidth || originHeight > maxHeight) {
                if(originWidth >= originHeight){//横图或方图
                    targetWidth = maxWidth;
                    targetHeight = (int) Math.round(maxWidth * (double)originHeight / (double)originWidth);
                }else{//竖图
                    targetHeight = maxHeight;
                    targetWidth = (int) Math.round(maxHeight * (double)originWidth / (double)originHeight);
                }
            }
            BufferedImage tag = new BufferedImage(targetWidth,targetHeight,BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(img,0,0,targetWidth,targetHeight,null);
            FileOutputStream out = new FileOutputStream(target_path);
            //JPEGImageEncoder可适用于其他图片的类型的转换
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam encoder_param = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            encoder_param.setQuality(0.85f, true);//质量压缩,范围为0.1-1之间,若压缩尺寸过小,建议压缩质量设为最高1f
            encoder.setJPEGEncodeParam(encoder_param);
            encoder.encode(tag);
            out.close();
            FileInputStream fileInputStream = new FileInputStream(target_path);
            int bytesRead = 0;
            byte[] result=new byte[0];
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                result= ArrayUtils.addAll(result,buffer);
            }
            //删除临时文件
            file.delete();
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
