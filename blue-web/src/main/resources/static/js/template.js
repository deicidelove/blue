/**
 * Created by lonvov2 on 2017/10/19.
 */
(function () {
    var template = {
        init:function () {
            template._bind();
        },
        _bind:function () {
            $('.heade_back').on('click',function(){
                window.history.back();
            });
            $('.small_list>div').on('click',function(){
                $(this).addClass('small_img_boder').siblings().removeClass('small_img_boder');
                var $url = $(this).css("background-image").split("\"")[1];
                $('.big_img_div>img').attr('src',$url);
                var type = $(this).attr("type");
                if(urlMaps){
                	if(urlMaps[type]){
                		$('.big_img_div>img').attr('src',urlMaps[type]);
                		$('.qr_code').hide();
                	}else{
                		$('.qr_code').show();
                	}
                }
            });
            $("#generateImg").on('click', function(){
            	var originalUrl = $(".big_img_div img").attr("src");
            	var type = $(".small_img_boder").attr("type");
            	if($('.qr_code').is(":hidden")){
            		alert("二维码已生成，请直接下载分享！");
            		return ;
            	}
        		Request.sendPostRequest(basePath + "/getCombinePic", {
        			"type":type,
        			"fileUrl" : originalUrl,
        			"openId" : openId
        		}, function(result) {
        			if( result.result == 'success'){
        				var urlObj = eval('('+result.message+')');
        				$('.big_img_div>img').attr('src',urlObj.showFilePath);
        				$('.qr_code').hide();
        				urlMaps[type] = urlObj.showFilePath;
        			}else {
        				alert(result.message);
        				return ;
        			}
        			
        		});
            });
        }
    };
    template.init();
    
    if(urlMaps){
    	$.each(urlMaps,function(key,value){  
    		if(key && value){
        		$("div[type="+key+"]").trigger("click");
        		return true;
        	}
    	  }); 
    	$("div[type="+1+"]").trigger("click");
    }
    
})();