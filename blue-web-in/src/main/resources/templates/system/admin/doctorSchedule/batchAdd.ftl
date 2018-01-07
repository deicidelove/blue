
<script type="text/javascript">
		var textStr;
		$("#doctorSchBatchAddTarget").load(function(){
		     debugger;
			 textStr=$(this);
			 var data = textStr[0].contentDocument.body.textContent;
		  	 var json = JSON.parse(data);
            if (json.status){
	                 $(this).removeData("bs.modal"); 
		  			 $(".modal-body").children().remove();
	                    $("#lgModal").modal('hide');
	                    alertMsg(json.msg,"success");
	                    reloadTable(list_ajax,"#doctorSchListTime","#deptNameSelect");
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
		 })
          
</script>
<iframe id="doctorSchBatchAddTarget" name="doctorSchBatchAddTarget" style="display:none"></iframe>
<div class="row"  >
	<div class="col-md-12">
		<form id="doctorSchBatchAddForm" method="post" enctype="multipart/form-data" action="/doctorSchedule/batchSave" target="doctorSchBatchAddTarget">
			<div class="modal-body">
				 <div class="form-group">
                    <label>排班上传</label>
                    <input type="file" class="form-control" name="fileName" id="fileName" value="排班上传" >
                </div>
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal" onclick="doctorClose();"><i class="fa fa-close"></i>关闭</button>
					<button type="submit" class="btn btn-primary btn-sm" onclick="return checkData()"><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
 
 <script>
	 $('.close').on('click',function(){
	 		debugger;
		   $(this).removeData("bs.modal"); 
		   $(".modal-body").children().remove();
		   $("#lgModal").modal('hide');
	 
	 });
   function doctorClose(){
    	debugger;
		   $(this).removeData("bs.modal"); 
		   $(".modal-body").children().remove();
		    $("#lgModal").modal('hide');
   }
            //JS校验form表单信息  
         function checkData(){  
            var fileDir = $("#fileName").val();  
            if(!fileDir){  
                alert("选择需要导入的Excel文件！");  
                return false;  
            }  
            var suffix = fileDir.substr(fileDir.lastIndexOf(".")); 
            if(suffix  && ".xls" != suffix && ".xlsx" != suffix ){  
                alert("选择Excel格式的文件导入！");  
                return false;  
            }  
            return true;  
         } 
</script>
