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
            $('.share_btn').on('click',activity._hide);
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
