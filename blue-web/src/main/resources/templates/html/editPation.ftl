<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>编辑就诊人</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/compile_people.css"/>
</head>
<body>
<div class="heade">
    <div class="heade_text">编辑就诊人</div>
    <div class="heade_back">返回</div>
</div>
<ul class="infor_ul">
    <li class="infor_li">
        <div class="left infor_li_div">姓名</div>
        <input type="text" name="name" class="left infor_li_input" placeholder="请添加真实姓名" value="${pation.sid}"/>
    </li>
    <!--<li class="infor_li">-->
        <!--<div class="left infor_li_div">身份证号</div>-->
        <!--<input type="text" class="left infor_li_input" placeholder="请添加真实号码" value="${pation.phone}"/>-->
    <!--</li>-->
    <li class="infor_li">
        <div class="left infor_li_div">手机号</div>
        <input type="text" name="phone" class="left infor_li_input" placeholder="请添加真实的手机号" value="${pation.phone}"/>
    </li>
    <li class="infor_li_box">
        <div class="left infor_li_checkbox"></div>
        <div class="left infor_li_text">设为默认</div>
    </li>
</ul>
<div class="infor_btn_remove" value="${pation.sid}">删除</div>
<div class="infor_btn_save" value="${pation.sid}">保存</div>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../js/editPation.js"></script>