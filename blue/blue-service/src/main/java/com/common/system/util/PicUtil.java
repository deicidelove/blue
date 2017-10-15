/**
 * 
 */
package com.common.system.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author amkong
 *
 */
public class PicUtil {
	
	public static String upFile( MultipartFile file){
		String urlTream = "";
		try {
			if (file != null && file.getName() != null && !file.isEmpty()) {
				// 转存文件
				String name = file.getOriginalFilename();
				urlTream = "D:/pics/" + name;
				file.transferTo(new File(urlTream));
				return  urlTream;
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
