<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>添加就诊人</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/add_people.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">添加就诊人</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back" style="color: white;">返回</div></a>
</div>
<ul class="infor_ul">
    <li class="infor_li">
        <div class="left infor_li_div">姓名</div>
        <input type="text" name="name" class="left infor_li_input" placeholder="请添加真实姓名"/>
    </li>
    <li class="infor_li">
        <div class="left infor_li_div">手机号</div>
        <input type="text" name="phone" class="left infor_li_input" placeholder="请添加真实的手机号"/>
    </li>
    <li class="infor_li_box">
        <div class="left infor_li_checkbox"></div>
        <div class="left infor_li_text">设为默认</div>
    </li>
</ul>
<div class="infor_btn">保存</div>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../js/addPation.js"></script>