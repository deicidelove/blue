<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>立即申请</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/join_us.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">立即申请</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back" style="color: white;">返回</div></a>
</div>
<ul class="infor_ul">
    <li class="infor_li">
        <div class="left infor_li_div">姓名</div>
        <div class="left infor_li_input"><input type="text" value="" placeholder="请输入您的真实姓名" name="addName"></div>
    </li>
    <li class="infor_li">
        <div class="left infor_li_div">电话</div>
        <div class="left infor_li_input"><input type="text" value="" placeholder="请输入您的常用电话" name="phone"></div>
    </li>
    <li class="infor_li mt_space">
        <div class="left infor_li_div">专业</div>
        <div class="left infor_li_input"><input type="text" value="" placeholder="比如：牙齿保健" name="educattion"></div>
    </li>
    <li class="infor_li">
        <div class="left infor_li_div">求职意向</div>
        <div class="left infor_li_input"><input type="text" value="" placeholder="比如：医疗销售" name="wantJob"></div>
    </li>
    <li class="infor_li">
        <div class="left infor_li_div">工资需求</div>
        <div class="left infor_li_input"><input type="text" value="" placeholder="请输入您做期待的薪资水平" name="wageWant"></div>
    </li>
    
    <li class="infor_li mt_space">
        <div class="infor_li_div" >工作描述</div>  
    </li>
    <li class="infot_texarea">
        <textarea class="ta_itemtext_con" placeholder="请简要描述您的工作经历及工作技能" name="describe"></textarea>
    </li>
</ul>
<p class="job_tip btn_bspace">您也可发送简历至邮箱：lxkq369@126.com</p>
<div class="infor_btn">提交</div>
</body>
</html>
<script type="text/javascript" src="../js/jquery.js"></script>
<script src="../js/addNow.js?v=2"></script>