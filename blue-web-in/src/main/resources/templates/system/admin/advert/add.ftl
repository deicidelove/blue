<script type="text/javascript">

var textStr;
		$("#advertAddTarget").load(function(){
		     debugger;
			 textStr=$(this);
			 var data = textStr[0].contentDocument.body.textContent;
		  	 var json = JSON.parse(data);
            if (json.status){
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功","success");
                    reloadTable(list_ajax,"#advertTime","#advertSelect");
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
		 })

</script>
<div class="row">
<iframe id="advertAddTarget" name="advertAddTarget" style="display:none"></iframe>
	<div class="col-md-12">
		<form id="advertAddForm" method="post" enctype="multipart/form-data" action="/advert/save" target="advertAddTarget">
			<div class="modal-body">
				<div class="form-group">
                    <label id="titleLabel">标题</label>
                    <input type="text" class="form-control" name="title" id="title" "
                            placeholder="输入标题...">
                </div>
                <div class="form-group">
                    <label id="contextLabel">文本</label>
                    <script id="editor" name="context" type="text/plain" style="width:100%;height:500px;"></script>
                </div>
				<div class="form-group">
					<label>类型</label> 
					<select name="type" class="form-control select2" style="width: 100%;">
                        <option select value="0">通知</option>
                        <option value="1">公告</option>
                    </select>
				</div>
				 <div class="form-group">
                    <label>上传文件</label>
                    <input type="file" class="form-control" name="fileName" id="fileName" value="上传图片" >
                </div>
				
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
					<button type="submit" class="btn btn-primary btn-sm" ><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
 var ue = UE.getEditor('editor');
</script>
 <script>
   $(function () { 
	   $('#lgModal').on('hide.bs.modal', function () {
		   $(this).removeData("bs.modal"); 
		   $(".modal-body").children().remove();
      })
   });
</script>