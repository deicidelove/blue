<script type="text/javascript">
		var textStr;
		$("#lastActEditTarget").load(function(){
		     debugger;
			 textStr=$(this);
			 var data = textStr[0].contentDocument.body.textContent;
		 
		  	 var json = JSON.parse(data);
            if (json.status) {
                $("#lgModal").modal('hide');
                alertMsg("更新成功", "success");
                reloadTable(list_ajax, "#goodsTime", "#goodsPremise");
            } else {
                alertMsg("更新失败:" + json.msg, "success");
	            }
		 })

</script>
<div class="row">
    <div class="col-md-12">
    <iframe id="lastActEditTarget" name="lastActEditTarget" style="display:none"></iframe>
        <form id="lastActEditForm" method="post" enctype="multipart/form-data" action="/lastact/update" target="lastActEditTarget">
            <input type="hidden" id="lastActId" name="sid" value="${bean.sid}">

            <div class="box-body">
                <div class="form-group">
                    <label id="goodsNameLabel">最近活动名称</label>
                    <input type="text" class="form-control" name="lastActName" id="lastActName" value="${bean.lastActName!}"
                           disabled placeholder="最近活动名称...">
                </div>
                <div class="form-group">
                    <label id="goodsNameLabel">最近活动英文名称</label>
                    <input type="text" class="form-control" name="lastActEnname" id="lastActEnname" value="${bean.lastActEnname!}"
                           disabled placeholder="最近活动英文名称...">
                </div>
                <div class="form-group">
                   <label id="goodsTitleLabel">最近活动标题</label>
                    <input type="text" class="form-control" name="lastActTitle" id="lastActTitle" value="${bean.lastActTitle!}"
                           placeholder="输入最近活动标题...">
                </div>
                <div class="form-group">
                    <label>最近活动内容</label> 
                    <script id="editor" name="lastActContent" type="text/plain" style="width:100%;height:500px;" ></script>
                </div>
                
                <div class="form-group">
                	<img src="${bean.lastActListImg!}" style="width:200px;height:200px"/>
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


<script type="text/javascript">
	var editContext = '${bean.lastActContent}';
	UE.getEditor('editor');
 	var ue = UE.getEditor('editor');
 	 //异步回调
    ue.ready(function() {
         ue.execCommand('insertHtml', editContext);
    });
 
</script>