<div class="row">
	<div class="col-md-12">
		<form id="oppoAddForm">
			<div class="modal-body">
				<div class="form-group date">
                    <label id="date">时间</label>
                    <div class="input-group-addon">
						<i class="fa fa-calendar"></i>
					</div>
					<input type="text" name="date" class="form-control pull-right" id="oppoAddTime" placeholder="选择时间...">
                </div> 			
                <div class="form-group">
					<label id="userOppoLabel">医生姓名</label>
					<select name="staffId" class="form-control select2" style="width: 100%;">
						<#list staffs as staff>
               				<option value="${staff.sid}">${staff.name}</option>
            			</#list>
                	</select>
                </div>
                <div class="form-group">
					<label id="payLabel">支付金额</label>
					<input type="text" class="form-control" name="payMoney" id="payMoney" placeholder="输入支付金额...">
				</div>
				
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal" onclick="OppoClose();"><i class="fa fa-close"></i>关闭</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="OppoSave();"><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
           //初始化时间选择器
		$('#oppoAddTime').datepicker({
			language: 'zh-CN',
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayHighlight: true
		});
       });
	
function OppoSave(){
	$("span").remove(".errorClass");
	$("br").remove(".errorClass");
	var status = 1;
	
	if(status == 0){
		return false;
	}else{
		$.ajax({
			url: '/oppointment/save',
	        type: 'post',
	        dataType: 'text',
	        data: $("#oppoAddForm").serialize(),
	        success: function (data) {
                var json = JSON.parse(data);
                if (json.status){
                 $(this).removeData("bs.modal"); 
		  		 $(".modal-body").children().remove();
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功","success");
                    reloadTable(list_ajax,"#oppointmentTime","#deptOppointmentSelect");
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
	        }
		});
	}
}
</script>

 <script>
  $('.close').on('click',function(){
	 		debugger;
		   $(this).removeData("bs.modal"); 
		   $(".modal-body").children().remove();
		   $("#lgModal").modal('hide');
	 
	 });
   function OppoClose(){
    	debugger;
		   $(this).removeData("bs.modal"); 
		   $(".modal-body").children().remove();
		   $("#lgModal").modal('hide');
   }
</script>