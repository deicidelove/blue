/**
 * Created by lonvov2 on 2017/10/17.
 */
(function () {
    var jinzhi=0;
    var code=1;
    var integral = {
        init:function () {
            integral._bind();
        },
        _bind:function () {
            $('.integral_tab01').on('click',function(){
                $(this).addClass('_color').siblings().removeClass('_color');
                $('.integral_tab_bg01').addClass('integral_tab_bg01_active');
                $('.integral_tab_bg02').removeClass('integral_tab_bg02_active');
                $('.integral_ul').hide();
                $('.unveiled_ul').show();
            });
            $('.integral_tab02').on('click',function(){
                $(this).addClass('_color').siblings().removeClass('_color');
                $('.integral_tab_bg02').addClass('integral_tab_bg02_active');
                $('.integral_tab_bg01').removeClass('integral_tab_bg01_active');
                $('.integral_ul').show();
                $('.unveiled_ul').hide();
            });
            $('.help_btn01').on('click',function(){
                $('.help').hide();
                $('.help_bg').hide();
                jinzhi=1;
            });
            $('.help_btn02').on('click',function(){
                $('.help').hide();
                $('.help_bg').hide();
                jinzhi=1;
            });
            $('.help_labe').on('click',function(){
                if( code== 1){
                    $('.help_labe_bg').addClass('help_labe_bg_active');
                    code = 0;
                }else{
                    $('.help_labe_bg').removeClass('help_labe_bg_active');
                    code = 1;
                }
            })
        }
    };
    integral.init();
    document.addEventListener("touchmove",function(e){
        if(jinzhi==0){
            e.preventDefault();
            e.stopPropagation();
        }
    },false);
})();