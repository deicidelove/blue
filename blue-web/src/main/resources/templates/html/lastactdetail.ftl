<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>最近活动-详情</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/project_center_xq.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">最近活动详情</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
</div>
<div class="project_bg">
	<img class='project_bg'  src="${(lastact.lastActListImg)! }"/>
</div>
<div class="project_cont">
    <div class="introduce">
        <div class="introduce_bg01">
            <div class="introduce_text04">${(lastact.lastActName)! }</div>
            <div class="introduce_text05">${(lastact.lastActEnName)! }</div>
            <div class="introduce_text06">${(lastact.lastActTitle)! }</div>
            <div class="introduce_text06">${(lastact.lastActContent)! }</div>
        </div>
        <div class="introduce_bg02"></div>
    </div>

</div>
</body>
</html>