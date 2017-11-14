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
<script>
var basePath = '${request.contextPath}';
</script>
<body>
<div class="heade">
    <div class="heade_text">蓝鲟专家</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
</div>
<div class="search">
	<#if search??>
	<input name="search" type="text" class="search_input" placeholder="${search}" value="${search}"/>
	<#else>
	<input name="search" type="text" class="search_input" placeholder="搜索疾病，医生姓名"/>
	</#if>
    
    <div class="search_btn"></div>
</div>
<div class="drop_down">
	
    <div class="drop_down_btn">
    	<#if flag>
        <span class="left drop_down_span01">请选择科室</span>
        <#else>
	     <span class="left drop_down_span01">${deptName}</span>
	     </#if>
        <span class="left drop_down_span02"></span>
        <span class="left drop_down_span03"></span>
    </div>
    
    <ul class="down_ul">
        <#list  depts as dept>
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
<#list doctors as temp>
    <div class="specialist_bg" style="background-image:url(${temp.url})"></div>
    <ul class="doctor_ul">
	    <#list temp.doctors as doctor>
	        <a href="/doctorDetial/${doctor.sid}" >
	        <li class="doctor_li">
                <#if doctor.headUrl??>
                <div class="doctor_img left" style="background-image: url(${doctor.headUrl})"></div>
                <#else>
                <div class="doctor_img left" ></div>
                </#if>
	            
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
	        </a>
	      </#list>
    </ul>
</#list>
</div>
<div class="height13"></div>
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
<script type="text/javascript" src="../js/jquery.js"></script>
<script src="../js/specialist.js"></script>