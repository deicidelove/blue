<div class="row">
	<div class="col-md-12">
		<form id="deptAddForm">
			<div class="modal-body">
				<div class="form-group">
					<label id="deptLabel">科室名</label>
					<input type="text" class="form-control" name="deptName" id="deptName" placeholder="输入账号...">
				</div>
				<div class="form-group">
					<label id="introduceLabel">介绍</label>
					<input type="text" class="form-control" name="context" id="context" placeholder="输入介绍...">
				</div>
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="blueDeptSave();"><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
function blueDeptSave(){
	$("span").remove(".errorClass");
	$("br").remove(".errorClass");
	var status = 1;
	if($("#deptName").val()==""){
		$("#deptName").prepend('<span class="errorClass" style="color:red">*科室名不能为空</span><br class="errorClass"/>');
		status = 0;
	}
	if(status == 0){
		return false;
	}else{
		$.ajax({
			url: '/blueDept/save',
	        type: 'post',
	        dataType: 'text',
	        data: $("#deptAddForm").serialize(),
	        success: function (data) {
                var json = JSON.parse(data);
                if (json.status){
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功","success");
                    reloadTable(list_ajax,"#dept","#deptSelect");
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
	        }
		});
	}
}
</script>