<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>我的订单</title>
    <script src="../js/common.js"></script>
    <script src="../js/bluecommon.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/confirm_order.css" />
    <link rel="stylesheet" href="../css/user_centerw.css" />
</head>
<script>
var basePath = '${request.contextPath}';
</script>
<body>
    <div class="heade">
        <div class="heade_text">我的订单</div>
        <div class="heade_back">返回</div>
    </div>
    <#if resultList??>
    	<#list resultList as orderDTO >
	    	<#if (orderDTO.orderEntity)?? && (orderDTO.goodsEntity)??>
			    <div class="mgb_space">
			        <ul class="confirm_ul">
			            <li class="confirm_li">
			                <div class="order_li_left left">
			                	<#if (orderDTO.goodsImgEntity)??>
			                	<img src="${orderDTO.goodsImgEntity.goodsImgUrl }" />
			                	</#if>
			                </div>
			                <div class="confirm_li_center left">
			                   ${orderDTO.goodsEntity.goodsName }
			                </div>
			                <div class="confirm_li_right right">
			                    <div>￥ ${orderDTO.goodsEntity.goodsPrice}</div>
			                    <div>x ${orderDTO.orderEntity.goodsNum }</div>
			                </div>
			            </li>
			        </ul>
			        <div class="order_total">
			        	<#if orderDTO.orderEntity.type = "wxPayCode">
			            <span>共${orderDTO.orderEntity.goodsNum }份 合计：￥<strong>${orderDTO.orderEntity.price! }</strong></span>
			            <#else>
			            <span>共${orderDTO.orderEntity.goodsNum }份 合计：积分<strong>${orderDTO.orderEntity.jifen! }</strong></span>
			            </#if>
			        </div>
			        <div class="order_set">
			        	<#if orderDTO.orderEntity.status == "wzf">
				            <div class="left color_org">未付款</div>
				            <div code="${orderDTO.orderEntity.orderId }" class="right graybr_btn" name ="deleteOrder">取消订单</div>
			            <#elseif orderDTO.orderEntity.status == "yzf">
				            <div class="left color_org">已完成</div>
				            <div code="${orderDTO.orderEntity.orderId }" class="right graybr_btn" name ="deleteOrder">删除订单</div>
			            </#if>
			        </div>
			    </div>    		
	    	</#if>
    	</#list>
    </#if>

</body>
</html>
<script src="../js/jquery.js"></script>
<script>
	$("div[name='deleteOrder']").on("click",function(){
		var orderId = $(this).attr("code");
		if(!orderId){
			return;
		}
		Request.sendPostRequest(basePath + "/personal/deleteOrder", {
			"orderId" : parseInt(orderId)
		}, function(result) {
			alert(result.msg);
			location.reload();
		});
	});
</script>