<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>医院概况</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/general.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">医院概况</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
</div>
<div class="general_tab box">
    <div class="flex-1 active">医院简介</div>
    <div class="flex-1">百年蓝鲟</div>
    <div class="flex-1">医院环境</div>
</div>
<ul class="tab_ul">
    <li class="tab_li">
        <div class="intro">
            <div class="intro_bg" style="background-image: url(${hospitalJSA.url!})"></div>
            <div class="intro_cont">
                <div class="intro_cont_head">
                    <span class="left"></span>
                    <span class="left">推荐理由</span>
                </div>
                <div class="intro_cont_text">
          			<#if hospitalJSA??>
          				${hospitalJSA.context!}
          			</#if>
                </div>
                <div class="intro_cont_head">
                    <span class="left"></span>
                    <span class="left">科室特色</span>
                </div>
                <div class="intro_cont_text">
                 <#if hospitalJSB??>
                  ${hospitalJSB.context!}
                 </#if>
                </div>
            </div>
        </div>
    </li>
    <li class="tab_li displaynone">
       <#if hospitalBN1??>
        <div class="century_01" style="background-image: url(${hospitalBN1.url!})">
            <div>
              ${hospitalBN1.context!}
            </div>
        </div>
       </#if>
       <#if hospitalBN11??>
        <div class="century_02" style="background-image: url(${hospitalBN11.url!})">
            <div>
                <p>  
                	${hospitalBN11.context!}
                </p>
            </div>
        </div>
       </#if>
       <#if hospitalBN12??>
        <div class="century_03" style="background-image: url(${hospitalBN12.url!})">
            <div>
                <p>
                     ${hospitalBN12.context!}
                </p>
                
            </div>
        </div>
        </#if>
        <#if hospitalBN13??>
        <div class="century_04" style="background-image: url(${hospitalBN13.url!})">
            <div>
               ${hospitalBN13.context!}
            </div>
            <div>
               
            </div>
        </div>
        </#if>
         <#if hospitalBN14??>
        <div class="century_05" style="background-image: url(${hospitalBN14.url!})">
            <div>
                <p>
                     ${hospitalBN14.context!}
                
                </p>
            </div>
        </div>
       </#if>
    </li>
    <li class="tab_li displaynone">
        <div class="survey">
        <#list hospitalHJ0 as hj>
            <div class="survey_concet">
            	<#if hj.url ?? && hj.url !="">
                	<img src="${hj.url!}" alt=""/>
                <#else>
                	<img name="position" src="../images/icon-130.png" alt=""/>
                </#if>
                <div>${hj.context!}</div>
            </div>
         </#list>                      
        </div>
    </li>
</ul>
<div class="margin_bottom"></div>
<div class="footer box">
    <div class="flex-1 footer_list">
      <a href="/">
        <div class="footer_bg04_active"></div>
        <div class="footer_color">首页</div>
     </a>
    </div>
    <div class="flex-1 footer_list">
         <a href="/blueWebsite">
        <div class="footer_bg01"></div>
        <div>蓝鲟</div>
        </a>
    </div>
    <div class="flex-1 footer_list">
     <a href="/blueWebsite">
        <div class="footer_bg02"></div>
        <div>走进蓝鲟</div>
    </a>
    </div>
    <div class="flex-1 footer_list">
        <a href="/personal/index">
            <div class="footer_bg03"></div>
            <div>我的</div>
        </a>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="../js/jquery.js"></script>
<script src="../js/general.js"></script>