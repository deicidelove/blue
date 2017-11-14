<script type="text/javascript">

	var textStr;
		$("#projectAdvertAddTarget").load(function(){
		     debugger;
			 textStr=$(this);
			 var data = textStr[0].contentDocument.body.textContent;
		  	 var json = JSON.parse(data);
           if (json.status){
	            $("#lgModal").modal('hide');
	            alertMsg("添加成功","success");
	            reloadTable(list_ajax,"#projectAdvertTime","#projectAdvertPremise");
            }else{
                alertMsg("添加失败:"+json.msg,"success");
            }
		 })

</script>
<div class="row">
<iframe id="projectAdvertAddTarget" name="projectAdvertAddTarget" style="display:none"></iframe>
	<div class="col-md-12">
		<form id="projectAdvertAddForm" method="post" enctype="multipart/form-data" action="/projectAdvert/save" target="projectAdvertAddTarget">
			<div class="modal-body">
				<div class="form-group">
                    <label id="titleLabel">标题</label>
                    <input type="text" class="form-control" name="title" id="title" "
                            placeholder="输入标题...">
                </div>
                <div class="form-group">
                    <label id="contextLabel">文本</label>
                    <input type="text" class="form-control" name="context" id="context""
                           placeholder="输入内容...">
                </div>
                
                <div class="form-group">
                    <label id="contextLabel">跳转地址</label>
                    <input type="text" class="form-control" name="jumpUrl" id="jumpUrl""
                           placeholder="输入跳转地址...">
                </div>
                <div class="form-group">
					<label>类型</label> 
					<select name="type" class="form-control select2" style="width: 100%;">
                        <option select value="0">首页轮播图</option>
                        <option value="1">首页中间广告</option>
                        <option value="2">首页近期活动广告</option>
                        <option value="3">项目中心轮播图</option>
                        <option value="4">项目中心中间广告</option>
                        <option value="5">项目中心底部广告</option>
                        <option value="6">项目中心上半部广告</option>
                        <option value="7">口腔百科轮播图</option>
                        <option value="8">口腔百科中间广告</option>
                        <option value="9">科室轮播图</option>
                        <option value="10">蓝鲟官网-积分抽奖广告</option>
                        <option value="11">蓝鲟官网-近期活动广告</option>
                        <option value="12">福利中心-积分抽奖广告</option>
                        <option value="13">福利中心-近期活动广告</option>
                    	<option value="14">福利中心-轮询广告</option>
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
