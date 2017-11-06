<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">职位管理</h3>
				<div class="box-tools pull-right">
					<@shiro.hasPermission name="job/add">
						<a onclick="addOurToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg" href="/addOur/add">添加</a>
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
							<input type="text" name="date" class="form-control pull-right" id="addOurTime" placeholder="选择时间...">
						</div>
					</div>
					
					<div class="col-md-4">
						<button type="submit" onclick="addOurReload();" class="btn btn-primary">搜索</button>
					</div>
				</div>
				<table id="addOur_tab" class="table table-bordered table-striped">
					<thead>
						<tr>
							<tr>
								<th>序号</th>
								<th>标题/th>
								<th>需要人数</th>
								<th>学历</th>
								<th>经验</th>
								<th>工资</th>
								<th>工作时间</th>
								<th>工作地点</th>
								<th>描述</th>
								<th>要求</th>
								<th>福利</th>
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
var addOur_tab;
$(function() {
	//初始化时间选择器
	$('#addOurTime').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true
	});
	//初始化表格
	
	var No=0;
	addOur_tab=$('#addOur_tab').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		"language":{"url":"adminlte/plugins/datatables/language.json"},
		"ajax" : {"url":"/addOur/page","type":"post"},
		"columns":[ 
		    {"data":null}, 
		    {"data":"title"},
		    {"data":"needNum"},	
		    {"data":"education"},
		    {"data":"experience"},	
		    {"data":"wages"},
		    {"data":"workTime"},	
		    {"data":"workAddress"},
		    {"data":"description"},	
		    {"data":"requirement"},
		    {"data":"fringeBenefits"},			
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
					//btn = '<a class="btn btn-xs btn-info" target="modal" modal="lg" href="/addOur/view/'+ data.sid+ '">查看</a> &nbsp;';
                    //btn +='<@shiro.hasPermission name="job/edit">'
                    //+'<a class="btn btn-xs btn-info" onclick="addOurToListAjax();" target="modal" modal="lg" href="/addOur/edit/'+ data.sid+ '">修改</a> &nbsp;'
                    //+'</@shiro.hasPermission>'
                    btn += '<@shiro.hasPermission name="job/delete">'
                    +'<a class="btn btn-xs btn-default" callback="addOurReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/addOur/delete/'+ data.sid + '">删除</a>'
                    +'</@shiro.hasPermission>';
					
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
	
	$("#securitySeek").on("click",function(){
 		reloadTable(addOur_tab,"#addOurTime","#addOurSelect");
	});
});

function addOurReload(){
	reloadTable(addOur_tab,"#addOurTime","#addOurSelect");
}

function addOurToListAjax(){
	list_ajax = addOur_tab;
	console.log(list_ajax);
}
function isNull(data){
    return (data == "" || data == undefined || data == null) ? true : false;
}
</script>