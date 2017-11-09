<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">公告通知管理</h3>
				<div class="box-tools pull-right">
					<@shiro.hasPermission name="advert/add">
						<a onclick="advertToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg" href="/advert/add">添加</a>
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
							<input type="text" name="date" class="form-control pull-right" id="advertTime" placeholder="选择时间...">
						</div>
					</div>
					<div class="col-md-4">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-search"></i></span>
							<select name="search" id="advertSelect"  class="form-control select2" style="width: 100%;">
								 <option select value="-1">全部</option>
								 <option value="0">通知</option>
	                        	 <option value="1">公告</option>
                			</select>
						</div>
					</div>
					<div class="col-md-4">
						<button type="submit" onclick="advertReload();" class="btn btn-primary">搜索</button>
					</div>
				</div>
				<table id="advert_tab" class="table table-bordered table-striped">
					<thead>
						<tr>
							<tr>
								<th>序号</th>
								<th>类型</th>
								<th>标题</th>
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
var advert_tab;
$(function() {
	//初始化时间选择器
	$('#advertTime').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true
	});
	//初始化表格
	
	var No=0;
	advert_tab=$('#advert_tab').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		"language":{"url":"adminlte/plugins/datatables/language.json"},
		"ajax" : {"url":"/advert/page","type":"post"},
		"columns":[ 
		    {"data":null},
		    {"data":null}, 
		    {"data":"title"},
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
			    targets: 1,
			    data: null,
			    render: function (data) {
			    	if(data.type == 0){
			    		return "通知";
			    	}else{
			    		return "公告";
			    	}
			    }
			},	
            {
				"targets" : -1,
				"data" : null,
				"render" : function(data) {
//					debugger;
					var btn = "";
					btn = '<a class="btn btn-xs btn-info" target="modal" modal="lg" href="/advert/view/'+ data.sid+ '">查看</a> &nbsp;';
                    btn +='<@shiro.hasPermission name="advert/edit">'
                    +'<a class="btn btn-xs btn-info" onclick="advertToListAjax();" target="modal" modal="lg" href="/advert/edit/'+ data.sid+ '">修改</a> &nbsp;'
                    +'</@shiro.hasPermission>'
                    +'<@shiro.hasPermission name="advert/delete">'
                    +'<a class="btn btn-xs btn-default" callback="advertReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/advert/delete/'+ data.sid + '">删除</a>'
                    +'</@shiro.hasPermission>';
					
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
	
	$("#securitySeek").on("click",function(){
 		reloadTable(advert_tab,"#advertTime","#advertSelect");
	});
});

function advertReload(){
	reloadTable(advert_tab,"#advertTime","#advertSelect");
}

function advertToListAjax(){
	list_ajax = advert_tab;
	console.log(list_ajax);
}
function isNull(data){
    return (data == "" || data == undefined || data == null) ? true : false;
}
</script>