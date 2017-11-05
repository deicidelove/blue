<div class="row">
	<div class="col-md-12">
		<form id="addOurForm">
			<div class="modal-body">
				<div class="form-group">
					<label id="deptLabel">标题</label>
					<input type="text" class="form-control" name="title" id="title" placeholder="输入标题...">
				</div>
				<div class="form-group">
					<label id="introduceLabel">需要人数</label>
					<input type="text" class="form-control" name="needNum" id="needNum" placeholder="输入需要人数...">
				</div>
				<div class="form-group">
					<label id="deptLabel">学历</label>
					<input type="text" class="form-control" name="education" id="education" placeholder="输入学历...">
				</div>
				<div class="form-group">
					<label id="introduceLabel">经验</label>
					<input type="text" class="form-control" name="experience" id="experience" placeholder="输入经验...">
				</div>
				<div class="form-group">
					<label id="deptLabel">工资</label>
					<input type="text" class="form-control" name="wages" id="wages" placeholder="输入工资...">
				</div>
				<div class="form-group">
					<label id="introduceLabel">工资时间</label>
					<input type="text" class="form-control" name="workTime" id="workTime" placeholder="输入工作时间...">
				</div>
				<div class="form-group">
					<label id="deptLabel">工作地址</label>
					<input type="text" class="form-control" name="workAddress" id="workAddress" placeholder="输入地址...">
				</div>
				<div class="form-group">
					<label id="introduceLabel">描述</label>
					<input type="text" class="form-control" name="description" id="description" placeholder="输入描述...">
				</div>
				<div class="form-group">
					<label id="deptLabel">要求</label>
					<input type="text" class="form-control" name="requirement" id="requirement" placeholder="输入要求...">
				</div>
				<div class="form-group">
					<label id="introduceLabel">福利待遇</label>
					<input type="text" class="form-control" name="fringeBenefits" id="fringeBenefits" placeholder="输入福利待遇...">
				</div>
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="addOurSave();"><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
function addOurSave(){
	$("span").remove(".errorClass");
	$("br").remove(".errorClass");
	var status = 1;
	
	if(status == 0){
		return false;
	}else{
		$.ajax({
			url: '/addOur/save',
	        type: 'post',
	        dataType: 'text',
	        data: $("#addOurForm").serialize(),
	        success: function (data) {
                var json = JSON.parse(data);
                if (json.status){
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功","success");
                    reloadTable(list_ajax,"#addOur","#addOurSelect");
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
	        }
		});
	}
}
</script>