/**
 * Created by lonvov2 on 2017/10/31.
 */
(function () {
    var registration = {
        init: function () {
            registration._bind();
            registration.toweek();
        },
        _bind: function () {

        },
        toweek:function(){
            var dd = new Date();
            var week=dd.getDay();//获取当前星期X(0-6,0代表星期天)
            $('.month_day').eq(week).addClass('day');
            for(var i=0;i<7;i++){
                var daycount=i-week;
                var bak= registration.GetDateStr(daycount);
                $('.month_day').eq(i).text(bak[0]);
                $('.week').eq(i).text(bak[1]);
            }
        },
        GetDateStr:function(AddDayCount) {
            var backval = [];
            var dd = new Date();
            dd.setDate(dd.getDate() + AddDayCount);//获取AddDayCount天后的日期
            var d = dd.getDate();
            var week = dd.getDay();         //获取当前星期X(0-6,0代表星期天)
            var weekname = "日";
            switch (week)
            {
                case 1:
                    weekname = "一";
                    break;
                case 2:
                    weekname = "二";
                    break;
                case 3:
                    weekname = "三";
                    break;
                case 4:
                    weekname = "四";
                    break;
                case 5:
                    weekname = "五";
                    break;
                case 6:
                    weekname = "六";
                    break;
            }
            var backday = d;
            var backweek = weekname;
            backval.push(backday, backweek);
            return backval;
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
    registration.init();
})();
