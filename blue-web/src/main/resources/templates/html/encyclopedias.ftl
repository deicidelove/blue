<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>口腔百科</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/swiper.min.css">
    <link rel="stylesheet" href="../css/encyclopedia.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">口腔百科</div>
    <div class="heade_back">返回</div>
</div>
<div class="swiper01">
    <div class="swiper-container banner" id="banner">
        <ul class="swiper-wrapper img-list" id="test">
            <li class="swiper-slide"><img src="../images/icon-69.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-69.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-69.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-69.png" alt=""/></li>
        </ul>
        <div class="swiper-pagination"></div>
    </div>
</div>
<div class="sift">
    <span class="left">精选百科</span>
    <span class="left">SELECTED</span>
</div>
<div class="sift_img">
    <div class="sift_img_top">
        <div class="sift_img_top_left left">牙齿检查，让您与家人尽享美味</div>
        <div class="sift_img_top_right left"></div>
    </div>
    <div class="sift_img_botm box">
    	<#list encyclopediasjx as jx>
	         <a href="/encyclopediasDetial/${jx.sid}">
		        <div class="flex-1">
		        	<#if jx.url??>
		            <div class="sift_img_botm_bg01" style="background: url('${jx.url}')no-repeat center;"></div>
		            <#else>
		             <div class="sift_img_botm_bg01"></div>
		            </#if>
		            <div>${jx.title}</div>
		        </div>
	        </a>
        </#list>
    </div>
</div>
<div class="swiper02">
    <div class="swiper-container banner02">
        <ul class="swiper-wrapper img-list">
            <li class="swiper-slide"><img src="../images/icon-74.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-74.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-74.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-74.png" alt=""/></li>
        </ul>
        <div class="swiper-pagination"></div>
    </div>
</div>
<a href="/encyAll">
    <div class="sift">  
        <span class="left">口腔百科</span>
        <span class="left">ORAL ENCYCLOPEDIA</span>
        <span class="right"></span>
    </div>
</a>
<div class="oral_an">
    <div class="oral_an_bg"></div>
    <div class="oral_an_head">你的牙齿这么烂为啥不矫正？！</div>
</div>
<ul class="encyclopedia_ul">
    <#list encyclopedias as item>
	    <a href="/encyclopediasDetial/${item.sid}">
		    <li class="encyclopedia_li">
	    		<#if item.url??>
	            <div class="left encyclopedia_li_bg" style="background: url('${item.url}')no-repeat center;"></div>
	            <#else>
	             <div class="left encyclopedia_li_bg"></div>
	            </#if>
	        	<div class="left encyclopedia_li_cent">
	            <div>${item.title}</div>
	            <div>${item.context}</div>
		        </div>
		    </li>
	    </a>
    </#list>
</ul>
<div class="margin_bottom"></div>
<div class="footer box">
    
    <div class="flex-1 footer_list">
      <a href="/">
        <div class="footer_bg04_active"></div>
        <div class="footer_color">首页</div>
     </a>
    </div>
   
   
    <div class="flex-1 footer_list">
         <a href="/blueWebsite">
        <div class="footer_bg01"></div>
        <div>蓝鲟</div>
        </a>
    </div>
    
   
    <div class="flex-1 footer_list">
     <a href="/blueWebsite">
        <div class="footer_bg02"></div>
        <div>走进蓝鲟</div>
    </a>
    </div>
    
    <div class="flex-1 footer_list">
        <a href="/personal/index">
            <div class="footer_bg03"></div>
            <div>我的</div>
        </a>
    </div>
</div>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../js/swiper.min.js"></script>
<script>
    var swiper = new Swiper('.swiper01 .swiper-container', {
        pagination: '.swiper-pagination',
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        paginationClickable: true,
        spaceBetween: 0,
        centeredSlides: true,
        loop: true,
        autoplay: 2500,
        autoplayDisableOnInteraction: false
    });
    var swiper02 = new Swiper('.swiper02 .swiper-container', {
        pagination: '.swiper-pagination',
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        paginationClickable: true,
        spaceBetween: 0,
        centeredSlides: true,
        loop: true,
        autoplay: 1500,
        autoplayDisableOnInteraction: false
    });
</script>