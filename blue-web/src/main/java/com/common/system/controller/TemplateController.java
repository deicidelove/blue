package com.common.system.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Paths;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.common.system.service.WeiXinService;
import com.common.system.util.StandardJSONResult;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

@Controller
public class TemplateController {
	private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);
	// 合成图片后下载到本地的位置
	private static final String PictureRoot = "picture";
	
	// 微信服务
	@Resource WeiXinService weixinService;
	private final ResourceLoader resourceLoader;  
	
	
	@Autowired  
	public TemplateController(ResourceLoader resourceLoader) {  
		this.resourceLoader = resourceLoader;  
	} 
	
	
	
	/** 根据电话号码获取用户二维码  **/
	@ResponseBody
	@RequestMapping("/getORCode")
	public String getORCode(HttpServletResponse response, String phoneNumber) throws Exception {
		String qrcodeUrl = weixinService.getUserQRCode(phoneNumber);
		return qrcodeUrl;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getCombinePic", method = RequestMethod.POST)
	public String getCombinePic(@RequestParam("file") MultipartFile file, String openId) throws Exception {
		if ( file == null ) {
			return JSON.toJSONString(StandardJSONResult.getFailedInstance("请先选中上传一张图片"));
		} else {
			// 二维码网址
			String qrcodeUrl = weixinService.getUserQRCode(openId);
			// 二维码图片
			BufferedImage qrcodeImg = getImage(qrcodeUrl);
			if ( qrcodeImg != null ) {
				BufferedImage userImg = fileToImage(file.getInputStream());
				if ( userImg == null ) {
					return JSON.toJSONString(StandardJSONResult.getFailedInstance("不是合法图片,请重新上传"));
				} else {
					// 生成空白图片
					BufferedImage blankPic = buildBlankPic(userImg.getWidth(), userImg.getHeight()/4);
					// 缩小二维码
					Integer zoomSize = getZoomSize(blankPic.getWidth(), blankPic.getHeight());
					BufferedImage zoomQRCode = zoomPic(qrcodeImg, zoomSize, zoomSize);
					// 合成图片
					overlapImage(blankPic, zoomQRCode);
					// 拼接图片
					BufferedImage finalImg = combineImages(userImg, blankPic);
					// 写入本地
					String pictureName = openId + ".jpg";
					String localFilePath = Joiner.on(File.separator).join(PictureRoot, pictureName);
					String downloadFilePath = Joiner.on("/").join("picture", pictureName);
					// 先创建父文件夹
					File combinedFile = new File(localFilePath);
					combinedFile.getParentFile().mkdirs();
					ImageIO.write(finalImg, "jpg", new File(localFilePath));
					return JSON.toJSONString(StandardJSONResult.getSuccessInstance(JSON.toJSONString(new PictureFilePath(downloadFilePath, pictureName))));
				}
			} else {
				return JSON.toJSONString(StandardJSONResult.getFailedInstance("在获取用户二维码图片时发生异常"));
			}
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/picture/{fileName:.+}")
	public ResponseEntity<?> showPicture(@PathVariable String fileName) {
		try {  
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get("picture", fileName).toString()));  
        } catch (Exception e) {
        	logger.error("在获取图片" + fileName + "的显示路径时发生异常", e);
            return ResponseEntity.notFound().build();  
        }  
	}
	
	
	class PictureFilePath implements Serializable {
		private static final long serialVersionUID = 1728184257159429532L;
		private String showFilePath;
		private String fileName;
		
		public PictureFilePath(){}
		public PictureFilePath(String showFilePath, String fileName) {
			this.showFilePath = showFilePath;
			this.fileName = fileName;
		}
		public String getShowFilePath() {
			return showFilePath;
		}
		public void setShowFilePath(String showFilePath) {
			this.showFilePath = showFilePath;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		
	}
	
	
	
	private Integer getZoomSize(int width, int height) {
		Integer smallWidth = width/2;
		Integer smallHeight = height/2;
		return smallWidth > smallHeight ? smallHeight : smallWidth;
	}


	private BufferedImage combineImages(BufferedImage userImg, BufferedImage blankPic) {
		BufferedImage imageNew = 
				new BufferedImage(userImg.getWidth(), userImg.getHeight() + blankPic.getHeight(), BufferedImage.TYPE_INT_RGB);
		int[] imageRGBArrOne = getImageRGB(userImg);
		int[] imageRGBArrTwo = getImageRGB(blankPic);
		imageNew.setRGB(0, 0, userImg.getWidth(), userImg.getHeight(), imageRGBArrOne, 0, userImg.getWidth());
		imageNew.setRGB(0, userImg.getHeight(), blankPic.getWidth(), blankPic.getHeight(), imageRGBArrTwo, 0, blankPic.getWidth());
		return imageNew;
	}
	
	private int[] getImageRGB(BufferedImage image) {
		int[] ImageRGBArr = new int[image.getWidth() * image.getHeight()];
		return image.getRGB(0, 0, image.getWidth(), image.getHeight(), ImageRGBArr, 0, image.getWidth());
	}
	

	private BufferedImage zoomPic(BufferedImage qrcodeImg, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
		image.getGraphics().drawImage(qrcodeImg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
		return image;
	}
	
	private BufferedImage getImage(String url) {
		if ( !Strings.isNullOrEmpty(url) ) {
			try {
				HttpResponse<InputStream> response = Unirest.get(url).asBinary();
				if ( response != null ) {
					return ImageIO.read(response.getBody());
				}
			} catch (Exception e) {
				logger.error("在下载用户二维码图片时发生异常", e);
			}
		}
		return null;
	}
	
	
	private BufferedImage fileToImage(InputStream input) {
		try {
			return ImageIO.read(input);
		} catch (Exception e) {
			logger.error("在将用户上传文件转成BufferedImage时发生异常", e);
		}
		return null;
	}
	
	
	private BufferedImage buildBlankPic(int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);  
        Graphics g = image.getGraphics();// 得到图片 
        g.fillRect(0, 0, width, height);    
        g.setColor(Color.WHITE);  
        return image;
	}
	
	
	private void overlapImage(BufferedImage big, BufferedImage small) {  
        try {  
        	Graphics2D g = big.createGraphics();
            int x = big.getWidth()*1/2 - small.getWidth()/2;  
            int y = big.getHeight()/2 - small.getHeight()/2;  
            g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);  
            g.dispose();  
        } catch (Exception e) {  
            logger.error("在合成二维码图片时发生异常", e);
        }  
    }
	
	
}
