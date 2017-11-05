<div class="row">
	<div class="box-body  no-padding">
		<table class="table table-striped">
			<tr>
				<td>活动名称：</td>
				<td style="width: 90%">${bean.actName!}</td>
			</tr>
			<tr>
				<td>活动数量：</td>
				<td>${bean.actTotalNum!}</td>
			</tr>
			<tr>
				<td>中奖数量:</td>
				<td> ${bean.actGivingNum!}</td>
			</tr>
			<tr>
				<td>当前期数：</td>
				<td>
					${bean.actPeriods!}
				</td>
			</tr>
		</table>
		<div class="box-footer">
			<div class="pull-right">
				<button type="button" class="btn btn-default btn-sm" id="close" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
			</div>
      	</div>
	</div>
</div>