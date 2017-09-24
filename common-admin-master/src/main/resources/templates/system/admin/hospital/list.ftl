<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">医院概况管理</h3>
				<div class="box-tools pull-right">
					<@shiro.hasPermission name="user/add">
						<a onclick="securityToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg" href="/user/add">添加</a>
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
							<input type="text" class="form-control pull-right" id="securityTime" placeholder="选择时间...">
						</div>
					</div>
					<div class="col-md-4">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-search"></i></span>
							<input type="text" class="form-control" id="securityPremise" placeholder="根据账号搜索...">
						</div>
					</div>
					<div class="col-md-4">
						<button type="submit" onclick="securityReload();" class="btn btn-primary">搜索</button>
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
	$('#securityTime').datepicker({
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
			    	if(data.type==1){
			    		return "医院文化";
			    	}else if(data.type==2){
			    		return "医院环境";
			    	}else{
			    		return "医院简介";
			    	}
			    }
			},
            {
				"targets" : -1,
				"data" : null,
				"render" : function(data) {
//					debugger;
					var btn = "";
					btn = '<a class="btn btn-xs btn-primary" target="modal" modal="lg" href="/user/view/'+ data.id+ '">查看</a> &nbsp;';
					if(isNull(data.role) ||  'super' != data.role.value){
                        btn +='<@shiro.hasPermission name="user/edit">'
                        +'<a class="btn btn-xs btn-info" onclick="securityToListAjax();" target="modal" modal="lg" href="/user/edit/'+ data.id+ '">修改</a> &nbsp;'
                        +'</@shiro.hasPermission>'
                        +'<@shiro.hasPermission name="user/delete">'
                        +'<a class="btn btn-xs btn-default" callback="securityReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/user/delete/'+ data.id + '">删除</a>'
                        +'</@shiro.hasPermission>';
					}
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
	
	$("#securitySeek").on("click",function(){
 		reloadTable(hospital_tab,"#securityTime","#securityPremise");
	});
});

function securityReload(){
	reloadTable(hospital_tab,"#securityTime","#securityPremise");
}

function securityToListAjax(){
	list_ajax = hospital_tab;
	console.log(list_ajax);
}
function isNull(data){
    return (data == "" || data == undefined || data == null) ? true : false;
}
</script>