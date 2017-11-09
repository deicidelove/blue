
<script type="text/javascript">
		var textStr;
		$("#doctorEditTarget").load(function(){
		     debugger;
			 textStr=$(this);
			 var data = textStr[0].contentDocument.body.textContent;
		 
		  	 var json = JSON.parse(data);
            if (json.status) {
                $("#lgModal").modal('hide');
                alertMsg("更新成功", "success");
                reloadTable(list_ajax, "#doctorTime", "#doctorPremise");
            } else {
                alertMsg("更新失败:" + json.msg, "success");
	            }
		 })

</script>

<div class="row">
<iframe id="doctorEditTarget" name="doctorEditTarget" style="display:none"></iframe>
    <div class="col-md-12">
        <form id="doctorEditForm" method="post" enctype="multipart/form-data" action="/doctor/update" target="doctorEditTarget">
            <input type="hidden" id="sid" name="sid" value="${bean.sid}">

            <div class="box-body">
                <div class="form-group">
                    <label id="userNoLabel">姓名</label>
                    <input type="text" class="form-control" name="name" id="name"  placeholder="输入姓名..." value="${bean.name!}">
                </div>
                
                <div class="form-group">
                    <label>性别</label>
                    <select name="sex" class="form-control select2" style="width: 100%;">
                        <option <#if bean.sex == 0>selected="selected"</#if> value="0">女</option>
                        <option <#if bean.sex == 1>selected="selected"</#if> value="1">男</option>
                    </select>
                </div>
                <div class="form-group">
                    <label id="nickPhoneLabel">电话</label>
                    <input type="text" class="form-control" name="phone" id="phone" placeholder="输入电话..." value="${bean.phone!}">
                </div>
                <div class="form-group">
                    <label id="nickIntroduceLabel">介绍</label>
                    <textarea class="form-control" name="introduce" id="introduce" placeholder="输入简介...">${bean.introduce!}</textarea>
                </div>
                <div class="form-group">
                    <label id="nickJobLabel">工号</label>
                    <input type="text" class="form-control" name="jobNum" id="introduce" placeholder="输入工号..." value="${bean.jobNum!}">
                </div>
                <div class="form-group">
                    <label>科室</label>
                    <select name="deptId" class="form-control select2" style="width: 100%;">
						<#list depts as dept>
               				<option <#if bean.deptId == dept.sid>selected="selected"</#if> value="${dept.sid}">${dept.name}</option>
            			</#list>
                    </select>
                </div>
                <div class="form-group">
                    <label>地址</label>
                    <input type="text" class="form-control" name="address" id="introduce"  placeholder="输入地址..." value="${bean.address!}">
                </div>
                 <div class="form-group">
                	<img src="${bean.headUrl!}" style="width:200px;height:200px"/>
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
