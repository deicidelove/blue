<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">医院概况管理</h3>
				<div class="box-tools pull-right">
					<@shiro.hasPermission name="hospital/add">
						<a onclick="hospitalToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg" href="/hospital/add">添加</a>
					</@shiro.hasPermission>
				</div>
			</div>
			<div class="box-body">
				<div class="clearfix">
					<div class="col-md-4">
						<div class="input-group date ">
							<div class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</div>
							<input type="text" class="form-control pull-right" id="hospitalTime" placeholder="选择时间...">
						</div>
					</div>
					<div class="col-md-4">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-search"></i></span>
							<select name="search" id="hospitalNameSelect"  class="form-control select2" style="width: 100%;">
								<option value="-1">全部</option>
	                   			<option value="0">医院简介</option>
	                   			<option value="1">医院文化</option>
	                   			<option value="2">医院环境</option>
                			</select>
						</div>
					</div>
					<div class="col-md-4">
						<button type="submit" onclick="hospitalReload();" class="btn btn-primary">搜索</button>
					</div>
				</div>
				<table id="hospital_tab" class="table table-bordered table-striped">
					<thead>
						<tr>
							<tr>
								<th>序号</th>
								<th>类型</th>
								<th>标题</th>
								<th>文本</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
var hospital_tab;
$(function() {
	//初始化时间选择器
	$('#hospitalTime').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true
	});
	//初始化表格
	
	var No=0;
	hospital_tab=$('#hospital_tab').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		"language":{"url":"adminlte/plugins/datatables/language.json"},
		"ajax" : {"url":"/hospital/page","type":"post"},
		"columns":[ 
		    {"data":null}, 
			{"data":null},
			{"data":"title"},
			{"data":"context"},
			{"data":"createTime"},
			{"data":null} 
			],
		"columnDefs":[
			{
			    targets: 0,
			    data: null,
			    render: function (data) {
			    	No=No+1;
			        return No;
			    }
			},
			{
			    targets: 1,
			    data: null,
			    render: function (data) {
			    	if(data.type==21 || data.type==22){
			    		return "医院文化";
			    	}else if(data.type==0){
			    		return "医院环境";
			    	}else{
			    		return "百年蓝鲟";
			    	}
			    }
			},
            {
				"targets" : -1,
				"data" : null,
				"render" : function(data) {
//					debugger;
					var btn = "";
					btn = '<a class="btn btn-xs btn-primary" target="modal" modal="lg" href="/hospital/view/'+ data.sid+ '">查看</a> &nbsp;';
					if(isNull(data.role) ||  'super' != data.role.value){
                        btn +='<@shiro.hasPermission name="hospital/edit">'
                        +'<a class="btn btn-xs btn-info" onclick="hospitalToListAjax();" target="modal" modal="lg" href="/hospital/edit/'+ data.sid+ '">修改</a> &nbsp;'
                        +'</@shiro.hasPermission>'
                        +'<@shiro.hasPermission name="hospital/delete">'
                        +'<a class="btn btn-xs btn-default" callback="hospitalReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/hospital/delete/'+ data.sid + '">删除</a>'
                        +'</@shiro.hasPermission>';
					}
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
	
	$("#securitySeek").on("click",function(){
 		reloadTable(hospital_tab,"#hospitalTime","#hospitalNameSelect");
	});
});

function hospitalReload(){
	reloadTable(hospital_tab,"#hospitalTime","#hospitalNameSelect");
}

function hospitalToListAjax(){
	list_ajax = hospital_tab;
	console.log(list_ajax);
}
function isNull(data){
    return (data == "" || data == undefined || data == null) ? true : false;
}
</script>