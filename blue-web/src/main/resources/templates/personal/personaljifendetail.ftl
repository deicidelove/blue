<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>积分明细</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/user_centerw.css" />
</head>
<body>
    <div class="heade">
        <div class="heade_text">积分明细</div>
        <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back" style="color: white;">返回</div></a>
    </div>
    
    <ul class="jifen_det">
    	<#if resultList??>
    		<#list resultList as jifenEntity >
		    	<li>
		        	<p>
		        	<#if jifenEntity.type == 'qr_fx'>
		            	分享获得积分  
		            <#elseif jifenEntity.type == '2'>
						参与活动赢积分
		            <#elseif jifenEntity.type == '3'>
		            	充值获取积分
		            <#elseif jifenEntity.type == 'jifen_xf'>
		            	兑换抽奖
		            </#if>
		            	<#if !jifenEntity.isReverse>
		            	<strong class="right">+ ${jifenEntity.jifen }</strong> 
		            	<#else>
		            		<strong class="right">- ${jifenEntity.jifen }</strong> 
		            	</#if>
		            </p>
		            <p class="time"> ${jifenEntity.createTime } </p>
		        </li>
	        </#list>
    	</#if>
    </ul>
		
</body>
</html>
<script src="../js/jquery.js"></script>