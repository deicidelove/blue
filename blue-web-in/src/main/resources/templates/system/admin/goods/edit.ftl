<div class="row">
    <div class="col-md-12">
        <form id="goodsEditForm">
            <input type="hidden" id="goodsId" name="goodsId" value="${bean.goodsId}">

            <div class="box-body">
                <div class="form-group">
                    <label id="goodsNameLabel">商品名称</label>
                    <input type="text" class="form-control" name="goodsName" id="goodsName" value="${bean.goodsName!}"
                           disabled placeholder="输入商品名称...">
                </div>
                <div class="form-group">
                   <label id="goodsTitleLabel">商品标题</label>
                    <input type="text" class="form-control" name="goodsTitle" id="goodsTitle" value="${bean.goodsTitle!}"
                           placeholder="输入商品标题...">
                </div>
                <div class="form-group">
                    <label>商品详情</label> 
                    <script id="editorEdit" name="goodsDetail" type="text/plain" style="width:100%;height:500px;" ></script>
                </div>
                
            </div>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i
                            class="fa fa-close"></i>关闭
                    </button>
                    <button onclick="goodsUpdate();" type="button" class="btn btn-primary btn-sm"><i
                            class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    function goodsUpdate() {
        debugger;
        $.ajax({
            url: '/goods/update',
            type: 'post',
            dataType: 'text',
            data: $("#goodsEditForm").serialize(),
            success: function (data) {
                var json = JSON.parse(data);
                if (json.status) {
                    $("#lgModal").modal('hide');
                    alertMsg("更新成功", "success");
                    reloadTable(list_ajax, "#goodsTime", "#goodsPremise");
                } else {
                    alertMsg("更新失败:" + json.msg, "success");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    }

</script>

<script type="text/javascript">
	var editContext = '${bean.goodsDetail}';
	UE.delEditor('editorEdit');
 	var ue = UE.getEditor('editorEdit');
 	 //异步回调
    ue.ready(function() {
         ue.execCommand('insertHtml', editContext);
    });
 
</script>