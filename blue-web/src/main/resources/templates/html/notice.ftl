<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>重要通知</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/encyclopedia.css" />
    <link rel="stylesheet" href="../css/article_det.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">重要通知</div>
    <div class="heade_back">返回</div>
</div>
<a href="/noticeDetialPage/${advertsFirst.sid}">
<div class="oral_an notice_an brdt_nospace">
    <#if advertsFirst.url??>
   		 <img class="artical_an" src="${advertsFirst.url}" />
    <#else>
     	<img class="artical_an" src="../images/notice_img01.jpg" />
    </#if>
    <div class="oral_an_head">${advertsFirst.title}</div>
</div>
</a>
<ul class="encyclopedia_ul notice_ul">
    <#list adverts as advert>
    <a href="/noticeDetialPage/${advert.sid}">
	    <li class="encyclopedia_li">
	         <#if advert.url??>
	   			 <img class="artical_an" src="${advert.url}" />
		    <#else>
		     	<img class="left artical_img" src="../images/notice_img02.jpg" />
		    </#if>
	        
	        <div class="left encyclopedia_li_cent">
	            <div>${advert.title}</div>
	            <div>${advert.context}</div>
	        </div>
	    </li>
    </a>
    </#list>
</ul>

</body>
</html>
