<script type="text/javascript">
	var textStr;
		$("#sourceAddTarget").load(function(){
		     debugger;
			 textStr=$(this);
			 var data = textStr[0].contentDocument.body.textContent;
		  	 var json = JSON.parse(data);
           if (json.status){
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功","success");
                    reloadTable(list_ajax,"#sourceTime","#sourceNameSelect");
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
		 })

</script>
<div class="row">
<iframe id="sourceAddTarget" name="sourceAddTarget" style="display:none"></iframe>
	<div class="col-md-12">
		<form id="sourceAddForm" method="post" enctype="multipart/form-data" action="/source/save" target="sourceAddTarget">
			<div class="modal-body">
				<div class="form-group">
					<label id="titleLabel">标题</label>
					<input type="text" class="form-control" name="title" id="title" placeholder="输入标题...">
				</div>
				<div class="form-group">
					<label id="contextLabel">文本</label>
					<input type="text" class="form-control" name="context" id="context" placeholder="输入文本...">
				</div>
				 <div class="form-group">
                	<label>上传文件</label>
                    <input type="file" class="form-control" name="fileName" id="fileName" value="上传图片" >
                </div>
				<div class="form-group">
					 <label>科室</label>
                    <select name="deptId" class="form-control select2" style="width: 100%;">
					<#list depts as dept>
           				<option value="${dept.sid}">${dept.name}</option>
        			</#list>
                    </select>
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
