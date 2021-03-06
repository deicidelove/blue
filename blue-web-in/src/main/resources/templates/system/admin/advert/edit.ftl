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
        <form id="advertEditForm" method="post" enctype="multipart/form-data" action="/advert/update" target="target">
            <input type="hidden" id="id" name="sid" value="${bean.sid!}">

            <div class="box-body">
                <div class="form-group">
                    <label id="titleLabel">标题</label>
                    <input type="text" class="form-control" name="title" id="title" value="${bean.title!}"
                           placeholder="输入标题...">
                </div>
                <div class="form-group">
                    <label id="contextLabel">文本</label>
                    <script id="editor" name="context" type="text/plain" style="width:100%;height:500px;" ></script>
                </div>
                <div class="form-group">
                    <label>类型</label>
                    <select name="type" class="form-control select2" style="width: 100%;">
                        <option <#if bean.type == 0>selected="selected"</#if> value="0">通知</option>
                        <option <#if bean.type == 1>selected="selected"</#if> value="1">公告</option>
                    </select>
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
                    <button type="submit" class="btn btn-primary btn-sm" ><i
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
