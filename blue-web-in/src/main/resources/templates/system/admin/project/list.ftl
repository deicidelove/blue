<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">项目管理</h3>
				<div class="box-tools pull-right">
					<@shiro.hasPermission name="project/add">
						<a onclick="projectToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg" href="/project/add">添加</a>
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
							<input type="text" name ="date" class="form-control pull-right" id="projectTime" placeholder="选择时间...">
						</div>
					</div>
					<div class="col-md-4">
						
					</div>
					<div class="col-md-4">
						<button type="submit" onclick="projectReload();" class="btn btn-primary">搜索</button>
					</div>
				</div>
				<table id="project_tab" class="table table-bordered table-striped">
					<thead>
						<tr>
							<tr>
								<th>序号</th>
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
var project_tab;
$(function() {
	//初始化时间选择器
	$('#projectTime').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true
	});
	//初始化表格
	
	var No=0;
	project_tab=$('#project_tab').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		"language":{"url":"adminlte/plugins/datatables/language.json"},
		"ajax" : {"url":"/project/page","type":"post"},
		"columns":[ 
		    {"data":null}, 
			{"data":"name"},
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
				"targets" : -1,
				"data" : null,
				"render" : function(data) {
//					debugger;
					var btn = "";
					btn = '<a class="btn btn-xs btn-primary" target="modal" modal="lg" href="/project/view/'+ data.sid+ '">查看</a> &nbsp;';
					if(isNull(data.role) ||  'super' != data.role.value){
                        btn +='<@shiro.hasPermission name="project/edit">'
                        +'<a class="btn btn-xs btn-info" onclick="projectToListAjax();" target="modal" modal="lg" href="/project/edit/'+ data.sid+ '">修改</a> &nbsp;'
                        +'</@shiro.hasPermission>'
                        +'<@shiro.hasPermission name="project/delete">'
                        +'<a class="btn btn-xs btn-default" callback="projectReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/project/delete/'+ data.sid + '">删除</a>'
                        +'</@shiro.hasPermission>';
					}
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
	
	$("#securitySeek").on("click",function(){
 		reloadTable(project_tab,"#projectTime","#projectPremise");
	});
});

function projectReload(){
	reloadTable(project_tab,"#projectTime","#projectPremise");
}

function projectToListAjax(){
	list_ajax = project_tab;
	console.log(list_ajax);
}
function isNull(data){
    return (data == "" || data == undefined || data == null) ? true : false;
}
</script>