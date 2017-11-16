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
            $('.qr_code').on('click',function(event){
                event.stopPropagation();
                $('.qr_code_big').show();
                $('.qr_code_big_bg').show();
            });
            $('.qr_code_big').on('click',function(event){
                event.stopPropagation();
                template._hidden();
            });
            $('.qr_code_big_bg').on('click',function(event){
                event.stopPropagation();
                template._hidden();
            });
            $('.small_list>div').on('click',function(){
                $(this).addClass('small_img_boder').siblings().removeClass('small_img_boder');
                var $url = $(this).css("background-image").split("\"")[1];
                $('.big_img_div>img').attr('src',$url);
            })
        },
        _hidden:function(){
            $('.qr_code_big').hide();
            $('.qr_code_big_bg').hide();
        }
    };
    template.init();
})();