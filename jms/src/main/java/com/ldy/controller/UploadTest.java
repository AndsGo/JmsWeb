package com.ldy.controller;

import com.alibaba.fastjson.JSONObject;
import com.ldy.common.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 01115490 on 2018/5/28.
 */
@RequestMapping("uploadTest")
@Controller
public class UploadTest {

	Logger logger = LoggerFactory.getLogger(UploadTest.class);

	/**
	 * 输出文件到页面
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value="/writeFileToView")
	public void writeFileToView(@RequestParam("path") String path, HttpServletResponse response){
		try {
			//读文件
			FileInputStream fis = new FileInputStream(path);
			int i = fis.available();
			byte[] data = new byte[i];
			fis.read(data);
			fis.close();

			String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			String contentType = "application/octet-stream";
			if ("jpg".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix)) {
				contentType = "image/jpeg";
			} else if ("png".equalsIgnoreCase(suffix)) {
				contentType = "image/png";
			} else if ("aac".equalsIgnoreCase(suffix)) {
				contentType = "audio/aac";
			} else if("mp3".equalsIgnoreCase(suffix)){
				contentType = "audio/x-mpeg";
			} else if("wav".equalsIgnoreCase(suffix)){
				contentType = "audio/x-wav";
			}
			response.setContentType(contentType);
			response.setCharacterEncoding("utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Content-Transfer-Encoding", "binary");

			//写文件
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			logger.error("writeFileToView error", e);
		}
	}



	@RequestMapping("uploadTest")
	@ResponseBody
	public JSONObject test(@RequestParam(value="file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {
		String pathUrl;
		String originalFilename = file.getOriginalFilename();
		String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
		if ("jpg".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix) ||"png".equalsIgnoreCase(suffix)) {
			pathUrl= makePath(file,true);
			logger.info("开始压缩{}",new Date().getTime());
			ImageUtil.compressImage(file,pathUrl);
			logger.info("压缩完成{}",new Date().getTime());
		}else {
			pathUrl= makePath(file,false);
		}

		JSONObject obj = new JSONObject();
		try {
			obj.put("success", true);
			obj.put("pictureUrl", pathUrl);
		} catch (Exception e) {
			obj.put("success", false);
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 上传文件
	 * 如果是图片不进行上传，只返回路径
	 * @param file
	 * @param isImage
     * @return
     */
	public String makePath(MultipartFile file,Boolean isImage){
		try {
			if (file != null) {
				String uploadImageFileName;
				String pathResult = "";

				uploadImageFileName = file.getOriginalFilename();
				String uuid= UUID.randomUUID().toString();
				String fileSaveName = "";
				// 获取后缀名
				if (uploadImageFileName.lastIndexOf(".") >= 0) {
					fileSaveName = uuid + uploadImageFileName.substring(uploadImageFileName.lastIndexOf(".")).replace("-","");
				}
				// 创建父目录
				String basePath = makeFilePath();
				File parentPath = new File(basePath);
				if (!parentPath.exists()) {
					parentPath.mkdirs();
				}
				//如果不是图片
				if(!isImage){
					File filePath = new File(basePath + File.separator +fileSaveName);
					file.transferTo(filePath);
				}
				pathResult = makeFilePath() + File.separator +fileSaveName;

				return pathResult;
			}
		} catch (Exception e) {
			//logger.error("quickReport upload makePicPath{}", e.getMessage());
		}
		return "";
	}
	/**
	 *
     * 创建文件夹
	 */
	public String makeFilePath(){
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		//String rootPath = sysConfigHolder.getKey("quickReportPath");
		String rootPath = "E:\\temp";
		String filePath=rootPath+ File.separator+year+month+day;
		return filePath;
	}

}
