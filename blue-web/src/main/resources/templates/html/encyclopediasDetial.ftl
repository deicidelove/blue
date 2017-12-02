<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>口腔百科详情</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/inform.css" />
    <link rel="stylesheet" href="../css/baike.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">口腔百科详情</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back" style="color: white;">返回</div></a>
</div>
<div class="inform_cont">
    <div class="inform_bg01">
        <div class="inform_head">${bean.title!}</div>
        <div class="inform_time">2017-09-22 14:55:07</div>
        <img src="${bean.url!}" alt="" class="inform_img"/>
        <p class="inform_p">
           ${bean.context!}
        </p>
</div>
<div class="inform_bg02"></div>
</body>
</html>