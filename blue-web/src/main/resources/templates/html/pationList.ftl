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
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
    <a href="/managePationPage"><div class="heade_manage">管理</div></a>
</div>
<ul class="select_ul">
<#if pations??>
	<#list pations as pation>
    	<li class="select_li" sid="${pation.sid}" scheduleId="${scheduleId}">${pation.name!}</li>
    </#list>
</#if>
</ul>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../js/selectPation.js"></script>