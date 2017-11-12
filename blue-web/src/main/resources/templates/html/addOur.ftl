<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>加入我们</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/join_us.css" />
</head>
<body style="background:#fff;">
<div class="heade">
    <div class="heade_text">加入我们</div>
    <div class="heade_back">返回</div>
</div>

<div class="company_box">
    <div class="company_inner">
        <div class="company_logo">
            <img src="../images/company_logo.png">
            <b>南京蓝鲟口腔医院</b>
        </div>
        <div class="info_labels">
            <span class="com_mail">lxkq369@126.com</span>
            <span class="com_telephone">025-52251111</span>
        </div>
    </div>
</div>

<ul class="job_list">
	<#list needWorks as needWork>
		<a href="/workNeedDetial/${needWork.sid}">
		<li>                     
	        <h4><span class="salary">${needWork.wages}</span>${needWork.title}</h4>
	        <div class="info_labels">
	            <span>招${needWork.needNum}人</span><em></em>
	            <span>${needWork.education}</span><em></em>
	            <span>${needWork.experience}</span>
	        </div>
	    </li>
	    </a>
    </#list>
</ul>
</body>
</html>