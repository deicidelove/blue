/**
 * Created by lonvov2 on 2017/10/12.
 */
(function () {
    var addNow = {
        init: function () {
        	addNow._bind();
        },
        _bind: function () {
           
            $('.infor_btn').on('click',function(){
               var addName = $("input[name='addName']").val();
               var phone = $("input[name='phone']").val();
               var educattion = $("input[name='educattion']").val();
               var wantJob = $("input[name='wantJob']").val();
               var wageWant = $("input[name='wageWant']").val();
               var describe = $("textarea[name='describe']").val();
               $.ajax( {  
            	   url:'/submitWorkWant',// 跳转到 action  
            	   data:{  
            		   addName : addName,  
            		   phone : phone,  
            		   educattion : educattion,  
            		   wantJob : wantJob,
            		   wageWant : wageWant,
            		   describe : describe
            	   },  
            	   type:'post',  
            	   cache:false,  
            	   dataType:'json',  
            	   success:function(data) {
            		   if(data.status){
            			   alert("提交成功！");  
            		   }else{
            			   alert("提交失败！请稍后重试！"); 
            		   }
//            	       window.location.reload();  
            	    },  
            	    error : function() {  
            	      alert("提交失败，请检查网络！");  
            	    }  
            	 }); 
            });
            
        },
       
    };
    addNow.init();
})();
