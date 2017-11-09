
<script type="text/javascript">
		var textStr;
		$("#deptTarget").load(function(){
		     debugger;
			 textStr=$(this);
			 var data = textStr[0].contentDocument.body.textContent;
		 
		  	 var json = JSON.parse(data);
            if (json.status) {
                $("#lgModal").modal('hide');
                alertMsg("更新成功", "success");
                reloadTable(list_ajax, "#dept","#deptSelect");
            } else {
                alertMsg("更新失败:" + json.msg, "success");
	            }
		 })

</script>
<div class="row">
<iframe id="deptTarget" name="deptTarget" style="display:none"></iframe>
    <div class="col-md-12">
        <form id="blueDeptEditForm" method="post" enctype="multipart/form-data" action="/blueDept/update" target="deptTarget">
            <input type="hidden" id="id" name="sid" value="${bean.sid}">
            <div class="box-body">
            	<div class="form-group">
					<label id="deptLabel">科室名</label>
					<input type="text" class="form-control" name="deptName" id="deptName" 
					placeholder="输入科室名..." value="${bean.name!}" >
				</div>
				<div class="form-group">
					<label id="introduceLabel">介绍</label>
					<textarea class="form-control" name="context" id="context" placeholder="输入介绍...">${bean.context!}</textarea>
				</div>
				<div class="form-group">
                	<img src="${bean.url!}" style="width:200px;height:200px"/>
                	<label>上传文件</label>
                    <input type="file" class="form-control" name="fileName" id="fileName" value="上传图片" >
                </div>
            </div>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i
                            class="fa fa-close"></i>关闭
                    </button>
                    <button  type="submit" class="btn btn-primary btn-sm"><i
                            class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
