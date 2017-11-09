/**
 * Created by lonvov2 on 2017/10/24.
 */
(function () {
    var code = 1;
    var jinzhi=1;
    var subs = {
        init: function () {
            subs._bind();
            subs.toweek();
        },
        _bind: function () {
            $('.record_btn01').on('click',function(){
                if( code ==1 ){
                    $(this).children().last('div').addClass('active');
                    $('.abstract').show();
                    code = 0;
                }else{
                    $(this).children().last('div').removeClass('active');
                    $('.abstract').hide();
                    code = 1;
                }
            });
            $('.order').on('click',function(){
            	var scheduleId = $(this).attr("value");
            	  $.ajax( {  
               	   url:'/doctorOrder',// 跳转到 action  
               	   data:{  
               		   scheduleId : scheduleId
               	   },  
               	   type:'post',  
               	   cache:false,  
               	   dataType:'json',  
               	   success:function(data) {
               		   if(data.status){
               			    var shifts = data.data.shifts;
               			    var date = data.data.date;
               			    $('.popup_date').html(date);
               			    for(i=0;i<shifts.length;i++){
               			    	var item = shifts[i]; 
               			    	var time = item.time;
               			    	var count = item.count ;
               			    	var li = "<li class=\"pop_li left\"><span class=\"left\">";
               			    	var span = time+"</span><span class=\"left\"'\>"+callFee+"元</span>";
               			    	var button = "";
               			    	if(item.count <= 0){
               			    		button="<button class=\"right pop_li_bg01\">约满</button></li>";
               			    	}else{
               			    		button="<a href=\"/orderInfo/"+scheduleId+"/"+item.sid+"\"><button class=\"right pop_li_bg02\">可约</button></a></li>";
               			    	}
               			    	var html = li+span+button;
               			    	$('.popup_ul').append(html);
               			    }
               		   }else{
               			   alert("预约失败！请稍后重试！"); 
               		   }
               	    },  
               	    error : function() {  
               	      alert("预约失败，请检查网络！");  
               	    }  
               	 }); 
                $('.popup_bg').show();
                $('.popup').fadeIn(200);
            });
            $('.full').on('click',function(){
                subs._popx('该时段已约满',2);
            });
            $('.close').on('click',subs._hide);
            $('.popup_bg').on('click',subs._hide);
            $('.pop_li_bg01').on('click',function(){
                subs._popx('该时段已约满',2);
            });
            $('.pop_li_bg02').on('click',function(){
                subs._popx('预约成功！',2.5);
                subs._hide();
            });
        },
        _hide:function(){
            $('.popup_bg').hide();
            $('.popup').hide();
        },
        toweek:function(){
            var dd = new Date();
            var week=dd.getDay();         //获取当前星期X(0-6,0代表星期天)
            for(var i=1;i<=7;i++){
                var daycount=i-week;
                var bak= subs.GetDateStr(daycount);
                $('.month_day').eq(i-1).text(bak[0]);
                $('.week').eq(i-1).text(bak[1]);
            }
        },
        GetDateStr:function(AddDayCount) {
            var backval = [];
            var dd = new Date();
            dd.setDate(dd.getDate() + AddDayCount);//获取AddDayCount天后的日期
            var y = dd.getFullYear();
            var m = dd.getMonth() + 1;//获取当前月份的日期
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
            var backday = m + "." + d;
            var backweek = "周" + weekname;
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
    subs.init();
    document.addEventListener("touchmove",function(e){
        if(jinzhi==0){
            e.preventDefault();
            e.stopPropagation();
        }
    },false);
})();
