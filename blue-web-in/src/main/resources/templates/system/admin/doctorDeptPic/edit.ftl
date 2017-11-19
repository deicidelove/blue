<script type="text/javascript">
		var textStr;
		$("#doctorDeptEditTarget").load(function(){
		     debugger;
			 textStr=$(this);
			 var data = textStr[0].contentDocument.body.textContent;
		 
		  	 var json = JSON.parse(data);
            if (json.status) {
                $("#lgModal").modal('hide');
                alertMsg("更新成功", "success");
                reloadTable(list_ajax, "#doctorDeptTime","#deptDoctorSelect");
            } else {
                alertMsg("更新失败:" + json.msg, "success");
	            }
		 })

</script>
<div class="row">
	<iframe id="doctorDeptEditTarget" name="doctorDeptEditTarget" style="display:none"></iframe>
    <div class="col-md-12">
        <form id="doctorDeptEditForm" method="post" enctype="multipart/form-data" action="/doctorDept/update" target="doctorDeptEditTarget">
            <input type="hidden" id="sid" name="sid" value="${bean.sid}">

            <div class="box-body">
              
                <div class="form-group">
                    <label>科室</label>
                    <select name="deptId" class="form-control select2" style="width: 100%;">
						<#list depts as dept>
               				<option <#if bean.deptId == dept.sid>selected="selected"</#if> value="${dept.sid}">${dept.name}</option>
            			</#list>
                    </select>
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
                    <button type="submit" class="btn btn-primary btn-sm"><i
                            class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
