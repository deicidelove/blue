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
    <#if project0??>
        <a href="/projectDetial/${project0.sid}">
        <div class="project_img_01 left" style="background: url(${project0.url})no-repeat center;">
            <div>${project0.name}</div>   
        </div>
        </a>
    </#if>
    <#if project1??>
        <a href="/projectDetial/${project1.sid}">
        <div class="project_img_02 left" style="background: url(${project1.url})no-repeat center;">
            <div>${project1.name}</div>
        </div>
         </a>
    </#if>
    <#if project2??>
        <a href="/projectDetial/${project2.sid}">
        <div class="project_img_03 left" style="background: url(${project2.url})no-repeat center;">
            <div>${project2.name}</div>
         </div>
        </a>
    </#if>
    <#if project3??>
        <a href="/projectDetial/${project3.sid}">
        <div class="project_img_04 left" style="background: url(${project3.url})no-repeat center;">
            <div>${project3.name}</div>
        </div>
      </a>
    </#if>
    <#if project4??>
        <a href="/projectDetial/${project4.sid}">
        <div class="project_img_05 left" style="background: url(${project4.url})no-repeat center;">
            <div>${project4.name}</div>
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
<div class="all_01"></div>
<div class="all_bg box">
     <#if project5??>
		<a href="/projectDetial/${project5.sid}">
	    <div class="all_bg_div left">
	        <div class="all_02" style="background: url(${project5.url})no-repeat center;"></div>
	        <div class="all_text01">${project5.name}</div>
	        <div class="all_text02">${project5.context}</div>
    	</div>
	    </a>
    </#if>
     <#if project6??>
		<a href="/projectDetial/${project6.sid}">
	    <div class="all_bg_div margin_12 left">
	        <div class="all_03" style="background: url(${project6.url})no-repeat center;"></div>
	        <div class="all_text01">${project6.name}</div>
	        <div class="all_text02">${project6.context}</div>
    	</div>
	    </a>
    </#if>
     <#if project7??>
		<a href="/projectDetial/${project7.sid}" >
	    <div class="all_bg_div left">
	        <div class="all_04" style="background: url(${project7.url})no-repeat center;"></div>
	        <div class="all_text01">${project7.name}</div>
	        <div class="all_text02">${project7.context}</div>
    	</div>
	    </a>
    </#if>
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