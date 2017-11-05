<div class="row">
    <div class="col-md-12">
        <form id="actEditForm">
            <input type="hidden" id="actId" name="actId" value="${bean.actId}">

            <div class="box-body">
				<div class="form-group">
					<label id="actNameLabel">活动名称</label>
					<input type="text" class="form-control" name="actName" id="actname" value="${bean.actName!}" placeholder="输入名称...">
				</div>
            </div>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i
                            class="fa fa-close"></i>关闭
                    </button>
                    <button onclick="securityUpdate();" type="button" class="btn btn-primary btn-sm"><i
                            class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    function securityUpdate() {
        debugger;
        $.ajax({
            url: '/act/update',
            type: 'post',
            dataType: 'text',
            data: $("#actEditForm").serialize(),
            success: function (data) {
                var json = JSON.parse(data);
                if (json.status) {
                    $("#lgModal").modal('hide');
                    alertMsg("更新成功", "success");
                    reloadTable(list_ajax, "#actTime", "#actPremise");
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