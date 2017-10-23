<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>蓝鲟专家</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/specialist.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">蓝鲟专家</div>
    <div class="heade_back">返回</div>
</div>
<div class="search">
    <input type="text" class="search_input" placeholder="搜索疾病，医生姓名"/>
    <div class="search_btn"></div>
</div>
<div class="drop_down">
	
	    <div class="drop_down_btn">
	    	<#if flag>
	        <span class="left drop_down_span01">科室选择</span>
	        <span class="left drop_down_span02"></span>
	        <span class="left drop_down_span03"></span>
	        </#if>
	    </div>
    </#if>
    <ul class="down_ul">
        <#list depts as dept>
	         <a href="/findDoctor/${dept.sid}">
		        <li class="down_li">
		           <span class="left">${dept.name}</span>
		            <!--<span class="right down_li_bg"></span>-->
		        </li>
	        </a>
        </#list>
    </ul>
    <div class="drop_down_bg"></div>
</div>
<div class="doctor">
    <div class="specialist_bg"></div>
    <ul class="doctor_ul">
	    <#list doctors as doctor>
	        <li class="doctor_li">
	            <div class="doctor_img left"></div>
	            <div class="doctor_xq left">
	                <div class="name">
	                    <span>${doctor.name}</span>
	                    <span>${doctor.positionName}</span>
	                </div>
	                <div class="point">${doctor.introduce}</div>
	                <div class="specialist">专家门诊</div>
	            </div>
	            <div class="doctor_more right">预约</div>
	        </li>
	      </#list>
    </ul>
</div>
<div class="height13"></div>
<div class="footer box">
    <div class="flex-1 footer_list">
        <div class="footer_bg04_active"></div>
        <div class="footer_color">首页</div>
    </div>
    <div class="flex-1 footer_list">
        <div class="footer_bg01"></div>
        <div>蓝鲟</div>
    </div>
    <div class="flex-1 footer_list">
        <div class="footer_bg02"></div>
        <div>走进蓝鲟</div>
    </div>
    <div class="flex-1 footer_list">
        <div class="footer_bg03"></div>
        <div>我的</div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="../js/jquery.js"></script>
<script src="../js/specialist.js"></script>