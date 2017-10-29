/**
 * Created by lonvov2 on 2017/10/12.
 */
(function () {
    var addAppointment = {
        init: function () {
        	addAppointment._bind();
        },
        _bind: function () {
           
            $('.infor_btn').on('click',function(){
               var staffId = $(this).attr("value");
               var staffName = $("div[name='staffName']").val();
               var pay = $("div[name='pay']").val();
               var pationId = $("div[name='pationId']").val();
               var deptName=$("div[name='deptName']").val();
               var deptId = $("div[name='deptId']").attr("val");
               $.ajax( {  
            	   url:'/orderSubmit',// 跳转到 action  
            	   data:{  
            		   staffId : staffId,  
            		   staffName : staffName,  
            		   pay : pay,  
            		   pationId : pationId,
            		   deptName : deptName,
            		   deptId : deptId
            	   },  
            	   type:'post',  
            	   cache:false,  
            	   dataType:'json',  
            	   success:function(data) {
            		   if(data.status){
            			   alert("预约成功！");  
            		   }else{
            			   alert("预约失败！请稍后重试！"); 
            		   }
//            	       window.location.reload();  
            	    },  
            	    error : function() {  
            	      alert("请检查网络！");  
            	    }  
            	 }); 
            });
            
        },
       
    };
    addAppointment.init();
})();
