<div class="row">
	<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">医生排班管理</h3>
					<div class="box-tools pull-right">
						<@shiro.hasPermission name="sche/add">
							<a onclick="doctorSchAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg" href="/doctorSchedule/add">添加</a>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="scheBatchAdd">
							<a onclick="doctorSchAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg" href="/doctorSchedule/batchAdd">批量添加</a>
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
								<input name="date" type="text" class="form-control pull-right" id="doctorSchListTime" placeholder="选择时间...">
							</div>
						</div>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-search"></i></span>
								
								 <select name="search" id="deptNameSelect"  class="form-control select2" style="width: 100%;">
									<option value="-1">全部</option>
									<#list depts as dept>
		                   				<option value="${dept.sid}">${dept.name}</option>
		                			</#list>
	                			</select>
							</div>
						</div>
						<div class="col-md-4">
							<button type="submit" onclick="doctorSchReload();" class="btn btn-primary">搜索</button>
						</div>
					</div>
					<table id="DoctorSchedule_tab" class="table table-bordered table-striped">
						<thead>
							<tr>
								<tr>
									<th>序号</th>
									<th>姓名</th>
									<th>科室</th>
									<th>排班</th>
									<th>次数</th>
									<th>时间</th>
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
var DoctorSchedule_tab;
$(function() {
	//初始化时间选择器
	$('#doctorSchListTime').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true
	});
	//初始化表格
	 debugger;
	var No=0;
	$('#DoctorSchedule_tab').dataTable().fnDestroy();
	DoctorSchedule_tab=$('#DoctorSchedule_tab').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		"language":{"url":"adminlte/plugins/datatables/language.json"},
		"ajax" : {"url":"/doctorSchedule/page","type":"post"},
		"columns":[ 
		    {"data":null}, 
			{"data":"staffName"},
			{"data":"deptName"},
			{"data":"shiftTime"},
			{"data":"count"},
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
					btn = '';
					if(isNull(data.role) ||  'super' != data.role.value){
                       // btn +='<@shiro.hasPermission name="sche/edit">'
                        //+'<a class="btn btn-xs btn-info" onclick="doctorSchAjax();" target="modal" modal="lg" href="/doctorSchedule/edit/'+ data.sid+ '">修改</a> &nbsp;'
                       btn += '</@shiro.hasPermission>'
                        +'<@shiro.hasPermission name="sche/delete">'
                        +'<a class="btn btn-xs btn-default" callback="doctorSchReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/doctorSchedule/delete/'+ data.sid + '">删除</a>'
                        +'</@shiro.hasPermission>';
					}
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
	
	$("#securitySeek").on("click",function(){
	
 		reloadTable(DoctorSchedule_tab,"#doctorSchListTime","#deptNameSelect");
	});
});

function doctorSchReload(){
	debugger;
	reloadTable(DoctorSchedule_tab,"#doctorSchListTime","#deptNameSelect");
}
function test(){
debugger;
var No=0;
	DoctorSchedule_tab=$('#DoctorSchedule_tab').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		"language":{"url":"adminlte/plugins/datatables/language.json"},
		"ajax" : {"url":"/doctorSchedule/page","type":"post","data": $("#schForm").serialize()},
		"columns":[ 
		    {"data":null}, 
			{"data":"staffName"},
			{"data":"deptName"},
			{"data":"shiftTime"},
			{"data":"count"},
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
					btn = '';
					if(isNull(data.role) ||  'super' != data.role.value){
                        btn +='<@shiro.hasPermission name="user/edit">'
                        +'<a class="btn btn-xs btn-info" onclick="doctorSchAjax();" target="modal" modal="lg" href="/doctorSchedule/edit/'+ data.sid+ '">修改</a> &nbsp;'
                        +'</@shiro.hasPermission>'
                        +'<@shiro.hasPermission name="user/delete">'
                        +'<a class="btn btn-xs btn-default" callback="doctorSchReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/doctorSchedule/delete/'+ data.sid + '">删除</a>'
                        +'</@shiro.hasPermission>';
					}
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
}

function doctorSchAjax(){
	list_ajax = DoctorSchedule_tab;
	console.log(list_ajax);
}
function isNull(data){
    return (data == "" || data == undefined || data == null) ? true : false;
}
</script>