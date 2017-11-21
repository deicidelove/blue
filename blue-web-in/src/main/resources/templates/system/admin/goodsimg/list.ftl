<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">商品图片管理</h3>
				<div class="box-tools pull-right">
					<@shiro.hasPermission name="goodsImgUpload">
						<a onclick="securityToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg" href="/goodsimg/goodsimgupload">图片上传</a>
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
							<input type="text" class="form-control pull-right" id="securityTime" placeholder="选择时间...">
						</div>
					</div>
					<div class="col-md-4">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-search"></i></span>
							<input type="text" class="form-control" id="securityPremise" placeholder="根据商品Id搜索...">
						</div>
					</div>
					<div class="col-md-4">
						<button type="submit" onclick="securityReload();" class="btn btn-primary">搜索</button>
					</div>
				</div>
				<table id="goods_img_tab" class="table table-bordered table-striped">
					<thead>
						<tr>
							<tr>
								<th>序号</th>
								<th>商品Id</th>
								<th>图片类型</th>
								<th>图片地址</th>
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
var goods_img_tab;
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
	$('#goods_img_tab').dataTable().fnDestroy();
	goods_img_tab=$('#goods_img_tab').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		"language":{"url":"adminlte/plugins/datatables/language.json"},
		"ajax" : {"url":"/goodsimg/page","type":"post"},
		"columns":[ 
		    {"data":null}, 
			{"data":"goodsId"},
			{"data":null},
			{"data":"goodsImgUrl"},
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
                    var imgType = data.imgType;
                    if("list_img" == imgType){
                    	return "列表图片";
                    }else if("ad_img" == imgType){
                    	return "商品轮询图片";
                    }
                }
            },
            {
				"targets" : -1,
				"data" : null,
				"render" : function(data) {
//					debugger;
					var btn = "";
					btn = '<a class="btn btn-xs btn-primary" target="modal" modal="lg" href="/goodsimg/view/'+ data.goodsImgId+ '">查看</a> &nbsp;';
					if(isNull(data.role) ||  'super' != data.role.value){
                        btn += '<@shiro.hasPermission name="goodsDelete">'
                        +'<a class="btn btn-xs btn-default" callback="securityReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/goodsimg/delete/'+ data.goodsImgId + '">删除</a>'
                        +'</@shiro.hasPermission>';
					}
					return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
		No=0;
    } );
	
	$("#securitySeek").on("click",function(){
 		reloadTable(goods_img_tab,"#securityTime","#securityPremise");
	});
});

function securityReload(){
	reloadTable(goods_img_tab,"#securityTime","#securityPremise");
}

function securityToListAjax(){
	list_ajax = goods_img_tab;
	console.log(list_ajax);
}
function isNull(data){
    return (data == "" || data == undefined || data == null) ? true : false;
}
</script>