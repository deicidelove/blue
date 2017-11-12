<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>近期活动</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/activity_list.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">近期活动</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
</div>

<ul class="activity_list">
	<#if lastActList??>
		<#list lastActList as lastAct>
		<a href = "/lastact/detail?lastActId=${(lastAct.sid)! }">
			<li>
		    	<img src="${(lastAct.lastActListImg)! }" />
		        <div class="list_tex">
		        	<strong>${(lastAct.lastActName)! }</strong>
		            <p>${(lastAct.lastActTitle)! }</p>
		        </div>
		    </li>
		</a>
		</#list>
	</#if>

</ul>


</body>
</html>