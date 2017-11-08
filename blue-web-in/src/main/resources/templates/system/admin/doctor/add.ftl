<script type="text/javascript">
var textStr;
		$("#doctorAddTarget").load(function(){
		     debugger;
			 textStr=$(this);
			 var data = textStr[0].contentDocument.body.textContent;
		  	 var json = JSON.parse(data);
            if (json.status){
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功","success");
                    reloadTable(list_ajax,"#doctorTime","#doctorPremise");
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
		 })

</script>
<div class="row">
	<iframe id="doctorAddTarget" name="doctorAddTarget" style="display:none"></iframe>
	<div class="col-md-12">
		<form id="doctorAddForm" method="post" enctype="multipart/form-data" action="/doctor/save" target="doctorAddTarget">
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
					<label id="nickNameLabel">诊费</label>
					<input type="text" class="form-control" name="callFee" id="callFee" placeholder="输入诊费(数字)...">
				</div>
				<div class="form-group">
					<label id="nickNameLabel">职称</label>
					<input type="text" class="form-control" name="positionName" id="positionName" placeholder="输入职称...">
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
                    <input type="text" class="form-control" name="address" id="introduce" 
                           placeholder="输入地址...">
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
