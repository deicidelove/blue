package com.common.system.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.common.system.entity.WxUserEntity;
import com.common.system.service.WxUserBLueService;
import com.common.system.util.PicUtil;
import com.common.system.util.StandardJSONResult;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

@Controller
public class TemplateController {
	private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);
	// 合成图片后下载到本地的位置
	private static final String PictureRoot = "picture";
	
	// 微信服务
	@Resource WxMpService wxService;
	@Resource WxUserBLueService wxUserBLueService;
	private final ResourceLoader resourceLoader;  
	
	
	@Autowired  
	public TemplateController(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;  
	} 
	
	
	
	/** 根据电话号码获取用户二维码  **/
	@ResponseBody
	@RequestMapping("/getORCode")
	public String getORCode(HttpServletResponse response, String openId) throws Exception {
		String qrcodeUrl = getQRCodeUrl(openId);
		return qrcodeUrl;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCombinePic", method = RequestMethod.POST)
	public String getCombinePic(String fileUrl, String openId, String type) throws Exception {
		WxUserEntity wxUser = wxUserBLueService.getById(openId);
		if ( wxUser == null ) {
			return JSON.toJSONString(StandardJSONResult.getFailedInstance("无法确定您的身份,请重新登录"));
		} else {
			// 文件名
			if ( StringUtils.isEmpty(fileUrl) ) {
				return JSON.toJSONString(StandardJSONResult.getFailedInstance("请先选中上传一张图片"));
			} else {
				// 二维码网址
				try {
					if(fileUrl.indexOf("..") != -1){
						fileUrl = "http://wx.njlxkq.com"+fileUrl.substring(2);
					}
					String picturePathStr = wxUser.getCombinedPicturePath();
					Map<String, String> typePicMap = JSON.parseObject(picturePathStr, new TypeReference<Map<String, String>>(){});
					if ( !CollectionUtils.isEmpty(typePicMap) && typePicMap.containsKey(type) ) {
						// 如果已经存在,则使用已存在图片
						String downloadFilePath = typePicMap.get(type);
						return JSON.toJSONString(StandardJSONResult.getSuccessInstance(JSON.toJSONString(new PictureFilePath(downloadFilePath, null))));
					} else {
						// 否则合成
						String qrCodeUrl = getQRCodeUrl(openId);
						BufferedImage qrcodeImg = getImage(openId, qrCodeUrl);
						if ( qrcodeImg != null ) {
							InputStream in = Unirest.get(fileUrl).asBinary().getBody();
							BufferedImage userImg = fileToImage(in);
							if ( userImg == null ) {
								return JSON.toJSONString(StandardJSONResult.getFailedInstance("不是合法图片,请重新上传"));
							} else {
								// (588, 1150) (689, 1249)
								// 缩小二维码
								Integer zoomSize = getZoomSize();
								BufferedImage zoomQRCode = zoomPic(qrcodeImg, zoomSize, zoomSize);
								// 合成图片
								overlapImage(userImg, zoomQRCode, 590, 1150);
								// 写入本地
								String pictureName = openId +type+ ".jpg";
								String picUrl =PicUtil.upFile(userImg, pictureName);
								// 更新合成后的本地文件地址
								wxUserBLueService.updateCombinedPicturePath(openId, putPicturePath(typePicMap, type, picUrl));
								return JSON.toJSONString(StandardJSONResult.getSuccessInstance(JSON.toJSONString(new PictureFilePath(picUrl, picUrl))));
							}
						} else {
							return JSON.toJSONString(StandardJSONResult.getFailedInstance("在获取用户二维码图片时发生异常"));
						}
					}
				} catch (Exception e) {
					logger.error("用户" + openId + "在获取二维码图片时发生异常", e);
					return JSON.toJSONString(StandardJSONResult.getFailedInstance("在获取用户二维码图片时发生异常"));
				}
			}
		}
	}

	
	private String putPicturePath(Map<String, String> typePicMap, String type, String localFilePath) {
		if ( CollectionUtils.isEmpty(typePicMap) ) {
			typePicMap = Maps.newHashMap();
		}
		typePicMap.put(type, localFilePath);
		return JSON.toJSONString(typePicMap);
	}


	// 通过ticket换取二维码
	public static final String ShowQRCodeURL = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
	private String getQRCodeUrl(String openId) {
		String qrCodeUrl = wxUserBLueService.getUserQRCode(openId);
		if ( !Strings.isNullOrEmpty(qrCodeUrl) ) {
			return qrCodeUrl;
		} else {
			try {
				WxMpQrCodeTicket ticket = wxService.getQrcodeService().qrCodeCreateLastTicket(openId);
				String qrcodeUrl = ShowQRCodeURL + "?ticket=" + ticket.getTicket();
				wxUserBLueService.updateUserQRCodeUrl(openId, ticket.getTicket(), qrcodeUrl);
				return qrcodeUrl;
			} catch (Exception e) {
				logger.error("在获取用户" + openId + "的二维码图片时发生异常", e);
				return null;
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
	
	
	// 默认二维码宽度为100
	private Integer getZoomSize() {
		return 100;
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
	
	private BufferedImage getImage(String openId, String url) {
		if ( !Strings.isNullOrEmpty(url) ) {
			try {
				HttpResponse<InputStream> response = Unirest.get(url).asBinary();
				if ( response != null ) {
					return ImageIO.read(response.getBody());
				}
			} catch (Exception e) {
				logger.error("在下载用户二维码图片时发生异常", e);
			}
		} else {
			logger.error("用户{}的二维码图片地址为空,无法合成二维码图片", openId);
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
	
	
	private void overlapImage(BufferedImage big, BufferedImage small, int x, int y) {  
        try {  
        	Graphics2D g = big.createGraphics();
        	g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);  
            g.dispose();
        } catch (Exception e) {  
            logger.error("在合成二维码图片时发生异常", e);
        }
    }
	
	
	//****************************************************************************************************************************
	@SuppressWarnings("unused")
	private BufferedImage combineImages(BufferedImage userImg, BufferedImage blankPic) {
		BufferedImage imageNew = 
				new BufferedImage(userImg.getWidth(), userImg.getHeight() + blankPic.getHeight(), BufferedImage.TYPE_INT_RGB);
		int[] imageRGBArrOne = getImageRGB(userImg);
		int[] imageRGBArrTwo = getImageRGB(blankPic);
		imageNew.setRGB(0, 0, userImg.getWidth(), userImg.getHeight(), imageRGBArrOne, 0, userImg.getWidth());
		imageNew.setRGB(0, userImg.getHeight(), blankPic.getWidth(), blankPic.getHeight(), imageRGBArrTwo, 0, blankPic.getWidth());
		return imageNew;
	}
	
	@SuppressWarnings("unused")
	private BufferedImage buildBlankPic(int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);  
		Graphics g = image.getGraphics();// 得到图片 
		g.fillRect(0, 0, width, height);    
		g.setColor(Color.WHITE);  
		return image;
	}
}
