/**
 * Created by lonvov2 on 2017/10/12.
 */
(function () {
	
    var buyjifen = {
        init: function () {
        	buyjifen._bind();
        },
        _bind: function () {
        	$("#jifenCount").on("change", function(){
        		var count = $(this).val();
        	
        		if(!reg.test(count) && count >= 10){
        			alert("请输入大于10的正整数！");
        			return;
        		}
        		$("#czFrePage").html("");
        		$("#czFrePage").html("￥"+ count/10);
        		$("#czFre").val(count/10);
        		});
        	//确认按钮
        	$('#buyjifen').on('click',function(){
        		var czFre = $("#czFre").val();
        		Request.sendPostRequest(basePath + "/personal/buyjifen/", {
        			"goodsId" : "-1",
        			"czFre" : czFre
        		}, function(result) {
        			if(result.status == 'fail'){
        				alert(result.message);
        				return ;
        			}else if( result.status == 'success'){
        				payParam.appId = result.appId;
        				payParam.timeStamp = result.timeStamp;
        				payParam.nonceStr = result.nonceStr;
        				payParam.package1 =  result.package;
        				payParam.signType = result.signType;
        				payParam.paySign = result.paySign;
        				pay();
        			}
        			
        		});
        	});
        }
        	
    };
    buyjifen.init();
})();

var reg = /^\+?[1-9]\d*$/;


//唤起微信支付  
function pay(){    
    if (typeof WeixinJSBridge == "undefined"){    
       if( document.addEventListener ){    
           document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);    
       }else if (document.attachEvent){    
           document.attachEvent('WeixinJSBridgeReady', onBridgeReady);     
           document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);    
       }    
    }else{ 
    	 onBridgeReady();
    }     

} 

//开始支付  
function onBridgeReady(){
    WeixinJSBridge.invoke(    
        'getBrandWCPayRequest', {    
            "appId" : payParam.appId,     //公众号名称，由商户传入         
            "timeStamp": payParam.timeStamp,         //时间戳，自1970年以来的秒数         
            "nonceStr" : payParam.nonceStr, //随机串         
            "package" : payParam.package1,         
            "signType" : payParam.signType,         //微信签名方式:         
            "paySign" : payParam.paySign    //微信签名     
        },    
        function(res){
            if(res.err_msg == "get_brand_wcpay_request:ok" ) {    
                alert("支付成功");  // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。     
                //回到用户订单列表  
            }else if (res.err_msg == "get_brand_wcpay_request:cancel")  {  
            	payfailAction();
                alert("支付过程中用户取消");
            }else{  
               //支付失败  
            	payfailAction();
               alert(res.err_msg);
               
            }       
        }    
    );     
 } 

function payfailAction (){
	 Request.sendPostRequest(basePath + "/personal/payfail/", {
			"outTradeId" : payParam.outTradeId
		}, function(result) {
			
		});
}

var payParam = {
        "appId" : "",     //公众号名称，由商户传入         
        "timeStamp": "",         //时间戳，自1970年以来的秒数         
        "nonceStr" : "", //随机串         
        "package1" : "",         
        "signType" : "",         //微信签名方式:         
        "paySign" : "",    //微信签名     
        "outTradeId":""      //微信唯一订单Id
};