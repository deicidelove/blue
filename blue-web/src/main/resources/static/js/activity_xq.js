/**
 * Created by lonvov2 on 2017/10/12.
 */
(function () {
	
    var code01 = 1;
    var code02 = 1;
    var code03 = 1;
    var jinzhi=1;
    var activity = {
        init: function () {
            activity._bind();
        },
        _bind: function () {
            $('.infor_btn').on('click',function(){
                $('.share').show();
                $('.share_bg').show();
                jinzhi=0;
            });
            $('.close_btn').on('click',activity._hide);
            $('.share_bg').on('click',activity._hide);
            //确认按钮
            $('.share_btn').on('click',function(){
            	var exchangeType =  $("div[way='way'].left.active").attr("exchangeType");
            	if("jifen" == exchangeType){
            		Request.sendPostRequest(basePath + "/jifen/createGoodsOrder/", {
            			"goodsId" : goodsId
            		}, function(result) {
            			if(result.status == 'fail'){
            				alert(result.message);
            				return ;
            			}else if( result.status == 'success'){
            				alert(result.message);
            			}
            		});
            	}else if("xianjin" == exchangeType){
            		Request.sendPostRequest(basePath + "/pay/createGoodsOrder/", {
            			"goodsId" : goodsId
            		}, function(result) {
            			payParam.appId = result.appId;
            			payParam.timeStamp = result.timeStamp;
            			payParam.nonceStr = result.nonceStr;
            			payParam.package =  result.packageValue;
            			payParam.signType = result.signType;
            			payParam.paySign = result.paySign;
            			pay();
            			
            		});
            	}
            	activity._hide();
            });
            $('.way_botm>div').on('click',function(){
                $(this).addClass('active').siblings().removeClass('active');
            });
            $('.record_btn01').on('click',function(){
                if( code01 ==1 ){
                    $(this).children().last('div').addClass('active');
                    $('.activity_ul').fadeIn(200);
                    code01 = 0;
                }else{
                    $(this).children().last('div').removeClass('active');
                    $('.activity_ul').fadeOut(100);
                    code01 = 1;
                }
            });
            $('.record_btn02').on('click',function(){
                if( code02 ==1 ){
                    $(this).children().last('div').addClass('active');
                    $('.particulars').fadeIn(200);
                    code02 = 0;
                }else{
                    $(this).children().last('div').removeClass('active');
                    $('.particulars').fadeOut(100);
                    code02 = 1;
                }
            });
            $('.record_btn03').on('click',function(){
                if( code03 ==1 ){
                    $(this).children().last('div').addClass('active');
                    $('.activity_num').fadeIn(200);
                    code03 = 0;
                }else{
                    $(this).children().last('div').removeClass('active');
                    $('.activity_num').fadeOut(100);
                    code03 = 1;
                }
            });
        },
        _hide:function(){
            $('.share').hide();
            $('.share_bg').hide();
            jinzhi=1;
        }
    };
    activity.init();
    document.addEventListener("touchmove",function(e){
        if(jinzhi==0){
            e.preventDefault();
            e.stopPropagation();
        }
    },false);
})();

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
            "package" : payParam.package,         
            "signType" : payParam.signType,         //微信签名方式:         
            "paySign" : payParam.paySign    //微信签名     
        },    
            
        function(res){         
            if(res.err_msg == "get_brand_wcpay_request:ok" ) {    
                alert("支付成功");  // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。     
                //回到用户订单列表  
            }else if (res.err_msg == "get_brand_wcpay_request:cancel")  {  
                alert("支付过程中用户取消");
            }else{  
               //支付失败  
               alert(res.err_msg)  
            }       
        }    
    );     
 } 

function afterPayAction (){
	 Request.sendPostRequest(basePath + "/pay/afterPayAction/", {
			"goodsId" : goodsId
		}, function(result) {
			
		});
}

var payParam = {
        "appId" : "",     //公众号名称，由商户传入         
        "timeStamp": "",         //时间戳，自1970年以来的秒数         
        "nonceStr" : "", //随机串         
        "package" : "",         
        "signType" : "",         //微信签名方式:         
        "paySign" : ""    //微信签名     
};