<div class="row">
    <div class="col-md-12">
        <form id="oppoEditForm">
            <input type="hidden" id="sid" name="sid" value="${bean.sid}">

            <div class="box-body">
               <div class="form-group date">
                    <label id="date">时间</label>
                    <div class="input-group-addon">
						<i class="fa fa-calendar"></i>
					</div>
					<input type="text" name="date" class="form-control pull-right" id="oppoEditTime" placeholder="选择时间...">
                </div>          
            </div>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal" onclick="oppoUpdateClose();"><i
                            class="fa fa-close"></i>关闭
                    </button>
                    <button onclick="oppoUpdate();" type="button" class="btn btn-primary btn-sm"><i
                            class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
	//初始化时间选择器
	$('#oppoEditTime').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true
	});
  });
	  function oppoUpdate() {
        debugger;
        $.ajax({
            url: '/oppointment/update',
            type: 'post',
            dataType: 'text',
            data: $("#oppoEditForm").serialize(),
            success: function (data) {
                var json = JSON.parse(data);
                if (json.status) 
                {
                 $(this).removeData("bs.modal"); 
		   		$(".modal-body").children().remove();
                    $("#lgModal").modal('hide');
                    alertMsg("更新成功", "success");
                    reloadTable(list_ajax, "#oppointmentTime", "#deptOppointmentSelect");
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
 <script>
  $('.close').on('click',function(){
	 		debugger;
		   $(this).removeData("bs.modal"); 
		   $(".modal-body").children().remove();
		   $("#lgModal").modal('hide');
	 
	 });
   function oppoUpdateClose(){
    	debugger;
		   $(this).removeData("bs.modal"); 
		   $(".modal-body").children().remove();
		   $("#lgModal").modal('hide');
   }
</script>