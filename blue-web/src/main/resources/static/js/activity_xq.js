/**
 * Created by lonvov2 on 2017/10/12.
 */
(function () {
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
            })
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
