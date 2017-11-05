<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">应聘管理</h3>
			</div>
			<div class="box-body">
				<div class="clearfix">
					<div class="col-md-4">
						<div class="input-group date ">
							<div class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</div>
							<input type="text" name="date" class="form-control pull-right" id="wantTime" placeholder="选择时间...">
						</div>
					</div>
					
					<div class="col-md-4">
						<button type="submit" onclick="wantReload();" class="btn btn-primary">搜索</button>
					</div>
				</div>
				<table id="wantWork_tab" class="table table-bordered table-striped">
					<thead>
						<tr>
							<tr>
								<th>序号</th>
								<th>姓名</th>
								<th>电话</th>
								<th>学历</th>
								<th>目标工作</th>
								<th>理想薪资</th>
								<th>描述</th>
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
debugger;
var wantWork_tab;
$(function() {
	//初始化时间选择器
	$('#wantTime').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true
	});
	//初始化表格
	
	var No=0;
	wantWork_tab=$('#wantWork_tab').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		"language":{"url":"adminlte/plugins/datatables/language.json"},
		"ajax" : {"url":"/addOur/wantPage","type":"post"},
		"columns":[ 
		    {"data":null}, 
		    {"data":"name"},
		    {"data":"phone"},	
		    {"data":"profession"},
		    {"data":"wantJob"},	
		    {"data":"wantWage"},
		    {"data":"describe"},	
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
                    btn += '<@shiro.hasPermission name="user/delete">'
                    +'<a class="btn btn-xs btn-default" callback="wantReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/addOur/wantDelete/'+ data.sid + '">删除</a>'
                    +'</@shiro.hasPermission>';
					
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
	
	$("#securitySeek").on("click",function(){
 		reloadTable(wantWork_tab,"#wantTime","#addOurSelect");
	});
});

function wantReload(){
	reloadTable(wantWork_tab,"#wantTime","#addOurSelect");
}

function wantToListAjax(){
	list_ajax = wantWork_tab;
	console.log(list_ajax);
}
function isNull(data){
    return (data == "" || data == undefined || data == null) ? true : false;
}
</script>