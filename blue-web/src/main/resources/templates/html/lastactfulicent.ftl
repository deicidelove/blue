<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>福利中心</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/swiper.min.css">
    <link rel="stylesheet" href="../css/welfare.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">福利中心</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
</div>
<div class="swiper01">
    <div class="swiper-container banner" id="banner">
        <ul class="swiper-wrapper img-list" id="test">
            <li class="swiper-slide"><img src="../images/icon-54.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-54.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-54.png" alt=""/></li>
            <li class="swiper-slide"><img src="../images/icon-54.png" alt=""/></li>
            
        </ul>
        <div class="swiper-pagination"></div>
    </div>
</div>
<div class="make"></div>
<div class="activity">
    <div class="activity_head">
        <span class="left">近期活动</span>
        <span class="right" onclick = "gotoLastActList()"></span>
    </div>
    <div class="activity_bg box">
    	<#if lastActList??>
    		<#list lastActList as lastAct>
    			<a href="/lastact/detail?lastActId=${(lastAct.sid)! }">
	    			<div class="flex-1 activity_bg0${lastAct_index+1}"  >
	    				<img style="    width: 94%; height: 2.1rem;  background-size: cover;  margin: 0 3% 1% 3%;" 
    						class="flex-1 "  src="${(lastAct.lastActListImg)! }">
	    			</div>
	    		</a>
    		</#list>
    	</#if>

    </div>
</div>
<div class="lottery">
    <div class="activity_head">
        <span class="left">积分抽奖</span>
        <span class="right"  onclick = "gotojifen()"></span>
    </div>
   	<a href="/jifen/actdetail/?goodsId=${goods.goodsId }">
	    <div class="lottery_img">
	    	<img src="${goods.goodsPicUrl }" />
	    </div>
	</a>
    <ul class="lottery_ul">
    	<#if actGoodsList??  >
    		<#list actGoodsList as actGoods>
    			<a href="/jifen/actdetail/?goodsId=${(actGoods.goodsId)! }">
    			<li class="lottery_li">
		            <div class="lottery_li_left left">
		            	<img  src="${(actGoods.listImg)! }" />
		            </div>
		            <div class="lottery_li_right left">
		                <div class="_li_top">
		                    <div>${(actGoods.goodsName)! }</div>
		                    <div>${(actGoods.goodsTitle)! }</div>
		                </div>
		                <div class="_li_bottom box">
		                    <div class="flex-1">
		                        <div class="_color01">${(actGoods.jifen)! }</div>
		                        <div>需积分</div>
		                    </div>
		                    <div class="flex-1">
		                        <div class="_color02">${(actGoods.participantsNum)! }</div>
		                        <div>已参与</div>
		                    </div>
		                    <div class="flex-1">
		                        <div class="_color03">${(actGoods.remainingNum)! }</div>
		                        <div>剩余</div>
		                    </div>
		                </div>
		            </div>
		        </li>
		   	</a>
    		</#list>
    	</#if>
        
  
    </ul>
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
    
    function gotojifen(){
    	window.location.href = "/jifen/index";
    }
    function gotoLastActList(){
    	window.location.href = "/lastact/list";
    }
</script>