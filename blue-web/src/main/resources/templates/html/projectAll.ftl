<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>所有项目</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/encyclopedia.css" />
    <link rel="stylesheet" href="../css/article_det.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">所有项目</div>
    <div class="heade_back">返回</div>
</div>

<ul class="encyclopedia_ul notice_ul">
    <#list projects as project>
    <a href="/projectDetial/${project.sid}">
	    <li class="encyclopedia_li">
	         <#if project.url??>
	   			 <img class="artical_an" src="${project.url}" />
		    <#else>
		     	<img class="left artical_img" src="../images/notice_img02.jpg" />
		    </#if>
	        
	        <div class="left encyclopedia_li_cent">
	            <div>${project.name}</div>
	            <div>${project.context}</div>
	        </div>
	    </li>
    </a>
    </#list>
</ul>

</body>
</html>
