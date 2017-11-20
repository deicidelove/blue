<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>儿科科室</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/department.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">${dept.name}</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
</div>
<div class="health_bg" style="background-image: url(${deptBg.url})">
</div>
<div class="health_content" >
    <div class="introduce">
        <div class="headline">科室介绍</div>
        <div class="introduce_cont">
            ${dept.context}
        </div>
    </div>
    <div class="doctor">
        <div class="headline">科室医生</div>
        <ul class="doctor_ul">
            <#list doctors as doctor>
	            <li class="doctor_li">
	                <a href="/doctorDetial/${doctor.sid}/-1">
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
	                <div class="doctor_more right"></div>
                     </a>
	            </li>
            </#list>
        </ul>
    </div>
</div>
</body>
</html>