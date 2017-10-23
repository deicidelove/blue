/**
 * Created by lonvov2 on 2017/10/19.
 */
(function () {
    var jinzhi=1;
    var code=1;
    var specialist = {
        init:function () {
            specialist._bind();
        },
        _bind:function () {
            $('.drop_down_btn').on('click',function(){
                if( code ==1){
                    specialist._ulshow();
                    code=0;
                    jinzhi=0;
                }else{
                    specialist._ulhide();
                    code=1;
                    jinzhi=1;
                }
            });
            $('.drop_down_bg').on('click',function(){
                    specialist._ulhide();
                    code=1;
                    jinzhi=1;
            });
            $('.down_li').on('click',function(){
                specialist._ulhide();
                code=1;
                jinzhi=1;
                var $text = $(this).find('span').first().text();
                var $span = '<span class="right down_li_bg"></span>';
                $(this).addClass('down_li_color').siblings().removeClass('down_li_color');
                $(this).append($span);
                $(this).siblings().find('.down_li_bg').remove();
                $('.drop_down_span01').text($text);
            });
        },
        _ulshow:function(){
            $('.down_ul').show();
            $('.drop_down_bg').show();
            $('.drop_down_span02').hide();
            $('.drop_down_span03').show();
        },
        _ulhide:function(){
            $('.down_ul').hide();
            $('.drop_down_bg').hide();
            $('.drop_down_span02').show();
            $('.drop_down_span03').hide();
        }
    };
    specialist.init();
    document.addEventListener("touchmove",function(e){
        if(jinzhi==0){
            e.preventDefault();
            e.stopPropagation();
        }
    },false);
})();