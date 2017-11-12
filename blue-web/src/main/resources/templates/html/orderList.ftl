<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>预约挂号</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/registration.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">预约挂号</div>
    <div class="heade_back">返回</div>
</div>
<div class="reg_date box">
    <div class="flex-1">
        <div class="week"></div>
        <div class="month_day"></div>
    </div>
    <div class="flex-1">
        <div class="week"></div>
        <div class="month_day"></div>
    </div>
    <div class="flex-1">
        <div class="week"></div>
        <div class="month_day"></div>
    </div>
    <div class="flex-1">
        <div class="week"></div>
        <div class="month_day"></div>
    </div>
    <div class="flex-1">
        <div class="week"></div>
        <div class="month_day"></div>
    </div>
    <div class="flex-1">
        <div class="week"></div>
        <div class="month_day"></div>
    </div>
    <div class="flex-1">
        <div class="week"></div>
        <div class="month_day"></div>
    </div>
</div>
<div class="doctor_bg">
    <div class="doctor">
        <ul class="doctor_ul">
            <#list doctors as doctor>
            <a href="/doctorDetial/${doctor.sid}" >
            <li class="doctor_li">
	             <#if doctor.headUrl??>
			    <div class="doctor_img left" style="background-image: url(${doctor.headUrl})"></div>
			    <#else>
			    <div class="doctor_img left"></div>
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
<script type="text/javascript" src="../js/jquery.js"></script>
<script src="../js/registration.js"></script>