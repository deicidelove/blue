$(function() {
	PA.Personal.initClick();
});

window.PA = window.PA || {};
PA.Personal = (function () {
	function Personal(){
		
	};
	Personal.prototype.initClick = function(){
		if($("#order")){
			$("#order").off('click');
			$("#order").on('click',function(){
				window.location.href = basePath +"/personal/order";
			});
		}
		if($("#invitation")){
			$("#invitation").off('click');
			$("#invitation").on('click',function(){
				window.location.href = basePath +"/personal/invitation";
			});
		}
		if($("#buyjifen")){
			$("#buyjifen").off('click');
			$("#buyjifen").on('click',function(){
				window.location.href = basePath +"/personal/buyjifen";
			});
		}
		if($("#jifendetail")){
			$("#jifendetail").off('click');
			$("#jifendetail").on('click',function(){
				window.location.href = basePath +"/personal/jifendetail";
			});
		}
	};
	return new Personal;
})();
/**
 * 公共方法及变量
 */
var CommonUtil = {

};

/**
 * 错误提示信息
 */
var ErrorMessage = {};