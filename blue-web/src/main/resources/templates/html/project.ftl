<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>项目中心</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/swiper.min.css">
    <link rel="stylesheet" href="../css/project_center.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">项目中心</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
</div>
<div class="swiper01">
    <div class="swiper-container banner" id="banner">
        <ul class="swiper-wrapper img-list" id="test">
        	<#list projectLBT as lbt>
	        	<a href="${lbt.jumpUrl}">
		            <li class="swiper-slide">
		            	<img src="${lbt.picUrl}" alt=""/>
		            </li>
	            </a>
            </#list>
        </ul>
        <div class="swiper-pagination"></div>
    </div>
</div>
<div class="project_img">
    <#if project0??>
        <a href="${project0.jumpUrl}">
        <div class="project_img_01 left" style="background-image: url(${project0.picUrl})">
            <div>${project0.title}</div>   
        </div>
        </a>
    </#if>
    <#if project1??>
        <a href="${project1.jumpUrl}">
        <div class="project_img_02 left" style="background-image: url(${project1.picUrl})">
            <div>${project1.title}</div>
        </div>
         </a>
    </#if>
    <#if project2??>
        <a href="${project2.jumpUrl}">
        <div class="project_img_03 left" style="background-image: url(${project2.picUrl})">
            <div>${project2.title}</div>
         </div>
        </a>
    </#if>
    <#if project3??>
        <a href="${project3.jumpUrl}">
        <div class="project_img_04 left" style="background-image: url(${project3.picUrl})">
            <div>${project3.title}</div>
        </div>
      </a>
    </#if>
    <#if project4??>
        <a href="${project4.jumpUrl}">
        <div class="project_img_05 left" style="background-image: url(${project4.picUrl})">
            <div>${project4.title}</div>
        </div>
        </a>
    </#if>
</div>
<a href="/projectPageAll">
<div class="all_projects">
    <span class="left">所有项目</span>
    <span class="left">ALL PROJECTS</span>
    <span class="right"></span>
</div>
</a>
<#if projectMiddle??>
<a href="${projectMiddle.jumpUrl}"><div class="all_01" style="background-image:${projectMiddle.picUrl}"></div></a>
<#else>
<div class="all_01" ></div>
</#if>
<div class="all_bg box">
     <#if project5??>
	    <div class="all_bg_div left">
	    <a href="/projectDetial/${project5.sid}">
	        <div class="all_02" style="background-image: url(${project5.url})"></div>
	        <div class="all_text01">${project5.name}</div>
	        <div class="all_text02">${project5.context}</div>
    	</a>
    	</div>
    </#if>
     <#if project6??>
	    <div class="all_bg_div margin_12 left">
	    <a href="/projectDetial/${project6.sid}">
	        <div class="all_03" style="background-image: url(${project6.url})"></div>
	        <div class="all_text01">${project6.name}</div>
	        <div class="all_text02">${project6.context}</div>
    	 </a>
    	</div>
	   
    </#if>
     <#if project7??>
	    <div class="all_bg_div right">
	    <a href="/projectDetial/${project7.sid}" >
	        <div class="all_04" style="background-image: url(${project7.url})"></div>
	        <div class="all_text01">${project7.name}</div>
	        <div class="all_text02">${project7.context}</div>
    	</a>
    	</div>
	    
    </#if>
</div>
<div class="swiper02">
    <div class="swiper-container banner02">
        <ul class="swiper-wrapper img-list">
        <#list projectBottom as bottom>
	        	<a href="${bottom.jumpUrl}">
		            <li class="swiper-slide">
		            	<img src="${bottom.picUrl}" alt=""/>
		            </li>
	            </a>
            </#list>
        </ul>
        <div class="swiper-pagination"></div>
    </div>
</div>
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