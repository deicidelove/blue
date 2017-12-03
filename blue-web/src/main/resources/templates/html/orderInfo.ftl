<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>预约信息</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/reserve_information.css" />
     <script src="../../js/common.js"></script>
    <link rel="stylesheet" href="../../css/common.css" />
    <link rel="stylesheet" href="../../css/reserve_information.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">预约信息</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back" style="color: white;">返回</div></a>
</div>
<ul class="infor_ul">
    <li class="infor_li">
        <div class="left infor_li_div">挂号科室</div>
        <div class="left infor_li_input" name="deptName" val="${dept.name!}">${dept.name!}</div>
    </li>
    <li class="infor_li">
        <div class="left infor_li_div">科室区域</div>
        <div class="left infor_li_input" name="deptId" val="${dept.sid}">${staff.address!}</div>
    </li>
    <li class="infor_li">
        <div class="left infor_li_div">挂号医师</div>
        <div class="left infor_li_input" name="staffName" val="${staff.name!}">${staff.name!}</div>
    </li>
    <li class="infor_li">
        <div class="left infor_li_div">就诊日期</div>
        <div class="left infor_li_input" name="orderTime" val="${orderTime} ${bds.shiftTime!}">${orderTime} ${bds.shiftTime!}</div>
    </li>
    <li class="infor_li">
        <div class="left infor_li_div">挂号费用</div>
        <div class="left infor_li_input infor_li_input_color" name="pay" val="${staff.callFee}">￥${staff.callFee}</div>
    </li>
    <a href="/selectPationPage/${bds.sid}">
    <li class="infor_li margin_top">
        <div class="left infor_li_div">就诊人</div>
        <#if pation??>
        <div class="left infor_li_input infor_li_people" name="pationId" val="${pation.sid}">${pation.name!}</div>
        <#else>
        <div class="left infor_li_input infor_li_people" name="pationId" val="0">添加就诊人</div>
        </#if>
        <div class="right infor_li_input_bg"></div>
    </li>
    </a>
</ul>
<div style="display:none" name="scheduleId" val="${bds.sid}"></div>
<div class="infor_btn" value="${staff.sid}">立即预约</div>
</body>
</html>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script src="../../js/addAppointment.js?v=1"></script>