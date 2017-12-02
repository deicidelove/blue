<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>就诊人管理</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/manage_people.css" />
</head>
<body>
<div class="heade">
    <div class="heade_text">就诊人管理</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back" style="color: white;">返回</div></a>
</div>
<ul class="manage_ul">
<#if pations??>
    <#list pations as pation>
    <li class="manage_li">
        <div class="manage_div01">
            <div class="manage_div01_top">
                <span class="left">${pation.name!}</span>
                <span class="right">${pation.phone!}</span>
            </div>
        </div>
        <div class="manage_div02">
         
            <div class="manage_checkbox left" sid="${pation.sid}">
               
                <#if pation.isDefault == 1>
                	 <div class="left" datatype="1"></div>
                <#else>
               		 <div class="left" datatype="0"></div>
                </#if>
                <div class="left infor_li_text">默认就诊人</div>
            </div>
           	<a href="editPationPage/${pation.sid}">
            <div class="manage_compile left">
                <div class="left infor_li_compile"></div>
                <div class="left infor_li_text">编辑</div>
            </div>
            </a>
            <div class="manage_delete left delete_people" sid="${pation.sid}">
                <div class="left infor_li_delete"></div>
                <div class="left infor_li_text">删除</div>
            </div>
        </div>
    </li>
    </#list>
    </#if>
    
</ul>
<a href="/addPationPage"><div class="infor_btn">添加就诊人</div></a>
<div class="delete">
    <div class="delete_text">确定要删除吗？</div>
    <div class="delete_btn box">
        <div class="flex-1 no">否</div>
        <div class="flex-1 _color yes">是</div>
    </div>
</div>
<div class="delete_bg"></div>
</body>
</html>
<script src="../js/jquery.js"></script>
<script>
    $(function(){
        var jinzhi=1;
        var delSid = 0;
        var list_index = null;
        $('.manage_checkbox').each(function(){
            var $div = $(this).children("div:first-child");
            var code = $div.attr('datatype');
            if( code == 1 ){
                $div.addClass('infor_li_checkbox').removeClass('infor_li_checkbox_active');
            }else{
                $div.removeClass('infor_li_checkbox').addClass('infor_li_checkbox_active');
            }
        });
        $('.manage_checkbox').on('click',function(){
            var $div = $(this).children("div:first-child");
            var code = $div.attr('datatype');
            var sid = $(this).val();
            var isDefault = 0;
            if( code == 1 ){
            	isDefault=0;
                $div.attr('datatype','0');
            }else{
            	isDefault=1;
                $div.attr('datatype','1');
            }
            $.ajax( {  
               	   url:'/updateIsDefault',// 跳转到 action  
               	   data:{  
               		   sid : sid,
               		   isDefault:isDefault
               	   },  
               	   type:'post',  
               	   cache:false,  
               	   dataType:'json',  
               	   success:function(data) {
               		   if(data.status){
               			  alert("更新成功！"); 
               		   }else{
               			   alert("请稍后重试！"); 
               		   }
               	    },  
               	    error : function() {  
               	      alert("请检查网络！");  
               	    }  
               	 }); 
            $('.manage_checkbox').each(function(){
                var $div = $(this).children("div:first-child");
                var code = $div.attr('datatype');
                if( code == 1 ){
                    $div.addClass('infor_li_checkbox').removeClass('infor_li_checkbox_active');
                }else{
                    $div.removeClass('infor_li_checkbox').addClass('infor_li_checkbox_active');
                }
            });
        });
        $('.delete_people').on('click',function(){        
         	delSid =  $(this).attr("sid");
            list_index = $(this).parents().parents().index();
  			$('.delete_bg').show();
            $('.delete').show();
            jinzhi=0;
        });
        $('.yes').on('click',function(){
        	
         	$.ajax( {  
               	   url:'/deletePation',// 跳转到 action  
               	   data:{  
               		   sid : delSid
               	   },  
               	   type:'post',  
               	   cache:false,  
               	   dataType:'json',  
               	   success:function(data) {
               		   if(data.status){
               		    jinzhi=1;
			            $('.delete_bg').hide();
			            $('.delete').hide();
			            $('.manage_li').eq(list_index).fadeOut(100,function(){
			                $(this).remove();
			            });
               			alert("删除成功！"); 
               		   }else{
               			   alert("请稍后重试！"); 
               		   }
               	    },  
               	    error : function() {  
               	      alert("请检查网络！");  
               	    }  
               	 });
           
        });
        $('.no').on('click',function(){
            jinzhi=1;
            $('.delete_bg').hide();
            $('.delete').hide();
        });
        document.addEventListener("touchmove",function(e){
            if(jinzhi==0){
                e.preventDefault();
                e.stopPropagation();
            }
        },false);
    });
</script>