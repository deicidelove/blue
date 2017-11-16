<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>个人中心</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/personal.css" />
</head>
<script>
   var basePath = '${request.contextPath}';
</script>
<body>
<div class="heade">
    <div class="heade_text">个人中心</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
</div>
<div class="integral_banner"></div>
<div class="buy_credits">
    <div class="buy_credits_cont">
        <div class="personal_head"><img class="personal_head_img"  src = "${wxDetailEntity.pic! }"/></div>
        <div class="personal_name">${wxDetailEntity.name! }</div>
        <div class="personal_integral">
            <span class="left"></span>
            <span class="left">积分${wxUserEntity.jifen! }</span>
        </div>
    </div>
    <div class="chain_L"></div>
    <div class="chain_R"></div>
</div>
<ul class="integral_ul">
    <li class="integral_li">
        <a href="/myOrderList">
        <div class="left integral_li_bg01"></div>
        <div class="left">我的预约</div>
        <div class="right"></div>
        </a>
    </li>
    <li class="integral_li">
    	<a href="/managePationPage  ">
        <div class="left integral_li_bg02"></div>
        <div class="left">就诊人管理</div>
        <div class="right"></div>
        </a>
    </li>
    <li class="integral_li">
        <div class="left integral_li_bg03"></div>
        <div class="left" id= "order">我的订单</div>
        <div class="right"></div>
    </li>
    <li class="integral_li">
        <div class="left integral_li_bg04"></div>
        <div class="left" id = "invitation">我的邀请</div>
        <div class="right"></div>
    </li>
    <li class="integral_li">
        <div class="left integral_li_bg05"></div>
        <div class="left" id = "buyjifen">购买积分</div>
        <div class="right"></div>
    </li>
    <li class="integral_li">
        <div class="left integral_li_bg06"></div>
        <div class="left" id = "jifendetail">我的积分明细</div>
        <div class="right"></div>
    </li>
    <li class="integral_li bordernone">
        <div class="left integral_li_bg07"></div>
        <div class="left">我的二维码分享</div>
        <div class="right"></div>
    </li>
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
<script src="../js/personal.js"></script>