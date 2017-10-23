<script type="text/javascript">

		var textStr;
		$("#hospitalEditTarget").load(function(){
		     debugger;
			 textStr=$(this);
			 var data = textStr[0].contentDocument.body.textContent;
		 
		  	 var json = JSON.parse(data);
           if (json.status) {
                    $("#lgModal").modal('hide');
                    alertMsg("更新成功", "success");
                    reloadTable(list_ajax,"#hospitalTime","#hospitalNameSelect");
                } else {
                    alertMsg("更新失败:" + json.msg, "success");
                }
		 })
   

</script>
<div class="row">
<iframe id="hospitalEditTarget" name="hospitalEditTarget" style="display:none"></iframe>
    <div class="col-md-12">
        <form id="hospitalEditForm" method="post" enctype="multipart/form-data" action="/hospital/update" target="hospitalEditTarget">
            <input type="hidden" id="id" name="sid" value="${bean.sid}">

            <div class="box-body">
                 <div class="form-group">
                    <label id="titleLabel">标题</label>
                    <input type="text" class="form-control" name="title" id="title" value="${bean.title!}"
                           placeholder="输入标题...">
                </div>
                <div class="form-group">
                    <label id="contextLabel">文本</label>
                    <input type="text" class="form-control" name="context" id="context" value="${bean.context!}"
                           placeholder="输入内容...">
                </div>
                <div class="form-group">
                    <label>类型</label>
                    <select name="type" class="form-control select2" style="width: 100%;">
                        <option <#if bean.type == 0>selected="selected"</#if> value="0">医院环境</option>
                        <option <#if bean.type == 1>selected="selected"</#if> value="1">百年蓝鲟</option>
                        <option <#if bean.type == 11>selected="selected"</#if> value="12">企业愿景</option>
                        <option <#if bean.type == 12>selected="selected"</#if> value="13">管理理念</option>
                        <option <#if bean.type == 13>selected="selected"</#if> value="14">企业使命</option>
                        <option <#if bean.type == 14>selected="selected"</#if> value="14">经营理念</option>
                        <option <#if bean.type == 21>selected="selected"</#if> value="21">推介理由</option>
                        <option <#if bean.type == 22>selected="selected"</#if> value="22">科室特色</option>
                    </select>
                </div>
                <div class="form-group">
                	<img src="${bean.url!}" style=""/>
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
