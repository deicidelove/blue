<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>科室介绍</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/swiper.min.css">
    <link rel="stylesheet" href="../css/administrative_office.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">科室介绍</div>
    <div class="heade_back">返回</div>
</div>
<div class="swiper01">
    <div class="swiper-container banner" id="banner">
        <ul class="swiper-wrapper img-list" id="test">
            <li class="swiper-slide"><img src="../images/icon-03.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-03.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-03.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-03.png" alt=""/></li>
        </ul>
        <div class="swiper-pagination"></div>
    </div>
</div>
<div class="make"></div>
<ul class="office_ul">
	<#list depts as dept>
	    <a href="/deptDetial/${dept.sid}">
		    <li class="office_li">
		        <div class="office_li_left left office_01"></div>
		        <div class="office_li_center left">
		            <div>${dept.name}</div>
		            <div>${dept.context}</div>
		        </div>
		        <div class="office_li_right right"></div>
		    </li>
	     </a>
    </#list>
</ul>
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
</script>