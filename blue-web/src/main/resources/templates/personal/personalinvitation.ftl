<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>我的邀请</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/activity_xq.css" />
    <link rel="stylesheet" href="../css/user_centerw.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">我的邀请</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
</div>

<ul class="yaoqing_ul">
		<#if resultList??>
			<#list resultList as wxEntity >
			    <li class="activity_li">
			        <img src="${wxEntity.pic }" class="yaoqing_img" />
			        <div class="activity_li_div02 left">
			            <div>${wxEntity.name }</div>
			            <div>${wxEntity.createTime }</div>
			        </div>
			    </li>
			</#list>
		</#if>
</ul>

</body>
</html>
