/**
 * Created by lonvov2 on 2017/10/19.
 */
(function () {
    var template = {
        init:function () {
            template._bind();
        },
        _bind:function () {
            $('.small_list>div').on('click',function(){
                $(this).addClass('small_img_boder').siblings().removeClass('small_img_boder');
                var $url = $(this).css("background-image").split("\"")[1];
                console.log($url);
                $('.big_img_div>img').attr('src',$url);
            })
        }
    };
    template.init();
})();