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
    <div class="heade_back">返回</div>
</div>
<div class="swiper01">
    <div class="swiper-container banner" id="banner">
        <ul class="swiper-wrapper img-list" id="test">
            <li class="swiper-slide"><img src="../images/icon-79.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-79.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-79.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-79.png" alt=""/></li>
        </ul>
        <div class="swiper-pagination"></div>
    </div>
</div>
<div class="project_img">
    <div class="project_img_01 left">
        <div>定制化儿童体检</div>
        <div>儿童护龈保健</div>
    </div>
    <div class="project_img_02 left">
        <div>呼吸中心</div>
    </div>
    <div class="project_img_03 left">
        <div>中医护龈</div>
    </div>
    <div class="project_img_04 left">
        <div>牙龈肿痛</div>
    </div>
    <div class="project_img_05 left">
        <div>正畸牙齿</div>
    </div>
</div>
<div class="all_projects">
    <span class="left">所有项目</span>
    <span class="left">ALL PROJECTS</span>
    <span class="right"></span>
</div>
<div class="all_01"></div>
<div class="all_bg box">
	<#list projects as project>
		<a href="/projectDetial/${project.sid}">
	    <div class="flex-1">
	        <div class="all_02"><img src="${project.url}"/></div>
	        <div class="all_text01">${project.name}</div>
	        <div class="all_text02">${project.context}</div>
	    </div>
	    </a>
   	</#list>
</div>
<div class="swiper02">
    <div class="swiper-container banner02">
        <ul class="swiper-wrapper img-list">
            <li class="swiper-slide"><img src="../images/icon-89.jpg" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-89.jpg" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-89.jpg" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-89.jpg" alt=""/></li>
        </ul>
        <div class="swiper-pagination"></div>
    </div>
</div>
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
        <div class="footer_bg03"></div>
        <div>我的</div>
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