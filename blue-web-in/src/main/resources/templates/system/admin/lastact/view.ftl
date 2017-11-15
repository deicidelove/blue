<div class="row">
	<div class="box-body  no-padding">
		<table class="table table-striped">
			<tr>
				<td>活动id：</td>
				<td style="width: 90%">${bean.actId!}</td>
			</tr>
			<tr>
				<td>商品 名称：</td>
				<td>${bean.goodsName!}</td>
			</tr>
			<tr>
				<td>商品标题:</td>
				<td style="width: 90%">${bean.goodsName!}</td>
			</tr>
			<tr>
				<td>商品价格：</td>
				<td>
					${bean.goodsPrice!}元
				</td>
			</tr>
			<tr>
				<td>商品积分：</td>
				<td>
					${bean.jifen!}积分
				</td>
			</tr>
			<tr>
				<td>创建时间：</td>
				<td>${bean.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
			</tr>
			<tr>
				<td>更新时间：</td>
				<#if bean.updateTime??>
                    <td>${bean.updateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
				</#if>
			</tr>
		</table>
		<div class="box-footer">
			<div class="pull-right">
				<button type="button" class="btn btn-default btn-sm" id="close" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
			</div>
      	</div>
	</div>
</div>