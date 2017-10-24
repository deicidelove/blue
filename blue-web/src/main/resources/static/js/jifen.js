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
		Request.sendPostRequest(basePath + "/jifen/getlist/", {
			"data" : ""
		}, function(result) {
			if(result){
				var actList = eval("("+result+")");
				$.each(actList,function(){
					this
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