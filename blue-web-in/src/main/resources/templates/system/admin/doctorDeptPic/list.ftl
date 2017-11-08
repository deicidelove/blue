<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">科室主治医生宣传管理</h3>
				<div class="box-tools pull-right">
					<@shiro.hasPermission name="doctorDept/add">
						<a onclick="doctorDeptToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg" href="/doctorDept/add">添加</a>
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
							<input type="text" name="date" class="form-control pull-right" id="doctorDeptTime" placeholder="选择时间...">
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
						<button type="submit" onclick="doctorDeptReload();" class="btn btn-primary">搜索</button>
					</div>
				</div>
				<table id="doctorDept_tab" class="table table-bordered table-striped">
					<thead>
						<tr>
							<tr>
								<th>序号</th>
								<th>科室名</th>
								<th>图片地址</th>
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
var doctorDept_tab;
$(function() {
	//初始化时间选择器
	$('#doctorDeptTime').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true
	});
	//初始化表格
	debugger;
	var No=0;
	doctorDept_tab=$('#doctorDept_tab').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		"language":{"url":"adminlte/plugins/datatables/language.json"},
		"ajax" : {"url":"/doctorDept/page","type":"post"},
		"columns":[ 
		    {"data":null}, 
			{"data":"deptName"},
			{"data":"url"},
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
                        btn +='<@shiro.hasPermission name="doctorDept/edit">'
                        +'<a class="btn btn-xs btn-info" onclick="doctorDeptToListAjax();" target="modal" modal="lg" href="/doctorDept/edit/'+ data.sid+ '">修改</a> &nbsp;'
                        +'</@shiro.hasPermission>'
                        +'<@shiro.hasPermission name="doctorDept/delete">'
                        +'<a class="btn btn-xs btn-default" callback="doctorDeptReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/doctorDept/delete/'+ data.sid + '">删除</a>'
                        +'</@shiro.hasPermission>';
					}
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
	
	$("#securitySeek").on("click",function(){
 		reloadTable(doctorDept_tab,"#doctorDeptTime","#deptDoctorSelect");
	});
});

function doctorDeptReload(){
	debugger;
	reloadTable(doctorDept_tab,"#doctorDeptTime","#deptDoctorSelect");
}

function doctorDeptToListAjax(){
	list_ajax = doctorDept_tab;
	console.log(list_ajax);
}
function isNull(data){
    return (data == "" || data == undefined || data == null) ? true : false;
}
</script>