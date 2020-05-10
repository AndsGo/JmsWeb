package com.ldy.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by 80002946 on 2018/5/20.
 */
@RestController
public class hello {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        return  "hello springBoot";
    }
//    @RequestMapping("/uploadTest")
//    @ResponseBody
//    public Map<String,Object> test(@RequestParam(value="file", required = false) MultipartFile file, HttpServletRequest request) {
//        String name =file.getOriginalFilename();
//
//        HashMap<String, Object> obj = new HashMap<String,Object>();
//        try {
//            //file.transferTo(new File("/nfsc/EOS_AESP_OWSP/demo"+name));
//            file.transferTo(new File("D://"+name));
//            obj.put("success", true);
//            obj.put("pictureUrl", "D://"+name);
//            //obj.put("pictureUrl", "/nfsc/EOS_AESP_OWSP/demo"+name);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            obj.put("success", false);
//            e.printStackTrace();
//        }
//        return obj;
//    }
//    /**
//     * 输出文件到页面
//     * @param response
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.GET,value="/writeFileToView")
//    public void writeFileToView(@RequestParam("path") String path, HttpServletResponse response){
//        try {
//            //读文件
//            FileInputStream fis = new FileInputStream(path);
//            int i = fis.available();
//            byte[] data = new byte[i];
//            fis.read(data);
//            fis.close();
//
//            String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
//            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
//            String contentType = "application/octet-stream";
//            if ("jpg".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix)) {
//                contentType = "image/jpeg";
//            } else if ("png".equalsIgnoreCase(suffix)) {
//                contentType = "image/png";
//            } else if ("aac".equalsIgnoreCase(suffix)) {
//                contentType = "audio/aac";
//            } else if("mp3".equalsIgnoreCase(suffix)){
//                contentType = "audio/x-mpeg";
//            } else if("wav".equalsIgnoreCase(suffix)){
//                contentType = "audio/x-wav";
//            }
//            response.setContentType(contentType);
//            response.setCharacterEncoding("utf-8");
//            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
//            response.addHeader("Content-Transfer-Encoding", "binary");
//
//            //写文件
//            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
//            outputStream.write(data);
//            outputStream.flush();
//            outputStream.close();
//        } catch (Exception e) {
//
//        }
//    }

}
