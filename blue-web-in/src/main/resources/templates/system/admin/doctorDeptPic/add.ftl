<script type="text/javascript">
var textStr;
		$("#doctorDeptAddTarget").load(function(){
		     debugger;
			 textStr=$(this);
			 var data = textStr[0].contentDocument.body.textContent;
		  	 var json = JSON.parse(data);
            if (json.status){
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功","success");
                    reloadTable(list_ajax,"#doctorDeptTime","#deptDoctorSelect");
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
		 })

</script>
<div class="row">
	<iframe id="doctorDeptAddTarget" name="doctorDeptAddTarget" style="display:none"></iframe>
	<div class="col-md-12">
		<form id="doctorDeptAddForm" method="post" enctype="multipart/form-data" action="/doctorDept/save" target="doctorDeptAddTarget">
			<div class="modal-body">
				
				 <div class="form-group">
                    <label>科室</label>
                    <select name="deptId" class="form-control select2" style="width: 100%;">
						<#list depts as dept>
               				<option value="${dept.sid}">${dept.name}</option>
            			</#list>
                    </select>
                </div>
               
                 <div class="form-group">
                    <label>上传文件</label>
                    <input type="file" class="form-control" name="fileName" id="fileName" value="上传头像" >
                </div>
				
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
					<button type="submit" class="btn btn-primary btn-sm" ><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
