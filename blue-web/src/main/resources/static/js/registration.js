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
            $('.month_day').on('click',registration._click_active);
        },
        _click_active:function(){
            $(this).parents().find('.month_day').removeClass('day');
            $(this).addClass('day');
            var date = $(this).attr("val");
            $.ajax( {  
            	   url:'/orderPageByDate/' ,// 跳转到 action  
            	   data:{  
            		   date : date
               	   }, 
            	   type:'post',  
            	   cache:false,  
            	   dataType:'json',  
            	   success:function(data) {
            		   if(data.status){
            			    var staffs = data.data;
            			    $('.doctor_ul').html("");
            			    for(i=0;i<staffs.length;i++){
            			    	var staff = staffs[i];
            			    	var html = "<a href=\"/doctorDetial/"+staff.sid+"\"><li class=\"doctor_li\">";
            			    	if(staff.headUrl!=null){
            			    		html+="<div class=\"doctor_img left\" style=\"background-image:url("+staff.headUrl+")\"></div>"
            			    	}else{
            			    		html+="<div class=\"doctor_img left\"></div>"
            			    	}
            			    	html +="<div class=\"doctor_xq left\"><div class=\"name\"><span>"+staff.name+"</span>"
            			    		+"<span>"+staff.positionName+"</span></div><div class=\"point\">"+staff.introduce+"</div> <div class=\"specialist\">专家门诊</div></div><div class=\"doctor_more right\">预约</div></li></a>"
            			    	$('.doctor_ul').append(html);
            			    }
            		   }else{
            			   alert("查询失败！请稍后重试！"); 
            		   }
            	    },  
            	    error : function() {  
            	      alert("查询失败，请检查网络！");  
            	    }  
            	 }); 
        },
        toweek:function(){
            var dd = new Date();
            //var cc = dd.getDate();
            //var week=dd.getDay();//获取当前星期X(0-6,0代表星期天)
            $('.month_day').eq(0).addClass('day');
            for(var i=0;i<7;i++){
                //var daycount =i - week;
                var bak= registration.GetDateStr(i);
                $('.month_day').eq(i).text(bak[0]);
                $('.week').eq(i).text(bak[1]);
                $('.month_day').eq(i).attr("val",bak[2]);
            }
        },
        FormatDate:function(date,fmt){
        	var o = {
    		        "M+": date.getMonth() + 1, //月份 
    		        "d+": date.getDate(), //日 
    		        "h+": date.getHours(), //小时 
    		        "m+": date.getMinutes(), //分 
    		        "s+": date.getSeconds(), //秒 
    		        "q+": Math.floor((date.getMonth() + 3) / 3), //季度 
    		        "S": date.getMilliseconds() //毫秒 
    		    };
		    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
		    for (var k in o)
		    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		    return fmt;
        	
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
            backval.push(backday, backweek,registration.FormatDate(dd,"yyyy-MM-dd"));
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
