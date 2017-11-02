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
    <div class="heade_back">返回</div>
</div>
<div class="general_tab box">
    <div class="flex-1 active">医院简介</div>
    <div class="flex-1">百年蓝鲟</div>
    <div class="flex-1">医院环境</div>
</div>
<ul class="tab_ul">
    <li class="tab_li">
        <div class="intro">
            <div class="intro_bg"></div>
            <div class="intro_cont">
                <div class="intro_cont_head">
                    <span class="left"></span>
                    <span class="left">推荐理由</span>
                </div>
                <div class="intro_cont_text">
          			${hospitalJSA.context}
                </div>
                <div class="intro_cont_head">
                    <span class="left"></span>
                    <span class="left">科室特色</span>
                </div>
                <div class="intro_cont_text">
                  ${hospitalJSB.context}
                </div>
            </div>
        </div>
    </li>
    <li class="tab_li displaynone">
        <div class="century_01">
            <div>
              ${hospitalBN1.context}
            </div>
        </div>
        <div class="century_02">
            <div>
                <p> ${hospitalBN11.context}
                </p>
                
            </div>
        </div>
        <div class="century_03">
            <div>
                <p>
                     ${hospitalBN12.context}
                </p>
                
            </div>
        </div>
        <div class="century_04">
            <div>
               ${hospitalBN13.context}
            </div>
            <div>
               
            </div>
        </div>
        <div class="century_05">
            <div>
                <p>
                     ${hospitalBN14.context}
                </p>
yu            </div>
        </div>
    </li>
    <li class="tab_li displaynone">
        <div class="survey">
        <#list hospitalHJ0 as hj>
            <div class="survey_concet">
            	<#if hj.url ?? && hj.url !="">
                	<img src="${hj.url}" alt=""/>
                <#else>
                	<img name="position" src="../images/icon-130.png" alt=""/>
                </#if>
                <div>${hj.context}</div>
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
        <div class="footer_bg03"></div>
        <div>我的</div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="../js/jquery.js"></script>
<script src="../js/general.js"></script>