<div class="row">
    <div class="col-md-12">
        <form id="blueDeptEditForm">
            <input type="hidden" id="id" name="sid" value="${bean.sid}">

            <div class="box-body">
            	<div class="form-group">
					<label id="deptLabel">科室名</label>
					<input type="text" class="form-control" name="deptName" id="deptName" value="${bean.name!}" 
					placeholder="输入科室名...">
				</div>
				<div class="form-group">
					<label id="introduceLabel">介绍</label>
					<input type="text" class="form-control" name="context" id="context" value="${bean.context!}" 
					placeholder="输入介绍...">
				</div>
            </div>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i
                            class="fa fa-close"></i>关闭
                    </button>
                    <button onclick="blueDeptUpdate();" type="button" class="btn btn-primary btn-sm"><i
                            class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    function blueDeptUpdate() {
        debugger;
        $.ajax({
            url: '/blueDept/update',
            type: 'post',
            dataType: 'text',
            data: $("#blueDeptEditForm").serialize(),
            success: function (data) {
                var json = JSON.parse(data);
                if (json.status) {
                    $("#lgModal").modal('hide');
                    alertMsg("更新成功", "success");
                    reloadTable(list_ajax, "#dept","#deptSelect");
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