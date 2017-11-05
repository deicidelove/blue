/**
 * Created by lonvov2 on 2017/10/24.
 */
(function () {
	 var code = 1;
	 var selectPation={
			 init: function () {
				 selectPation._bind();
		        }, 
		        _bind: function () {
		       
		         $('.select_li').on('click',function(){
			         	var sid = $(this).attr("sid");
			         	var scheduleId = $(this).attr("scheduleId");
			         	window.location.href = '/orderInfoPage/?sid=' + sid + '&scheduleId=' + scheduleId;
		        })
		        }
	 }
	selectPation.init();
    
})();
