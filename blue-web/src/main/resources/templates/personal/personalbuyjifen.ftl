<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>购买积分</title>
    <script src="../../js/common.js"></script>
    <link rel="stylesheet" href="../../css/common.css" />
    <link rel="stylesheet" href="../../css/join_us.css" />
</head>
<body>
    <div class="heade">
        <div class="heade_text">购买积分</div>
       <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
    </div>
    
    <ul class="infor_ul">
        <li class="infor_li">
            <div class="left infor_li_div">购买积分</div>
            <div class="left infor_li_input"><input id="jifenCount" type="text" value="" placeholder="请输入购买积分数量"></div>
        </li>
        <li class="infor_li">
            <div class="left infor_li_div">应付金额</div>
            <div class="left infor_li_input infor_li_input_color" id="czFrePage">￥0.00</div>
            <input id="jifenCount" type="text" value="" id="czFre" display ="none"/>
        </li>
    </ul>
    <p class="jifen_tip btn_bspace">购买需知：一元等于十个积分，积分只可兑换活动，不可再次转换成现金，且不可提取。解释权规南京蓝鲟口腔医院。</p>
    <div class="jifen_field">
        <button class="com_btn blue_btn id = "buyjifen">立即购买</button>
    </div>
</body>
</html>

<script src="../../js/jifenbuy.js"></script>
<script src="../../js/jquery.js"></script>