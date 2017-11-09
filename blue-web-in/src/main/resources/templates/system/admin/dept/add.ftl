<script type="text/javascript">

var textStr;
		$("#deptAddTarget").load(function(){
		     debugger;
			 textStr=$(this);
			 var data = textStr[0].contentDocument.body.textContent;
		  	 var json = JSON.parse(data);
            if (json.status){
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功","success");
                    reloadTable(list_ajax,"#dept","#deptSelect");
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
		 })

</script>
<div class="row">
<iframe id="deptAddTarget" name="deptAddTarget" style="display:none"></iframe>
	<div class="col-md-12">
		<form id="deptAddForm" method="post" enctype="multipart/form-data" action="/blueDept/save" target="deptAddTarget">
			<div class="modal-body">
				<div class="form-group">
					<label id="deptLabel">科室名</label>
					<input type="text" class="form-control" name="deptName" id="deptName" placeholder="输入科室名...">
				</div>
				<div class="form-group">
					<label id="introduceLabel">介绍</label>
					<textarea class="form-control" name="context" id="context" placeholder="输入介绍..."></textarea>
				</div>
				 <div class="form-group">
                    <label>上传文件</label>
                    <input type="file" class="form-control" name="fileName" id="fileName" value="上传图片" >
                </div>
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
					<button type="submit" class="btn btn-primary btn-sm"><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
