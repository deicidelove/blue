<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>我的预约</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/confirm_order.css" />
    <link rel="stylesheet" href="../css/user_centerw.css" />
</head>

<body>
    <div class="heade">
        <div class="heade_text">我的预约</div>
        <a href="#" onclick="javascript:history.back(-1);location.reload()"><div class="heade_back">返回</div></a>
    </div>
    <#list oppos as oppo>
    <div class="mgb_space">
        <div class="order_set">
            <div class="left">${oppo.orderTime}</div>
            <div class="right_num">${oppo.deptName}</div>
        </div>
        
        <div class="yuyue_li"> 
            <img class="yuyue_img left" src="${oppo.head_url}" />
            <div class="yuyue_xq left">
                <div class="name">
                    <span>${oppo.staffName}</span>
                    <label>${oppo.positionName}</label>
                </div>
                <div class="point">${oppo.introduce}</div>
                <div class="specialist">专家门诊</div>
            </div>
            <div class="yuyue_price right">¥ ${oppo.payMoney}</div>
        </div>
        
        <div class="order_set">
            <#if oppo.isPass == 0>
	            <div class="left color_org">已预约</div>
	            <a href="/doctorDetial/${oppo.staffId}/${oppo.sid}"><div class="right graybr_btn">调整预约</div></a>
            <#else>
            	<div class="left color_org">预约已过期</div>
		        <div class="right graybr_btn" id="delete" val="${oppo.sid}">删除</div>
		    </#if>
        </div>
    </div>
    </#list>

</body>
</html>
<script src="../js/jquery.js"></script>
<script>
    $(function(){
        $('#delete').on('click',function(){
        	 debugger;
            var sid = $(this).attr("val");
            $.ajax( {  
               	   url:'/deleteOppo',
               	   data:{  
               		   sid : sid
               	   },  
               	   type:'post',  
               	   cache:false,  
               	   dataType:'json',  
               	   success:function(data) {
               		   if(data.status){
               		   location.reload();
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
    })  
</script>