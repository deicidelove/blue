<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">科室管理</h3>
				<div class="box-tools pull-right">
					<@shiro.hasPermission name="user/add">
						<a onclick="deptToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg" href="/blueDept/add">添加</a>
					</@shiro.hasPermission>
				</div>
			</div>
			<div class="box-body">
				<table id="blueDept_tab" class="table table-bordered table-striped">
					<thead>
						<tr>
							<tr>
								<th>序号</th>
								<th>科室名</th>
								<th>介绍</th>
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
var blueDept_tab;
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
	blueDept_tab=$('#blueDept_tab').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		"language":{"url":"adminlte/plugins/datatables/language.json"},
		"ajax" : {"url":"/blueDept/page","type":"post"},
		"columns":[ 
		    {"data":null}, 
		    {"data":"name"},
		    {"data":"context"},			
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
				"targets" : -1,
				"data" : null,
				"render" : function(data) {
//					debugger;
					var btn = "";
                        btn +='<@shiro.hasPermission name="user/edit">'
                        +'<a class="btn btn-xs btn-info" onclick="deptToListAjax();" target="modal" modal="lg" href="/blueDept/edit/'+ data.sid+ '">修改</a> &nbsp;'
                        +'</@shiro.hasPermission>'
                        +'<@shiro.hasPermission name="user/delete">'
                        +'<a class="btn btn-xs btn-default" callback="blueDeptReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/blueDept/delete/'+ data.sid + '">删除</a>'
                        +'</@shiro.hasPermission>';
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
	
	$("#securitySeek").on("click",function(){
 		reloadTable(blueDept_tab,"#dept","#deptSelect");
	});
});

function blueDeptReload(){
	reloadTable(blueDept_tab,"#dept","#deptSelect");
}

function deptToListAjax(){
	list_ajax = blueDept_tab;
	console.log(list_ajax);
}
function isNull(data){
    return (data == "" || data == undefined || data == null) ? true : false;
}
</script>