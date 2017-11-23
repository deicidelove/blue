<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>口腔百科</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/encyclopedia.css" />
    <link rel="stylesheet" href="../css/article_det.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">口腔百科</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back">返回</div></a>
</div>

<#assign i=1>
<#list encys as ency>
	<#if (i+3)%4==0>
		<a href="/encyclopediasDetial/${ency.sid}">
			<div class="oral_an notice_an brdt_nospace">
			     <#if ency.url??>
			   		<img class="artical_an" src="${ency.url}" />
			     <#else>
			     	<img class="artical_an" src="../images/notice_img01.jpg" />
			     </#if>
			    <div class="oral_an_head">${ency.title}</div>
			</div>
		</a>
	<#else>
		<#if (i+3)%4==1>
		<ul class="encyclopedia_ul notice_ul">
		</#if>
		    <li class="encyclopedia_li">
			     <a href="/encyclopediasDetial/${ency.sid}">
			         <#if ency.url??>
			   			 <img class="left artical_img" src="${ency.url}" />
				     <#else>
				     	<img class="left artical_img" src="../images/notice_img02.jpg" />
				     </#if>
			        <div class="left encyclopedia_li_cent">
			            <div>${ency.title}</div>
			            <div name="clearHtml">${ency.context}</div>
			        </div>
			      </a>
		    </li>
		<#if (i+3)%4==3>
		</ul>
		</#if>
	</#if>
<#assign i=i+1>
</#list>


</body>
</html>
<script src="../js/jquery.js"></script>
<script>
 		$("[name=clearHtml]").each(function () {
            var that = $(this);
            var textNoHtml = that.text();
			that.text(textNoHtml); 
        });
</script>
