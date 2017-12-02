<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">项目中心广告位管理</h3>
				<div class="box-tools pull-right">
					<@shiro.hasPermission name="project/add">
						<a onclick="projectAdvertToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg" href="/projectAdvert/add">添加</a>
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
							<input type="text" name ="date" class="form-control pull-right" id="projectAdvertTime" placeholder="选择时间...">
						</div>
					</div>
					<div class="col-md-4">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-search"></i></span>
							<select name="search" id="projectAdvertSelect"  class="form-control select2" style="width: 100%;">
								<option select value="-1">全部</option>
								<option value="0">首页轮播图</option>
		                        <option value="1">首页中间广告</option>
		                        <option value="2">首页近期活动广告</option>
		                        <option value="3">项目中心轮播图</option>
		                        <option value="4">项目中心所有项目大图</option>
		                        <option value="5">项目中心底部广告</option>
		                        <option value="6">项目中心上半部广告</option>
		                        <option value="7">口腔百科轮播图</option>
		                        <option value="8">口腔百科中间广告</option>
		                        <option value="9">科室轮播图</option>
		                        <option value="10">蓝鲟官网-积分抽奖广告</option>
		                        <option value="11">蓝鲟官网-近期活动广告</option>
		                        <option value="12">福利中心-积分抽奖广告</option>
		                        <option value="13">福利中心-近期活动广告</option>
		                    	<option value="14">福利中心-轮询广告</option>
		                    	<option value="15">项目中心所有项目小图</option>
	                		</select>
						</div>
					</div>
					<div class="col-md-4">
						<button type="submit" onclick="projectAdvertReload();" class="btn btn-primary">搜索</button>
					</div>
				</div>
				<table id="projectAdvert_tab" class="table table-bordered table-striped">
					<thead>
						<tr>
							<tr>
								<th>序号</th>
								<th>标题</th>
								<th>文本</th>
								<th>跳转地址</th>
								<th>类型</th>
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
var projectAdvert_tab;
$(function() {
	//初始化时间选择器
	$('#projectAdvertTime').datepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true
	});
	//初始化表格
	
	var No=0;
	
	$('#projectAdvert_tab').dataTable().fnDestroy();
	projectAdvert_tab=$('#projectAdvert_tab').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		"language":{"url":"adminlte/plugins/datatables/language.json"},
		"ajax" : {"url":"/projectAdvert/page","type":"post"},
		"columns":[ 
		    {"data":null}, 
			{"data":"title"},
			{"data":"context"},
			{"data":"jumpUrl"},
			{"data":"type"},
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
					btn = '<a class="btn btn-xs btn-primary" target="modal" modal="lg" href="/projectAdvert/view/'+ data.sid+ '">查看</a> &nbsp;';
					if(isNull(data.role) ||  'super' != data.role.value){
                        btn +='<@shiro.hasPermission name="project/edit">'
                        +'<a class="btn btn-xs btn-info" onclick="projectAdvertToListAjax();" target="modal" modal="lg" href="/projectAdvert/edit/'+ data.sid+ '">修改</a> &nbsp;'
                        +'</@shiro.hasPermission>'
                        +'<@shiro.hasPermission name="project/delete">'
                        +'<a class="btn btn-xs btn-default" callback="projectAdvertReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/projectAdvert/delete/'+ data.sid + '">删除</a>'
                        +'</@shiro.hasPermission>';
					}
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
	
	$("#securitySeek").on("click",function(){
 		reloadTable(projectAdvert_tab,"#projectAdvertTime","#projectAdvertSelect");
	});
});

function projectAdvertReload(){
	reloadTable(projectAdvert_tab,"#projectAdvertTime","#projectAdvertSelect");
}

function projectAdvertToListAjax(){
	list_ajax = projectAdvert_tab;
	console.log(list_ajax);
}
function isNull(data){
    return (data == "" || data == undefined || data == null) ? true : false;
}
</script>