<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>预约挂号</title>
     <script src="../../js/common.js"></script>
    <link rel="stylesheet" href="../../css/common.css" />
    <link rel="stylesheet" href="../../css/subscribe.css" />
</head>
<script type="text/javascript">
	var callFee = '${doctor.callFee}';
</script>
<body>
<div class="heade">
    <div class="heade_text">预约挂号</div>
    <a href="#" onclick="javascript:history.back(-1);"><div class="heade_back" style="color: white;">返回</div></a>
</div>
<div class="subs_head">
	 <#if doctor.headUrl??>
    <div class="head_bg left" style="background-image: url(${doctor.headUrl!})"></div>
    <#else>
    <div class="head_bg left" ></div>
    </#if>
    <div class="head_name left" >
        <div>${doctor.name!}</div>
        <div class="post">
            <span class="left">${doctor.positionName!}</span>
            <span class="left">${doctor.deptName!}</span>
        </div>
    </div>
</div>
<div class="record record_btn01">
    <div class="left">医生简介</div>
    <div class="right"></div>
</div>
<div class="abstract">
    ${doctor.introduce!}
</div>
<div class="working">
    <div class="working_schedule">医生排班表</div>
    <table class="working_table">
        <tr class="table_tr01">
            <td>排班</td>
            <td>
                <div class="date_week">
                    <div class="week"></div>
                    <div class="month_day"></div>
                </div>
            </td>
            <td>
                <div class="date_week">
                    <div class="week"></div>
                    <div class="month_day"></div>
                </div>
            </td>
            <td>
                <div class="date_week">
                    <div class="week"></div>
                    <div class="month_day"></div>
                </div>
            </td>
            <td>
                <div class="date_week">
                    <div class="week"></div>
                    <div class="month_day"></div>
                </div>
            </td>
            <td>
                <div class="date_week">
                    <div class="week"></div>
                    <div class="month_day"></div>
                </div>
            </td>
            <td>
                <div class="date_week">
                    <div class="week"></div>
                    <div class="month_day"></div>
                </div>
            </td>
            <td>
                <div class="date_week">
                    <div class="week"></div>
                    <div class="month_day"></div>
                </div>
            </td>
        </tr>
        <tr class="table_tr02">
            <td>上午</td>
            <#list morning as item>
	            <#if item.flag == 0>
		            <td>
		                <div class="order" value="${item.scheduleId}">预约</div>
		            </td>
		        </#if>
	            <#if item.flag == 1>
	            	<td>
	            		<div class="full">约满</div>
	            	</td>
	            </#if>
	            <#if item.flag == 2>
	            	<td></td>
	            </#if>
            </#list>
           
        </tr>
        <tr class="table_tr03">
            <td>下午</td>
            <#list afternoon as item>
	            <#if item.flag == 0>
		            <td>
		                <div class="order" value="${item.scheduleId}">预约</div>
		            </td>
		        </#if>
	            <#if item.flag == 1>
	            	<td>
	            		<div class="full">约满</div>
	            	</td>
	            <#else>
	            	<td></td>
	            </#if>
            </#list>
        </tr>
    </table>
    <div class="working_schedule">抓紧时间预约哦！</div>
</div>
<div class="popup_bg"></div>
<div class="popup">
    <div class="popup_head">
        <span class="left">咨询热线: 025-52251111</span>
        <span class="right close"></span>
    </div>
    <div class="popup_doctor">
        <div class="doctor_img left"></div>
        <div class="doctor_price left">
            <div class="doctor_price_div01">
                <span>￥0.00</span>
                <span>折扣价</span>
            </div>
            <div class="doctor_price_div02">原价 ￥30.00</div>
        </div>
    </div>
    <div class="popup_date"></div>
    <ul class="popup_ul">
    </ul>
</div>
<div class="margin_bottom"></div>
<div class="footer box">
    
    <div class="flex-1 footer_list">
      <a href="/">
        <div class="footer_bg04_active"></div>
        <div class="footer_color">首页</div>
     </a>
    </div>
   
   
    <div class="flex-1 footer_list">
         <a href="/blueWebsite">
        <div class="footer_bg01"></div>
        <div>蓝鲟</div>
        </a>
    </div>
    
   
    <div class="flex-1 footer_list">
     <a href="https://www.720think.com/t/53c5auj5j?from=singlemessage">
        <div class="footer_bg02"></div>
        <div>走进蓝鲟</div>
    </a>
    </div>
    
     <div class="flex-1 footer_list">
        <a href="/personal/index">
            <div class="footer_bg03"></div>
            <div>我的</div>
        </a>
    </div>
</div>
</body>
</html>

<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/subscribe.js"></script>