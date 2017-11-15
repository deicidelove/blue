<div class="row">
	<div class="col-md-12">
		<form id="lastActAddForm">
			<div class="modal-body">
				<div class="form-group">
					<label id="acitIdLabel">最近活动名称</label>
					<input type="text" class="form-control" name="lastActName" id="lastActName" placeholder="输入最近活动名称...">
				</div>
				<div class="form-group">
					<label id="goodsNameLabel">最近活动英文名称</label>
					<input type="text" class="form-control" name="lastActEnname" id="lastActEnname" placeholder="输入最近活动英文名称...">
				</div>
				<div class="form-group">
					<label id="goodsTitleLabel">最近活动标题</label>
					<input type="text" class="form-control" name="lastActTitle" id=""lastActTitle"" placeholder="输入最近活动标题...">
				</div>
				<div class="form-group">
					<label>最近活动内容</label> 
					<script id="editor" name="lastActContent" type="text/plain" style="width:100%;height:500px;"></script>
				</div>
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="lastActSave();"><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	var ue = UE.getEditor('editor');
	
	function lastActSave(){
		$("span").remove(".errorClass");
		$("br").remove(".errorClass");
		var status = 1;

		if(status == 0){
			return false;
		}else{
			$.ajax({
				url: '/lastact/save',
		        type: 'post',
		        dataType: 'text',
		        data: $("#lastActAddForm").serialize(),
		        success: function (data) {
	                var json = JSON.parse(data);
	                if (json.status){
	                    $("#lgModal").modal('hide');
	                    alertMsg("添加成功","success");
	                    reloadTable(list_ajax,"#goodsTime","#goodsPremise");
	                }else{
	                    alertMsg("添加失败:"+json.msg,"success");
	                }
		        }
			});
		}
	}

</script>