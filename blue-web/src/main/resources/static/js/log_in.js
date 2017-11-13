/**
 * Created by lonvov2 on 2017/10/18.
 */
(function () {
    var log = {
        init:function () {
            log._bind();
        },
        _bind:function () {
            $('.code_gain').on('click',log._verifyCode);
            $('.register_submit').on('click',log._verification)
        },
        _verifyCode: function() {
            var $phone = $('.phone').val();
            var $phone = $('.phone').val();
            if (/^1(3[0-9]|5[012356789]|8[0-9]|7[0678])\d{8}$/.test($phone)) {
            	$.get("/msgVerify", {'phoneNumber': $phone}, function(result){
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
            var phone,code = false;
            var $phone = $('.phone').val();
            var $code = $('.code').val();
            if (/^1(3[0-9]|5[012356789]|8[0-9]|7[0678])\d{8}$/.test($phone)) {
                phone = true;
            }else{
                log._popx('请输入正确手机号码',1.5);
                return;
            }
            if ( $code != '') {
                code = true;
            }else{
                log._popx('请输入验证码',1.5);
                return;
            }
            
            if( phone || code ){
                $.get("/userLogin", {'phoneNumber': $phone, 'checkCode': $code}, function(result){
                    var returned = eval('(' + result + ')');
                    if ( returned.result == 'success' ) {
                        // 保存验证码
                        register._popx('登陆成功',1.5);
                        // 登陆成功实现跳转
                        $.get("/registerSuccessedRedirect", {'phoneNumber': $phone}, function(result){});
                    } else {
                        // 显示错误信息
                        register._popx(result.message, 1.5);
                    }
                });
            }
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
        }
    };
    log.init();
})();