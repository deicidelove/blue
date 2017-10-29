/**
 * Created by lonvov2 on 2017/10/24.
 */
(function () {
	 var code = 1;
	 var addPation={
			 init: function () {
				 addPation._bind();
		        }, 
		        _bind: function () {
		        	 $('.infor_li_box').on('click',function(){
		                 if( code== 1){
		                     $('.infor_li_checkbox').addClass('infor_li_checkbox_active');
		                     code = 0;
		                 }else{
		                     $('.infor_li_checkbox').removeClass('infor_li_checkbox_active');
		                     code = 1;
		                 }
		             });
		         
		         $('.infor_btn').on('click',function(){
		         	var name = $("input[name='name']").val();
		         	var phone =$("input[name='phone']").val();
		         	var sid = $(this).Attr("value");
		       	  $.ajax( {  
		          	   url:'/addPation',// 跳转到 action
		          	   data:{  
		          		   name : name,
		          		   phone:phone,
		          		   sid: sid,
		          		  isDefault:code
		          	   },  
		          	   type:'post',  
		          	   cache:false,  
		          	   dataType:'json',  
		          	   success:function(data) {
		          		   if(data.status){
		          			  alert("更新成功！"); 
		          			  history.go(-1); 
		          			  location.reload(); 
		          			    }
		          		   }else{
		          			   alert("更新失败！请稍后重试！"); 
		          		   }
		          	    },  
		          	    error : function() {  
		          	      alert("请检查网络！");  
		          	    }  
		          	 }); 
		         
		         
		         });
		        }
	 }
	addPation.init();
    
})();
