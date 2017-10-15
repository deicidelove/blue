<script type="text/javascript">
    function doctorUpdate() {
        debugger;
        $.ajax({
            url: '/doctor/update',
            type: 'post',
            dataType: 'text',
            data: $("#doctorEditForm").serialize(),
            success: function (data) {
                var json = JSON.parse(data);
                if (json.status) {
                    $("#lgModal").modal('hide');
                    alertMsg("更新成功", "success");
                    reloadTable(list_ajax, "#doctorTime", "#doctorPremise");
                } else {
                    alertMsg("更新失败:" + json.msg, "success");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    }

</script>
<div class="row">
    <div class="col-md-12">
        <form id="doctorEditForm">
            <input type="hidden" id="sid" name="sid" value="${bean.sid}">

            <div class="box-body">
                <div class="form-group">
                    <label id="userNoLabel">姓名</label>
                    <input type="text" class="form-control" name="name" id="name" value="${bean.name!}"
                            placeholder="输入姓名...">
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
                    <input type="text" class="form-control" name="phone" id="phone" value="${bean.phone!}"
                           placeholder="输入电话...">
                </div>
                <div class="form-group">
                    <label id="nickIntroduceLabel">介绍</label>
                    <input type="text" class="form-control" name="introduce" id="introduce" value="${bean.introduce!}"
                           placeholder="输入简介...">
                </div>
                <div class="form-group">
                    <label id="nickJobLabel">工号</label>
                    <input type="text" class="form-control" name="jobNum" id="introduce" value="${bean.jobNum!}"
                           placeholder="输入工号...">
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
                    <input type="text" class="form-control" name="address" id="introduce" value="${bean.address!}"
                           placeholder="输入地址...">
                </div>
              
            </div>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i
                            class="fa fa-close"></i>关闭
                    </button>
                    <button onclick="doctorUpdate();" type="button" class="btn btn-primary btn-sm"><i
                            class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
