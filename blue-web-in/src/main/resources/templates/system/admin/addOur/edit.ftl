<script type="text/javascript">
		var textStr;
		$("#target").load(function(){
		     debugger;
			 textStr=$(this);
			 var data = textStr[0].contentDocument.body.textContent;
		 
		  	 var json = JSON.parse(data);
            if (json.status) {
                $("#lgModal").modal('hide');
                alertMsg("更新成功", "success");
                reloadTable(list_ajax, "#advertTime","#advertSelect");
            } else {
                alertMsg("更新失败:" + json.msg, "success");
	            }
		 })

</script>
<div class="row">
	<iframe id="target" name="target" style="display:none"></iframe>
    <div class="col-md-12">
        <form id="addOurEditForm" method="post" enctype="multipart/form-data" action="/addOur/update" target="target">
            <input type="hidden" id="id" name="sid" value="${bean.sid!}">

            <div class="box-body">
               <div class="form-group">
					<label id="deptLabel">标题</label>
					<input type="text" class="form-control" name="title" id="title" value=${bean.title!} placeholder="输入标题...">
				</div>
				<div class="form-group">
					<label id="introduceLabel">需要人数</label>
					<input type="text" class="form-control" name="needNum" id="needNum" value=${bean.needNum!} placeholder="输入需要人数...">
				</div>
				<div class="form-group">
					<label id="deptLabel">学历</label>
					<input type="text" class="form-control" name="education" id="education" value=${bean.education!} placeholder="输入学历...">
				</div>
				<div class="form-group">
					<label id="introduceLabel">经验</label>
					<input type="text" class="form-control" name="experience" id="experience" value=${bean.experience!} placeholder="输入经验...">
				</div>
				<div class="form-group">
					<label id="deptLabel">工资</label>
					<input type="text" class="form-control" name="wages" id="wages" value=${bean.wages!} placeholder="输入工资...">
				</div>
				<div class="form-group">
					<label id="introduceLabel">工资时间</label>
					<input type="text" class="form-control" name="workTime" id="workTime" value=${bean.workTime!} placeholder="输入工作时间...">
				</div>
				<div class="form-group">
					<label id="deptLabel">工作地址</label>
					<input type="text" class="form-control" name="workAddress" id="workAddress" value=${bean.workAddress!} placeholder="输入地址...">
				</div>
				<div class="form-group">
					<label id="introduceLabel">描述</label>
					<input type="text" class="form-control" name="description" id="description" value=${bean.description!} placeholder="输入描述...">
				</div>
				<div class="form-group">
					<label id="deptLabel">要求</label>
					<input type="text" class="form-control" name="requirement" id="requirement" value=${bean.requirement!} placeholder="输入要求...">
				</div>
				<div class="form-group">
					<label id="introduceLabel">福利待遇</label>
					<input type="text" class="form-control" name="fringeBenefits" id="fringeBenefits" placeholder="输入福利待遇..." value=${bean.fringeBenefits!} >
				</div>
            </div>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i
                            class="fa fa-close"></i>关闭
                    </button>
                    <button type="submit" class="btn btn-primary btn-sm" ><i
                            class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
