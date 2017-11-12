<div class="row">
	<div class="col-md-12">
		<form id="goodsAddForm">
			<div class="modal-body">
				<div class="form-group">
					<label id="acitIdLabel">活动Id</label>
					<input type="text" class="form-control" name="actId" id="actId" placeholder="输入活动Id...">
				</div>
				<div class="form-group">
					<label id="goodsNameLabel">商品名称</label>
					<input type="text" class="form-control" name="goodsName" id="goodsName" placeholder="输入商品名称...">
				</div>
				<div class="form-group">
					<label id="goodsTitleLabel">商品标题</label>
					<input type="text" class="form-control" name="goodsTitle" id=""goodsTitle"" placeholder="输入商品标题...">
				</div>
				<div class="form-group">
					<label id="goodsPriceLabel">商品价格</label>
					<input type="text" class="form-control" name="goodsPrice" id="goodsPrice" placeholder="输入商品价格...">
				</div>
				<div class="form-group">
					<label>商品积分</label> 
					<input type="text" class="form-control" name="jifen" id="jifen" placeholder="输入商品积分...">
				</div>
				<div class="form-group">
					<label>商品详情</label> 
					<script id="editor" name="goodsDetail" type="text/plain" style="width:100%;height:500px;"></script>
				</div>
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="securitySave();"><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
function securitySave(){
	$("span").remove(".errorClass");
	$("br").remove(".errorClass");
	var status = 1;
	if($("#actId").val()==""){
		$("#acitIdLabel").prepend('<span class="errorClass" style="color:red">*活动Id不能为空</span><br class="errorClass"/>');
		status = 0;
	}
	if(!isNumber(parseInt($("#goodsPrice").val()))){
		$("#goodsPriceLabel").prepend('<span class="errorClass" style="color:red">*价格输入 数字</span><br class="errorClass"/>');
		status = 0;
	}
	if(status == 0){
		return false;
	}else{
		$.ajax({
			url: '/goods/save',
	        type: 'post',
	        dataType: 'text',
	        data: $("#goodsAddForm").serialize(),
	        success: function (data) {
                var json = JSON.parse(data);
                if (json.status){
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功","success");
                    reloadTable(list_ajax,"#goodsTime","#goodsPremise");
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
	        }
		});
	}
}

function isNumber(obj) {  
    return typeof obj === 'number' && !isNaN(obj)  
}  
</script>
<script type="text/javascript">
 var ue = UE.getEditor('editor');
 </script>