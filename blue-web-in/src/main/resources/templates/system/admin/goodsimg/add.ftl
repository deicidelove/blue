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
		<form id="advertAddForm" method="post" enctype="multipart/form-data" action="/goodsimg/save" target="advertAddTarget">
			<div class="modal-body">
				<div class="form-group">
					<label id="goodsIdLabel">商品id</label>
					<input type="text" class="form-control" name="goodsId" id="goodsId" placeholder="输入商品Id...">
				</div>
				<div class="form-group">
					<label>图片类型</label> 
					<select name="imgType" class="form-control select2" style="width: 100%;">
                        <option select value="list_img">列表图</option>
                        <option value="ad_img">轮询图</option>
                    </select>
				</div>
	
				 <div class="form-group">
                    <label>上传图片</label>
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


