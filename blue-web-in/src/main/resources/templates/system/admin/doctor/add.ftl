<script type="text/javascript">
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
			url: '/doctor/save',
	        type: 'post',
	        dataType: 'text',
	        data: $("#doctorAddForm").serialize(),
	        success: function (data) {
                var json = JSON.parse(data);
                if (json.status){
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功","success");
                    reloadTable(list_ajax,"#doctorTime","#doctorPremise");
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
	        }
		});
	}
}
</script>
<div class="row">
	<div class="col-md-12">
		<form id="doctorAddForm">
			<div class="modal-body">
				<div class="form-group">
					<label id="userNoLabel">姓名</label>
					<input type="text" class="form-control" name="name" id="name" placeholder="输入姓名...">
				</div>
				<div class="form-group">
					<label id="passwordLabel">手机</label>
					<input type="text" class="form-control" name="phone" id="phone" placeholder="输入手机号...">
				</div>
				<div class="form-group">
					<label id="nickNameLabel">介绍</label>
					<input type="text" class="form-control" name="introduce" id="introduce" placeholder="输入介绍...">
				</div>
				<div class="form-group">
					<label id="nickNameLabel">工号</label>
					<input type="text" class="form-control" name="jobNum" id="jobNum" placeholder="输入工号...">
				</div>
				<div class="form-group">
					<label>性别</label> 
					<select name="sex" class="form-control select2" style="width: 100%;">
						<option value="1">男</option>
						<option value="0">女</option>
					</select>
				</div>
				 <div class="form-group">
                    <label>科室</label>
                    <select name="deptId" class="form-control select2" style="width: 100%;">
						<#list depts as dept>
               				<option value="${dept.sid}">${dept.name}</option>
            			</#list>
                    </select>
                </div>
                <div class="form-group">
                    <label>地址</label>
                    <input type="text" class="form-control" name="address" id="introduce" value="${bean.address!}"
                           placeholder="输入地址...">
                </div>
				
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="doctorSave();"><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
