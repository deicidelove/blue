/**
 * Created by lonvov2 on 2017/10/18.
 */
(function () {
    var register = {
        init:function () {
            register._bind();
        },
        _bind:function () {
            $('.code_gain').on('click',register._verifyCode);
            $('.register_submit').on('click',register._verification)
        },
        _popx: function(text, time, cb) {
            time = time * 1e3;
            var pop_info = "<span class='span-tip'>" + text + "</span>";
            $("body").append(pop_info);
            $(".span-tip").css("margin-left", function() {
                return -$(".span-tip")[0].offsetWidth / 2;
            });
            $(".span-tip").fadeOut(time, function() {
                $(this).remove();
                if (cb && typeof cb == "function") {
                    cb();
                }
            });
        },

        _verifyCode: function() {
            var $phone = $('.phone').val();
            var $phone = $('.phone').val();
            if (/^1(3[0-9]|5[012356789]|8[0-9]|7[0678])\d{8}$/.test($phone)) {
            	$.get("/login/msgVerify", {'phoneNumber': $phone}, function(result){
            		var returned = eval('(' + result + ')');
            		if ( returned.result == 'success' ) {
            			// 保存验证码
            			register._popx("短信发送成功，请注意查收！", 1.5);
            		} else {
            			// 显示错误信息
            			register._popx(result.message, 1.5);
            		}
            		// 倒计时
            		var step = 59;
            		$('.code_gain').val('重新发送 60 s').addClass('active_color');
            		var _res = setInterval(function(){
            			$('.code_gain').attr("disabled", true);
            			$('.code_gain').val('重新发送 '+step+' s');
            			step-=1;
            			if(step < 0){
            				$('.code_gain').removeAttr("disabled").removeClass('active_color');
            				$('.code_gain').val('重新发送');
            				clearInterval(_res);
            			}
            		},1000);
            	});
            }
        },

        _verification: function(){
            var name,phone,code = false;
            var $name = $('.name').val();
            var $phone = $('.phone').val();
            var $code = $('.code').val();
            if ( $name != '') {
                name = true;
            }else{
                register._popx('请输入用户名',1.5);
                return;
            }
            if (/^1(3[0-9]|5[012356789]|8[0-9]|7[0678])\d{8}$/.test($phone)) {
                phone = true;
            }else{
                register._popx('请输入正确手机号码',1.5);
                return;
            }
            if ( $code != '') {
                code = true;
            }else{
                register._popx('请输入验证码',1.5);
                return;
            }
            if( name || phone || code ) {
                $.get("/login/userRegister", {'phoneNumber': $phone, 'userName': $name, 'checkCode': $code}, function(result){
                    var returned = eval('(' + result + ')');
                    if ( returned.result == 'success' ) {
                        // 保存验证码
                        register._popx('注册成功',1.5);
                        // 注册成功实现跳转
//                        $.get("/login/registerSuccessedRedirect", {'phoneNumber': $phone}, function(result){});
                        location.href =  basePath+"/login/registerSuccessedRedirect";
                    } else {
                        // 显示错误信息
                        register._popx(result.message, 1.5);
                    }
                });
            }
        }
    };
    register.init();
})();