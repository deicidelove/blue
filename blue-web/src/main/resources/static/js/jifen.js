$(function() {
	JF.init();
});

window.JF = window.JF || {};
JF = {
	/**
	 * 界面初始化事件绑定
	 */
	"init" : function() {
		JF.getActList();
	},

	//获取列表信息
	"getActList" : function() {
		$(".unveiled_ul").html("");
		Request.sendPostRequest(basePath + "/jifen/getlist/", {
			"data" : ""
		}, function(result) {
			if(result && result.length > 0){
				$.each(result,function(){
					var li = "<li class=\"unveiled_li left\">";
					var devName = "<div class=\"unveiled_text01\">"+this.goodsName+"</div>";
					var devTitle = "<div class=\"unveiled_text02\">"+this.goodsTitle+"</div>";
					var devBlock = "<div class=\"box unveiled_text03\"></div>";
					var devBlock_1 = "<div ></div>";
					$(devBlock_1).addClass("flex-1");
					var devBlock_1_1 = "<div class=\"_color01\"></div>";
					$(devBlock_1_1).append(this.goodsPrice);
					var devBlock_1_2 = "<div>抢购</div>";
					var devBlock_2 = "<div ></div>";
					$(devBlock_2).addClass("flex-1");
					var devBlock_2_1 = "<div class=\"_color01\"></div>";
					$(devBlock_2_1).append(this.actTotalNum - this.remainingNum);
					var devBlock_2_2 = "<div>已参与</div>";
					var devBlock_3 = "<div ></div>";
					$(devBlock_3).addClass("flex_1");
					var devBlock_3_1 = "<div class=\"_color01\">"+this.remainingNum+"</div>";
					var devBlock_3_2 = "<div>剩余</div>";
					
					$(devBlock_1).append(devBlock_1_1).append(devBlock_1_2);
					$(devBlock_2).append(devBlock_2_1).append(devBlock_2_2);
					$(devBlock_3).append(devBlock_3_1).append(devBlock_3_2);	
					$(devBlock).append(devBlock_1).append(devBlock_2).append(devBlock_3);
					$(li).append(devName).append(devTitle).append(devBlock);
					$(".unveiled_ul").append(li);
				})
			}
		});
	},
};

/**
 * 公共方法及变量
 */
var CommonUtil = {

};

/**
 * 错误提示信息
 */
var ErrorMessage = {};