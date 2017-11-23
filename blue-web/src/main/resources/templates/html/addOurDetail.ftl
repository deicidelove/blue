<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>加入我们</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/join_us.css" />
</head>
<body class="gray_bg">
<div class="heade">
    <div class="heade_text">加入我们</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
</div>

<div class="btn_bspace">
    <div class="join_con">            
        <h4><span class="salary">${work.wages!}</span>${work.title!}</h4>
        <div class="join_infor">
            <span class="com_pernum"><#if work.needNum??>招${work.needNum!}人</#if></span>
            <span class="com_edu"><#if work.education??>${work.education!}</#if></span>
            <span class="com_years"><#if work.experience??>${work.experience!}</#if></span>
        </div>
    </div>
    <div class="com_timeadres">
        <p>工作时间：<#if work.workTime??> ${work.workTime}</#if></p>
        <p>工作地址：<#if work.workAddress??>${work.workAddress}</#if></p>
    </div>
    
    <div class="join_block mt_space">
        <div class="title"><span class="com_des">职位描述</span></div>
        <div class="desc">
         <#if  work.description??>
            ${work.description}
         </#if>
         </div>
    </div>
    
    <div class="join_block mt_space">
        <div class="title"><span class="com_trophy">职位要求</span></div>
        <div class="desc">
        <#if  work.requirement??>
            ${work.requirement}
         </#if>
         </div>
    </div>
    
    <div class="join_block mt_space">
        <div class="title"><span class="com_welfare">福利待遇</span></div>
        <div class="desc">
        <#if work.fringeBenefits??>
            ${work.fringeBenefits}
        </#if>
        </div>
    </div>
</div>    

<a href="/addOurNowPage"><div class="infor_btn">立即申请该职位</div></a>

</body>
</html>

