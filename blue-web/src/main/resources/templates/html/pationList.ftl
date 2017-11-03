<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>选择就诊人</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/select_people.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">选择就诊人</div>
    <div class="heade_back">返回</div>
    <a href="/managePationPage"><div class="heade_manage">管理</div></a>
</div>
<ul class="select_ul">
	<#list pations as pation>
    	<a href="/orderInfoPage${pation.sid}&${scheduleId}/"><li class="select_li">${pation.name}</li></a>
    </#list>
</ul>
</body>
</html>