$(function() {
	JF.init();
});

window.JF = window.JF || {};
JF = {
	/**
	 * 界面初始化事件绑定
	 */
	"init" : function() {

	},

	//获取列表信息
	"getActList" : function() {
		$(".unveiled_ul").html("");
		Request.sendPostRequest(basePath + "/jifen/getlist/", {
			"data" : ""
		}, function(result) {
			if(result){
				var actList = eval("("+result+")");
				$.each(actList,function(){
					var li = "<li class=\"unveiled_li left\">";
					var devName = "<div class=\"unveiled_text01\"></div>";
					$(devName).append(this.goodsName);
					var devTitle = "<div class=\"unveiled_text02\"></div>";
					$(devName).append(this.goodsTitle);
					var devBlock = "<div class=\"box unveiled_text03\"></div>";
					var devBlock-1 = "<div class=\"flex-1\"></div>";
					var devBlock-1-1 = "<div class=\"_color01\"></div>";
					$(devBlock-1-1).append(this.goodsPrice);
					var devBlock-1-2 = "<div>抢购</div>";
					var devBlock-2 = "<div class=\"flex-1\"></div>";
					var devBlock-2-1 = "<div class=\"_color01\"></div>";
					$(devBlock-2-1).append(this.actTotalNum - this.remainingNum);
					var devBlock-2-2 = "<div>已参与</div>";
					var devBlock-3 = "<div class=\"flex-1\"></div>";
					var devBlock-3-1 = "<div class=\"_color01\">"+this.remainingNum+"</div>";
					var devBlock-3-2 = "<div>剩余</div>";
					
					$(devBlock-1).append(devBlock-1-1).append(devBlock-1-2);
					$(devBlock-2).append(devBlock-2-1).append(devBlock-2-2);
					$(devBlock-3).append(devBlock-3-1).append(devBlock-3-2);
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