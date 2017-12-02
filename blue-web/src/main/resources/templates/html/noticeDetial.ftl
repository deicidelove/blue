<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>重要通知-详情</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/inform.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">通知公告</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back" style="color: white;">返回</div></a>
</div>
<div class="inform_cont">
    <div class="inform_bg01">
        <div class="inform_head">${advert.title!}</div>
        <div class="inform_time">2017-09-22 14:55:07</div>
        <#if advert.url??>
        	<img src="${advert.url!}" alt="" class="inform_img"/>
        <#else>
        	<img src="../images/icon-122.png" alt="" class="inform_img"/>
        </#if>
        <p class="inform_p">
          ${advert.context!} 
        </p>
       
    <div class="inform_bg02"></div>
</div>
</body>
</html>