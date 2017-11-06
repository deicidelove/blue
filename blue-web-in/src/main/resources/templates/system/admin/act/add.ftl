<div class="row">
	<div class="col-md-12">
		<form id="actAddForm">
			<div class="modal-body">
				<div class="form-group">
					<label id="actNameLabel">活动名称</label>
					<input type="text" class="form-control" name="actName" id="actname" placeholder="输入名称...">
				</div>
				<div class="form-group">
					<label id="actTotalNumLabel">活动数量</label>
					<input type="text" class="form-control" name="actTotalNum" id="acttotalnum" placeholder="输入活动 数量...">
				</div>
				<div class="form-group">
					<label id="actGivingNumLabel">中奖数量</label>
					<input type="text" class="form-control" name="actGivingNum" id="actgivingnum" placeholder="输入中奖数量...">
				</div>
				<div class="form-group">
					<label id="actPeriodsLabel">当前期数</label>
					<input type="text" class="form-control" name="actPeriods" id="actperiods" placeholder="输入当前期数...">
				</div>
				
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="securitySave();"><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
function securitySave(){
	$("span").remove(".errorClass");
	$("br").remove(".errorClass");
	var status = 1;
	if(!isNumber(parseInt($("#acttotalnum").val()))){
		$("#actTotalNumLabel").prepend('<span class="errorClass" style="color:red">*输入数字</span><br class="errorClass"/>');
		status = 0;
	}
	if(!isNumber(parseInt($("#actgivingnum").val()))){
		$("#actGivingNumLabel").prepend('<span class="errorClass" style="color:red">*输入数字</span><br class="errorClass"/>');
		status = 0;
	}
	if(!isNumber(parseInt($("#actperiods").val()))){
		$("#actPeriodsLabel").prepend('<span class="errorClass" style="color:red">*输入数字</span><br class="errorClass"/>');
		status = 0;
	}
	if(status == 0){
		return false;
	}else{
		$.ajax({
			url: '/act/save',
	        type: 'post',
	        dataType: 'text',
	        data: $("#actAddForm").serialize(),
	        success: function (data) {
                var json = JSON.parse(data);
                if (json.status){
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功","success");
                    reloadTable(list_ajax,"#actTime","#actPremise");
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
	        }
		});
	}
}

function isNumber(obj) {  
	
    return typeof obj === 'number' && !isNaN(obj)  
}  
</script>