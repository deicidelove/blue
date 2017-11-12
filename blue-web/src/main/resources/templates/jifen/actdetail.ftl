<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>积分活动-详情</title>
    <script src="../../js/common.js"></script>
    <link rel="stylesheet" href="../../css/common.css" />
    <link rel="stylesheet" href="../../css/swiper.min.css">
    <link rel="stylesheet" href="../../css/activity_xq.css" />
</head>
<script>
	var actId = '${actId}';
	var goodsId = '${goodsId}';
	var basePath = '${request.contextPath}';
</script>
<body>
<div class="heade">
    <div class="heade_text">积分活动-详情</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
</div>
<div class="activity_banner">
    <div class="swiper01">
        <div class="swiper-container banner" id="banner">
            <ul class="swiper-wrapper img-list" id="test">
            	<#if (detailDTO.imgList)??>
	            	<#list detailDTO.imgList as temp>
	               		<li class="swiper-slide"><img src="${temp.goodsImgUrl!}" alt=""/></li>
	                </#list>
                </#if>
            </ul>
            <div class="swiper-pagination"></div>
        </div>
    </div>
</div>
<div class="activity_ing">
    <div class="activity_ing_top">
        <div class="left">
        	进行中
        </div>
        <h3 class="left">${detailDTO.goodsName}</h3>
    </div>
    <div class="box activity_ing_botm">
        <div class="flex-1">
            <span>总需人次</span>
            <span class="color02">${detailDTO.actTotalNum}</span>
        </div>
        <div class="flex-1 text_alinecent">
            <span>已参与</span>
            <span class="color02">${detailDTO.participantsNum}</span>
        </div>
        <div class="flex-1 text_alineright">
            <span>剩余</span>
            <span class="color01">${detailDTO.remainingNum}</span>
        </div>
    </div>
</div>
<div class="record record_btn01">
    <div class="left">参与记录</div>
    <div class="right"></div>
</div>
<ul class="activity_ul">
	<#list detailDTO.goodsDetailWxDTOList as temp>
	    <li class="activity_li">
	        <div class="activity_li_div01 left">
	        	<img src="${temp.wxDetailEntity.pic}" class="activity_li_div01 left"/>
	        </div>
	        <div class="activity_li_div02 left">
	            <div>${temp.wxDetailEntity.name}抢购了1人次</div>
	            <div>${temp.createTime}</div>
	        </div>
	    </li>
    </#list>
</ul>
<div class="height20"></div>
<div class="record record_btn02">
    <div class="left">项目详情</div>
    <div class="right "></div>
</div>
<div class="particulars">
    <div class="particulars_img"></div>
    <div class="particulars_cont">
        ${detailDTO.goodsDetail }
    </div>
</div>
<div class="height20"></div>
<div class="record record_btn03">
    <div class="left">我的本期幸运号</div>
    <div class="right "></div>
</div>
	<#if  (detailDTO.givingCodeList)?? >
		<#list detailDTO.givingCodeList as givingCode>
			<div class="activity_num">${givingCode}</div>
		</#list>
	</#if>
<div class="height20"></div>
<div class="rule">
    <div class="left">规则展示</div>
    <div class="right"></div>
</div>
<div class="infor_btn">立即支付￥${detailDTO.goodsPrice }抢购</div>
<div class="share">
    <div class="close">
        <div class="right close_btn"></div>
    </div>
    <div class="way">
        <div class="way_top">兑换方式</div>
        <div class="way_botm">
            <div way="way" class="left active" exchangeType = "jifen">积分兑换</div>
            <div way="way" class="left" exchangeType = "xianjin">现金兑换</div>
        </div>
    </div>
    <div class="quantity">
        <div class="way_top">数量</div>
        <div class="quantity_botm">
            <div class="minus left minus_active"></div>
            <div class="number left">1</div>
            <div class="add left"></div>
            <div class="total left">
                <span>剩余:</span>
                <span>${detailDTO.remainingNum}</span>
            </div>
        </div>
    </div>
    <div class="share_btn">确定</div>
</div>
<div class="share_bg"></div>
</body>
</html>
<script src="../../js/jquery.js"></script>
<script src="../../js/activity_xq.js?v=6"></script>
<script src="../../js/swiper.min.js"></script>
<script src="../../js/bluecommon.js"></script>
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