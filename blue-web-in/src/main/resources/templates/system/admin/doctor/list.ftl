<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">医生管理</h3>
				<div class="box-tools pull-right">
					<@shiro.hasPermission name="user/add">
						<a onclick="doctorToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg" href="/doctor/add">添加</a>
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
							<input type="text" name="date" class="form-control pull-right" id="doctorTime" placeholder="选择时间...">
						</div>
					</div>
					<div class="col-md-4">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-search"></i></span>
							<select name="search" id="deptDoctorSelect"  class="form-control select2" style="width: 100%;">
								<option value="-1">全部</option>
								<#list depts as dept>
	                   				<option value="${dept.sid}">${dept.name}</option>
	                			</#list>
	                		</select>
						</div>
					</div>
					<div class="col-md-4">
						<button type="submit" onclick="doctorReload();" class="btn btn-primary">搜索</button>
					</div>
				</div>
				<table id="doctor_tab" class="table table-bordered table-striped">
					<thead>
						<tr>
							<tr>
								<th>序号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>电话</th>
								<th>科室</th>
								<th>地址</th>
								<th>工号</th>
								<th>介绍</th>
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
var doctor_tab;
$(function() {
	//初始化时间选择器
	$('#doctorTime').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true
	});
	//初始化表格
	debugger;
	var No=0;
	doctor_tab=$('#doctor_tab').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		"language":{"url":"adminlte/plugins/datatables/language.json"},
		"ajax" : {"url":"/doctor/page","type":"post"},
		"columns":[ 
		    {"data":null}, 
			{"data":"name"},
			{"data":null},
			{"data":"phone"},
			{"data":"deptName"},
			{"data":"address"},
			{"data":"jobNum"},
			{"data":"introduce"},
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
			    targets: 2,
			    data: null,
			    render: function (data) {
			    	if(data.sex==0){
			    		return "女";
			    	}else{
			    		return "男";
			    	}
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
                        +'<a class="btn btn-xs btn-info" onclick="doctorToListAjax();" target="modal" modal="lg" href="/doctor/edit/'+ data.sid+ '">修改</a> &nbsp;'
                        +'</@shiro.hasPermission>'
                        +'<@shiro.hasPermission name="user/delete">'
                        +'<a class="btn btn-xs btn-default" callback="doctorReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/doctor/delete/'+ data.sid + '">删除</a>'
                        +'</@shiro.hasPermission>';
					}
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
	
	$("#securitySeek").on("click",function(){
 		reloadTable(doctor_tab,"#doctorTime","#deptDoctorSelect");
	});
});

function doctorReload(){
	debugger;
	reloadTable(doctor_tab,"#doctorTime","#deptDoctorSelect");
}

function doctorToListAjax(){
	list_ajax = doctor_tab;
	console.log(list_ajax);
}
function isNull(data){
    return (data == "" || data == undefined || data == null) ? true : false;
}
</script>