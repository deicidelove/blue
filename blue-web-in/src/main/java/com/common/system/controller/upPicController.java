/**
 * 
 */
package com.common.system.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.common.system.util.PicUtil;

/**
 * @author amkong
 *
 */
@Controller
public class upPicController {
	
//	@RequestMapping("upJson")
//	@ResponseBody
//	public String upJson(HttpServletRequest request) throws ParseException{
//		 
//				String path = PicUtil.upFile(file);
//				String config =
//			             "{\n" +
//			             "            \"state\": \"SUCCESS\",\n" +
//			             "                \"url\": \""+path+"\",\n" +
//			             "                \"title\": \"path\",\n" +
//			             "                \"original\": \"path\"\n" +
//			             "        }";
//			     return config;
//	}
//	
	@RequestMapping("upPic")
	@ResponseBody
	public String upPic(@RequestParam("upfile") MultipartFile file) throws ParseException{
		 
				String path = PicUtil.upFile(file);
				String config =
			             "{\n" +
			             "            \"state\": \"SUCCESS\",\n" +
			             "                \"url\": \""+path+"\",\n" +
			             "                \"title\": \"path\",\n" +
			             "                \"original\": \"path\"\n" +
			             "        }";
			     return config;
	}

}
