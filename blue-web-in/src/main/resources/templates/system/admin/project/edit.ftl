<script type="text/javascript">
	var textStr;
		$("#projectEditTarget").load(function(){
		     debugger;
			 textStr=$(this);
			 var data = textStr[0].contentDocument.body.textContent;
		 
		  	 var json = JSON.parse(data);
           if (json.status) {
                    $("#lgModal").modal('hide');
                    alertMsg("更新成功", "success");
                    reloadTable(list_ajax, "#projectTime","#projectPremise");
                } else {
                    alertMsg("更新失败:" + json.msg, "success");
                }
		 })
		 


</script>
<div class="row">
<iframe id="projectEditTarget" name="projectEditTarget" style="display:none"></iframe>
    <div class="col-md-12">
        <form id="projectEditForm" method="post" enctype="multipart/form-data" action="/project/update" target="projectEditTarget">
            <input type="hidden" id="id" name="sid" value="${bean.sid}">

           <div class="box-body">
                <div class="form-group">
                    <label id="titleLabel">标题</label>
                    <input type="text" class="form-control" name="title" id="title" value="${bean.name!}"
                           placeholder="输入标题...">
                </div>
                <div class="form-group">
                    <label id="contextLabel">文本</label>
                    <script id="editor" name="context" type="text/plain" style="width:100%;height:500px;" ></script>
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
                    <button  type="submit" class="btn btn-primary btn-sm"><i
                            class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
	var editContext = '${bean.context}';
 	var ue = UE.getEditor('editor');
 	 //异步回调
    ue.ready(function() {
         ue.execCommand('insertHtml', editContext);
    });
 
</script>
