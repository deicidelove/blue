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
            });
            $("#generateImg").on('click', function(){
            	var imgUrl = $(".big_img_div img").attr("src")
        		Request.sendPostRequest(basePath + "/personal/generateimg", {
        			"imgUrl" : imgUrl
        		}, function(result) {
        			if(result.result_code == 'fail'){
        				alert(result.result_msg);
        				return ;
        			}else if( result.result_code == 'success'){
        				alert(result.result_msg)
        			}
        			
        		});
            });
        }
    };
    template.init();
})();