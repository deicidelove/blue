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
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
</div>
<div class="swiper01">
    <div class="swiper-container banner" id="banner">
        <ul class="swiper-wrapper img-list" id="test">
         <#list encyclopedyLBT as lbt>
	        	
		            <li class="swiper-slide">
		            <a href="${lbt.jumpUrl}">
		            	<img src="${lbt.picUrl}" alt=""/>
		            </a>
		            </li>
	           
            </#list>
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
        <#if encyclopediasjx0??>
	        <div class="sift_img_top_left left">${encyclopediasjx0.title}</div>
	        <a href="/encyclopediasDetial/${encyclopediasjx0.sid}">
	        <div class="sift_img_top_right left" style="background-image: url(${encyclopediasjx0.url})"></div>
	    	</a>
    	<#else>
	    	<div class="sift_img_top_left left">请设置精选百科</div>
	        <div class="sift_img_top_right left" ></div>
    	</#if>
    </div>
    <div class="sift_img_botm box">
    	<#if encyclopediasjx1??>
	    	
	        <div class="flex-1">
	        	<a href="/encyclopediasDetial/${encyclopediasjx1.sid}">
		            <div class="sift_img_botm_bg01" style="background-image: url(${encyclopediasjx1.url})"></div>
		            <div>${encyclopediasjx1.title}</div>
	            </a> 
	        </div>
	       
        </#if>
        <#if encyclopediasjx2??>
	        
	        <div class="flex-1 margin_13">
	        	<a href="/encyclopediasDetial/${encyclopediasjx2.sid}">
		            <div class="sift_img_botm_bg02" style="background-image: url(${encyclopediasjx2.url})"></div>
		            <div>${encyclopediasjx2.title}</div>
	         	</a>
	        </div>
	       
        </#if>
        <#if encyclopediasjx3??>
	       
	        <div class="flex-1">
		         <a href="/encyclopediasDetial/${encyclopediasjx3.sid}">
		            <div class="sift_img_botm_bg03" style="background-image: url(${encyclopediasjx3.url})"></div>
		            <div>${encyclopediasjx3.title}</div>
		         </a>
	        </div>
	       
        </#if>
    </div>
</div>
<div class="swiper02">
    <div class="swiper-container banner02">
        <ul class="swiper-wrapper img-list">
             <#list encyclopedyMiddle as middle>
	        	
		            <li class="swiper-slide">
		            <a href="${middle.jumpUrl}">
		            	<img src="${middle.picUrl}" alt=""/>
		             </a>
		            </li>
	           
            </#list>
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
<#if encyclopedias0??>
 <a href="/encyclopediasDetial/${encyclopedias0.sid}">
<div class="oral_an">
    <div class="oral_an_bg" style="background-image: url(${encyclopedias0.url})"></div>
    <div class="oral_an_head">${encyclopedias0.title}</div>
</div>
</a>
<#else>
<div class="oral_an">
    <div class="oral_an_bg"></div>
    <div class="oral_an_head">请设置口腔百科</div>
</div>
</#if>
<ul class="encyclopedia_ul">
    <#if encyclopedias1??>
    <a href="/encyclopediasDetial/${encyclopedias1.sid}">
    <li class="encyclopedia_li">
        <div class="left encyclopedia_li_bg" style="background-image: url(${encyclopedias1.url})"></div>
        <div class="left encyclopedia_li_cent">
            <div>${encyclopedias1.title}</div>
            <div name="clearHtml">${encyclopedias1.context}</div>
        </div>
    </li>
    </a>
    </#if>
     <#if encyclopedias2??>
     <a href="/encyclopediasDetial/${encyclopedias2.sid}">
    <li class="encyclopedia_li">
        <div class="left encyclopedia_li_bg encyclopedia_li_bg02" style="background-image: url(${encyclopedias2.url})"></div>
        <div class="left encyclopedia_li_cent">
            <div>${encyclopedias2.title}</div>
             <div name="clearHtml">${encyclopedias2.context}</div>
        </div>
    </li>
    </a>
    </#if>
    <#if encyclopedias3??>
    <a href="/encyclopediasDetial/${encyclopedias3.sid}">
    <li class="encyclopedia_li">
        <div class="left encyclopedia_li_bg encyclopedia_li_bg03" style="background-image: url(${encyclopedias3.url})"></div>
        <div class="left encyclopedia_li_cent">
            <div>${encyclopedias3.title}</div>
             <div name="clearHtml">${encyclopedias3.context}</div>
        </div>
    </li>
     </a>
     </#if>
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
<script>
	debugger;
 		$("[name=clearHtml]").each(function () {
            var that = $(this);
            var textNoHtml = that.text();
			that.text(textNoHtml); 
        });
</script>