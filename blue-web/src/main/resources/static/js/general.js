/**
 * Created by lonvov2 on 2017/10/19.
 */
(function () {
    var general = {
        init:function () {
            general._bind();
        },
        _bind:function () {
            $('.general_tab>div').on('click',general._tabToggle);
        },
        _tabToggle:function(){
            var $tab_li = $('.tab_li');
            var $index = $(this).index();
            $(this).addClass('active').siblings().removeClass('active');
            $tab_li.eq($index).show().siblings().hide();
        },
        _popx: function(text, time, cb) {
            time = time * 1e3;
            var pop_info = "<span class='span-tip'>" + text + "</span>";
            $("body").append(pop_info);
            $(".span-tip").css("margin-left", function() {
                return -$(".span-tip")[0].offsetWidth / 2;
            });
            $(".span-tip").fadeOut(time, function() {
                $(this).remove();
                if (cb && typeof cb == "function") {
                    cb();
                }
            });
        }
    };
    general.init();
})();