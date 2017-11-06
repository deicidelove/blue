<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">资源管理</h3>
				<div class="box-tools pull-right">
					<@shiro.hasPermission name="source/add">
						<a onclick="sourceToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg" href="/source/add">添加</a>
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
							<input type="text" name="date" class="form-control pull-right" id="sourceTime" placeholder="选择时间...">
						</div>
					</div>
					<div class="col-md-4">
						<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-search"></i></span>
								
								 <select name="search" id="sourceNameSelect"  class="form-control select2" style="width: 100%;">
									<option value="-1">全部</option>
									<#list depts as dept>
		                   				<option value="${dept.sid}">${dept.name}</option>
		                			</#list>
	                			</select>
							</div>
					</div>
					<div class="col-md-4">
						<button type="submit" onclick="sourceReload();" class="btn btn-primary">搜索</button>
					</div>
				</div>
				<table id="source_tab" class="table table-bordered table-striped">
					<thead>
						<tr>
							<tr>
								<th>序号</th>
								<th>标题</th>
								<th>科室</th>
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
var source_tab;
$(function() {
	//初始化时间选择器
	$('#sourceTime').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true
	});
	//初始化表格
	
	var No=0;
	source_tab=$('#source_tab').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		"language":{"url":"adminlte/plugins/datatables/language.json"},
		"ajax" : {"url":"/source/page","type":"post"},
		"columns":[ 
		    {"data":null}, 
			{"data":"title"},
			{"data":"deptName"},
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
					
					btn = '<a class="btn btn-xs btn-info" target="modal" modal="lg" href="/source/view/'+ data.sid+ '">查看</a> &nbsp;';
                    btn +='<@shiro.hasPermission name="source/edit">'
                        +'<a class="btn btn-xs btn-info" onclick="sourceToListAjax();" target="modal" modal="lg" href="/source/edit/'+ data.sid+ '">修改</a> &nbsp;'
                        +'</@shiro.hasPermission>'
                        +'<@shiro.hasPermission name="source/delete">'
                        +'<a class="btn btn-xs btn-default" callback="sourceReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/source/delete/'+ data.sid + '">删除</a>'
                        +'</@shiro.hasPermission>';
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
	
	$("#securitySeek").on("click",function(){
 		reloadTable(source_tab,"#sourceTime","#sourceNameSelect");
	});
});

function sourceReload(){
	reloadTable(source_tab,"#sourceTime","#sourceNameSelect");
}

function sourceToListAjax(){
	list_ajax = source_tab;
	console.log(list_ajax);
}
function isNull(data){
    return (data == "" || data == undefined || data == null) ? true : false;
}
</script>