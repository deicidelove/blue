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
    <div class="heade_back">返回</div>
</div>

<ul class="encyclopedia_ul notice_ul">
    <#list encys as ency>
    <a href="/encyclopediasDetial/${ency.sid}">
	    <li class="encyclopedia_li">
	         <#if ency.url??>
	   			 <img class="left artical_img" src="${ency.url}" />
		    <#else>
		     	<img class="left artical_img" src="../images/notice_img02.jpg" />
		    </#if>
	        <div class="left encyclopedia_li_cent">
	            <div>${ency.title}</div>
	            <div>${ency.context}</div>
	        </div>
	    </li>
    </a>
    </#list>
</ul>

</body>
</html>
