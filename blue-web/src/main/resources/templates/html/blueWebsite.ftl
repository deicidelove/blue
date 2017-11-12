<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>蓝鲟官网</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/swiper.min.css">
    <link rel="stylesheet" href="../css/network.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">蓝鲟口腔医院</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
</div>
<div class="swiper01">
    <div class="swiper-container banner" id="banner">
        <ul class="swiper-wrapper img-list" id="test">
            <li class="swiper-slide"><img src="../images/icon-41.jpg" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-41.jpg" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-41.jpg" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-41.jpg" alt=""/></li>
        </ul>
        <div class="swiper-pagination"></div>
    </div>
</div>
<div class="nav">
    <ul class="nav_ul">
        <li class="box nav_li">
        <div class="flex-1 nav_li_div">
        	 <a  href="/hospitalPage">
	            <div class="nav_li_div_01"></div>
	            <div>医院概况</div>
	            <div>HOSPITAL PROFILE</div>
        	 </a>
        </div>
        
            <div class="flex-1 nav_li_div">
                <a  href="/projectPage">
	                <div class="nav_li_div_02"></div>
	                <div>项目中心</div>
	                <div>PROJECT CENTER</div>
             	</a>
            </div>
        
            <div class="flex-1 nav_li_div">
                <a  href="/doctorPage">
	                <div class="nav_li_div_03"></div>
	                <div>蓝鲟专家</div>
	                <div>LANXUN EXPERT</div>
            	 </a>
            </div>
        </li>
       
        <li class="box nav_li">
            <div class="flex-1 nav_li_div">
           		<a  href="/deptIntroducePage">
	                <div class="nav_li_div_04"></div>
	                <div>科室介绍</div>
	                <div>DEPARTMENT</div>
                </a>
            </div>
            
            <div class="flex-1 nav_li_div">
                <a  href="/jifen/index">
                <div class="nav_li_div_05"></div>
                <div>积分活动</div>
                <div>INTEGRAL ACTIVITY</div>
                </a>
            </div>
            <div class="flex-1 nav_li_div">
                <a  href="/encyclopediasPage">
                <div class="nav_li_div_06"></div>
                <div>口腔百科</div>
                <div>ENCYCLOPEDIAS</div>
                </a>
            </div>
        </li>
    </ul>
    <a href="/orderPage">
    <div class="registration">
        <div>预约挂号</div>
        <div>
            <span>口腔报告</span>
            <span>口腔调理</span>
            <span>养生方案</span>
        </div>
        <div>为你省更多时间</div>
    </div>
    </a>
    <a href="/orderPage">
    <div class="make">
        <div>今日预约</div>
        <div>
            <span>口腔报告</span>
            <span>口腔调理</span>
            <span>养生方案</span>
        </div>
        <div>尽早治疗更有益</div>
    </div>
    </a>
</div>
 <a href="/noticePage">
<div class="affiche">
    <div class="affiche_bg left"></div>
    <div class="affiche_news left">
        <div class="swiper02">
            <div class="swiper-container swiper-container-v">
                <ul class="message_r left swiper-wrapper">
 					<#list adverts as item>                   
 						<li class="swiper-slide">
 							<div class="message_div01">
                            	<span class="left"></span>
                            	<span class="left" value="${item.advert.sid}">${item.advert.title}</span>
                        	</div>
                        	 <#if item.flag>
                      		 <div class="message_div01">
                           	 	<span class="left"></span>
                          	  	<span class="left" value="${item.advertNext.sid}">${item.advertNext.title}</span>
                       		 </div>
                       		 </#if>
                    	</li>
					</#list>
                </ul>
            </div>
        </div>
    </div>
</div>
</a>
<div class="invite"></div>
<div class="lottery">
    <div class="lottery_head">
        <span class="left">积分抽奖</span>
        <span class="left">LUCK DRAW</span>
        <span class="right" onclick="jifenRight()"></span>
    </div>
    <div class="lottery_bg">
         <#list goodsList as goods>
            <a href="/jifen/detail?goodsId=${goods.goodsId }">
                <div class="lottery_bg01 left" >
                	<img class="lottery_bg01 left"  src="${goods.goodsPicUrl }"/>
                </div>
            </a>
        </#list>
    </div>
</div>
<div class="activity">
    <div class="lottery_head">
        <span class="left">近期活动</span>
        <span class="left">RECENT ACTIVITY</span>
        <span class="right"></span>
    </div>
    <div class="activity_bg01"></div>
    <div class="activity_bglist box">
        <div class="flex-1 activity_bg02"></div>
        <div class="flex-1 activity_bg03"></div>
        <div class="flex-1 activity_bg04"></div>
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
    setTimeout(function(){
        var swiper = new Swiper('.swiper02 .swiper-container-v', {
            pagination: '.swiper-pagination-v',
            paginationClickable: true,
            direction: 'vertical',
            spaceBetween: 10,
            centeredSlides: true,
            loop: true,
            autoplay: 2500,
            autoplayDisableOnInteraction: false
        });
    },100);
    
    function jifenRight(){
    	window.location.href = "/jifen/index";
    }
</script>