<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>登录</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/log_in.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">登录</div>
    <div class="heade_back">返回</div>
</div>
<div class="log_in"></div>
<div class="register">
    <div class="register_02">
        <div class="left phone_bg"></div>
        <input type="text" class="left phone" placeholder="请输入11位手机号"/>
    </div>
    <div class="register_03">
        <div class="left code_bg"></div>
        <input type="text" class="left code" placeholder="请输入验证码"/>
        <input type="button" class="right code_gain" value="获取验证"/>
    </div>
</div>
<div class="register_submit">登录</div>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../js/log_in.js"></script>