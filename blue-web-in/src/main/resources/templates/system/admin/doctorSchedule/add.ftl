
<script type="text/javascript">
$(document).ready(function(){
	//初始化时间选择器
	$('#doctorSchAddTime').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true
	});
	 });
	
	function doctorSave(){
		$("span").remove(".errorClass");
		$("br").remove(".errorClass");
		var status = 1;
		if($("#name").val()==""){
			$("#userNoLabel").prepend('<span class="errorClass" style="color:red">*账号不能为空</span><br class="errorClass"/>');
			status = 0;
		}
		if(status == 0){
			return false;
		}else{
			$.ajax({
				url: '/doctorSchedule/save',
		        type: 'post',
		        dataType: 'text',
		        data: $("#doctorSchAddForm").serialize(),
		        success: function (data) {
	                var json = JSON.parse(data);
	                if (json.status){
	                 $(this).removeData("bs.modal"); 
		  			 $(".modal-body").children().remove();
	                    $("#lgModal").modal('hide');
	                    alertMsg("添加成功","success");
	                    reloadTable(list_ajax,"#doctorSchListTime","#deptNameSelect");
	                }else{
	                    alertMsg("添加失败:"+json.msg,"success");
	                }
		        }
			});
		}
	}

</script>
<div class="row"  >
	<div class="col-md-12">
		<form id="doctorSchAddForm">
			<div class="modal-body">
				<div class="form-group">
					<label id="userNoLabel">医生姓名</label>
					<select name="staffId" class="form-control select2" style="width: 100%;">
						<#list staffs as staff>
               				<option value="${staff.sid}">${staff.name}</option>
            			</#list>
                	</select>
                </div>
				 <div class="form-group date">
                    <label id="date">时间</label>
                    <div class="input-group-addon">
						<i class="fa fa-calendar"></i>
					</div>
					<input type="text" name="date" class="form-control pull-right" id="doctorSchAddTime" placeholder="选择时间...">
                </div>
                
                <div class="form-group">
                    <label>排班</label>
                    <select name="shiftTime" class="form-control select2" style="width: 100%;">
                        <option value="上午">上午</option>
                        <option value="下午">下午</option>
                    </select>
                </div>
                <div class="form-group">
                    <label id="nickPhoneLabel">次数</label>
                    <input type="text" class="form-control" name="count" id="count"
                           placeholder="输入次数...">
                </div>
				
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal" onclick="doctorClose();"><i class="fa fa-close"></i>关闭</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="doctorSave();"><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
 
 <script>
	 $('.close').on('click',function(){
	 		debugger;
		   $(this).removeData("bs.modal"); 
		   $(".modal-body").children().remove();
		   $("#lgModal").modal('hide');
	 
	 });
   function doctorClose(){
    	debugger;
		   $(this).removeData("bs.modal"); 
		   $(".modal-body").children().remove();
		    $("#lgModal").modal('hide');
   }
</script>
