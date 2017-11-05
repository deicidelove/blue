<script type="text/javascript">
	//初始化时间选择器
	$('#doctorSchEditTime').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true
	});
	  function doctorSchUpdate() {
        debugger;
        $.ajax({
            url: '/doctorSchedule/update',
            type: 'post',
            dataType: 'text',
            data: $("#doctorSchEditForm").serialize(),
            success: function (data) {
                var json = JSON.parse(data);
                if (json.status) {
                    $("#lgModal").modal('hide');
                    alertMsg("更新成功", "success");
                    reloadTable(list_ajax, "#doctorSchListTime", "#deptNameSelect");
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
        <form id="doctorSchEditForm">
            <input type="hidden" id="sid" name="id" value="${bean.sid}">

            <div class="box-body">
               <div class="form-group date">
                    <label id="date">时间</label>
                    <div class="input-group-addon">
						<i class="fa fa-calendar"></i>
					</div>
					<input type="text" name="date" class="form-control pull-right" id="doctorSchEditTime" placeholder="选择时间...">
                </div>
                
                <div class="form-group">
                    <label>排班</label>
                    <select name="shiftTime" class="form-control select2" style="width: 100%;">
                        <option <#if bean.shiftTime == "上午">selected="selected"</#if> value="上午">上午</option>
                        <option <#if bean.shiftTime == "下午">selected="selected"</#if> value="下午">下午</option>
                    </select>
                </div>
                <div class="form-group">
                    <label id="nickPhoneLabel">次数</label>
                    <input type="text" class="form-control" name="count" id="count" value="${bean.count!}"
                           placeholder="输入次数...">
                </div>
                
            </div>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i
                            class="fa fa-close"></i>关闭
                    </button>
                    <button onclick="doctorSchUpdate();" type="button" class="btn btn-primary btn-sm"><i
                            class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
