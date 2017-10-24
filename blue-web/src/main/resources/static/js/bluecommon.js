/**
 * Created with JetBrains WebStorm.
 * User: Administrator
 * Date: 14-6-5
 * Time: 下午2:04
 * To change this template use File | Settings | File Templates.
 */


/**
 * 利用占位符格式化字符串
 * 例如："你好，{0}, {1}".format('aa', 'Nice to meet you!') = "你好，aa Nice to meet you!";
 */
String.prototype.format = function () {
    var s = this, i = arguments.length;

    while (i--) {
        s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);
    }
    return s;
};

/**
 *js继承
 * @param subClass
 * @param superClass
 */
function extend(subClass, superClass) {
    var F = function () {
    };
    F.prototype = superClass.prototype;
    subClass.prototype = new F();
    subClass.prototype.constructor = subClass;
    subClass.superClass = superClass.prototype;
    if (superClass.prototype.constructor == Object.prototype.constructor) {
        superClass.prototype.constructor = superClass;
    }
}

/**
 * 截获URL参数
 * @type {{QueryString: Function}}
 */
var Request = {
    //获取URL参数
    QueryString: function (item) {
        var svalue = location.search.match(new RegExp("[\?\&]" + item + "=([^\&]*)(\&?)", "i"));
        return svalue ? svalue[1] : svalue;
    },

    /**
     * url 目标url
     * arg 需要替换的参数名称
     * arg_val 替换后的参数的值
     * return url 参数替换后的url
     */
    changeURLArg: function (url, arg, arg_val) {
        var pattern = arg + '=([^&]*)';
        var replaceText = arg + '=' + arg_val;
        if (url.match(pattern)) {//如果没有此参数，添加
            var tmp = '/(' + arg + '=)([^&]*)/gi';
            tmp = url.replace(eval(tmp), replaceText);
            return tmp;
        } else {//如果有此参数，修改
            if (url.match('[\?]')) {
                return url + '&' + replaceText;
            } else {
                return url + '?' + replaceText;
            }
        }
        return url + '\n' + arg + '\n' + arg_val;
    },
    /**
     * 去除指定的url参数
     * @param url
     * @param param
     * @returns {*}
     */
    cutURLParam: function (url, param) {
        var url1 = url;
        if (url.indexOf(param) > 0) {
            if (url.indexOf("&", url.indexOf(param) + param.length) > 0) {
                url1 = url.substring(0, url.indexOf(param) - 1) + url.substring(url.IndexOf("&", url.indexOf(param) + param.length) + 1);
            }
            else {
                url1 = url.substring(0, url.indexOf(param) - 1);
            }
            return url1;
        }
        else {
            return url1;
        }
    },

    HTTPErrorParams: {
        message: '<div><center style="font-size:18px;">请求失败，请稍候再试...</center></div>',
        result: "failed"
    },

    sendPostRequest: function (url, params, callback) {
        // / <summary>发送Action消息</summary>
        // / <param name="url" type="String">请求地址</param>
        // / <param name="params" type="Object">参数</param>
        // / <param name="callback" type="Function">消息执行回调</param>
        $.ajax(
            url,
            {
                type: "POST",
                dataType: "json",
                data: params || {},
                success: function (result) {
                    if (callback) {
                        if (result == null || result == undefined) {
                            result = Request.HTTPErrorParams;
                        }
                        callback(result);
                    }
                },
                error: function (request, textStatus, errorThrown) {
                    /// 通常 textStatus 和 errorThrown 之中只有一个会包含信息
                    try {
                        var result = eval('({0})'.format(request.responseText));
                        if (result) {
                            if (callback) {
                                callback(result);
                            }
                        } else {
                            if (callback) {
                                callback(Request.HTTPErrorParams);
                            }
                        }
                    } catch (e) {
                        if (callback) {
                            callback(Request.HTTPErrorParams);
                        }
                    }
                }
            });
    },

    /**
     * 跨域 ajax 请求
     * edit by rlcai
     * 添加方法参数，为保证调用时非请求内参数能够传递
     * @param url  地址
     * @param paramInfo  参数，对象
     * @param successCallBack     成功回调
     * @param errorCallBack       错误回调
     */
    crossAjaxPost: function (url, paramInfo, successCallBack, errorCallBack) {
        var params = arguments[4];

        if (currentUser) {
            if (!paramInfo) {
                paramInfo = {};
            }
            paramInfo.user = currentUser;
        }

        AjaxUtil.post(url, paramInfo, function (result) {
            if (typeof successCallBack === "function") {
                if (params == undefined) {
                    successCallBack(result);
                } else {
                    successCallBack(result, params);
                }
            }
        });
    },

    sendSynchronizeRequest: function (url, params, callback) {
        // / <summary>发送Action消息</summary>
        // / <param name="url" type="String">请求地址</param>
        // / <param name="params" type="Object">参数</param>
        // / <param name="callback" type="Function">消息执行回调</param>
        $.ajax(
            url,
            {
                type: "POST",
                dataType: "json",
                async: false,
                data: params || {},
                success: function (result) {
                    if (callback) {
                        if (result == null || result == undefined) {
                            result = Request.HTTPErrorParams;
                        }
                        callback(result);
                    }
                },
                error: function (request, textStatus, errorThrown) {
                    /// 通常 textStatus 和 errorThrown 之中只有一个会包含信息
                    try {
                        var result = eval('({0})'.format(request.responseText));
                        if (result) {
                            if (callback) {
                                callback(result);
                            }
                        } else {
                            if (callback) {
                                callback(Request.HTTPErrorParams);
                            }
                        }
                    } catch (e) {
                        if (callback) {
                            callback(Request.HTTPErrorParams);
                        }
                    }
                }
            });
    },

    sendGetRequest: function (url, params, callback) {
        // / <summary>发送Request消息</summary>
        // / <param name="url" type="String">请求地址</param>
        // / <param name="params" type="Object">参数</param>
        // / <param name="callback" type="Function">Request执行回调</param>
        $.ajax(
            url,
            {
                type: "GET",
                dataType: "json",
                data: params || {},
                success: function (result) {
                    if (callback) {
                        if (result == null || result == undefined) {
                            result = "没有返回数据！";
                        }
                        callback(result);
                    }
                },
                error: function (request, textStatus, errorThrown) {
                    // 通常 textStatus 和 errorThrown 之中只有一个会包含信息
                    try {
                        var result = eval('({0})'.format(request.responseText));
                        if (result) {
                            if (callback) {
                                callback(result);
                            }
                        } else {
                            if (callback) {
                                callback(Request.HTTPErrorParams);
                            }
                        }
                    } catch (e) {
                        if (callback) {
                            callback(Request.HTTPErrorParams);
                        }
                    }
                }
            });
    }
};
var artDialogCommon = {

    /**
     * artDialog 锁屏
     */
    lock: function () {
        var content = '<center style="font-size:18px;">数据获取中，请稍后...</center>';
        if (arguments[0] == "commit") {
            content = '<center style="font-size:18px;">数据提交中，请稍后...</center>';
        } else if (arguments[0] == "check") {
            content = '<center style="font-size:18px;">数据检测中，请稍后...</center>';
        }
        art.dialog({
            title: false,
            lock: true,
            background: 'gray', // 背景色
            opacity: 0.4,	// 透明度
            content: content,
            cancel: false,
            esc: false
        });
    },

    /**
     * artDialog 关闭（全部关闭）
     */
    close: function () {
        var list = art.dialog.list;
        for (var i in list) {
            list[i].close();
        }
    },

    /**
     * 仅有确定按钮的弹出框
     * @param title
     * @param content
     * @param okCallback
     */
    normal: function (title, content, okCallback) {
        var paramInfo = arguments[3];
        art.dialog({
            title: title,
            content: content,
            background: "gray",
            opacity: 0.4,
            ok: function () {
                if (typeof okCallback === "function") {
                    var res = okCallback(paramInfo);
                    if (res == false) {
                        return false;
                    }
                }
            },
            okVal: '确定',
            width: 600,
            height: "auto",
            lock: true,
            esc: false
        });
    },
    /**
     * 仅有确定按钮的弹出框
     * 弹出框 width height 自适应。
     * @param title
     * @param content
     * @param okCallback
     */
    normalAuto: function (title, content, okCallback) {
        art.dialog({
            title: title,
            content: content,
            background: "gray",
            opacity: 0.4,
            ok: function () {
                if (typeof okCallback === "function") {
                    var res = okCallback();
                    if (res == false) {
                        return false;
                    }
                }
            },
            okVal: '确定',
            width: 750,
            height: "auto",
            lock: true,
            esc: false,
            padding: '10px 10px'
        });
    },

    /**
     * 试卷设置弹出框
     * @param title
     * @param content
     * @param okCallback
     * @param cancelCallback
     */
    examSet: function (title, content, okCallback, cancelCallback) {
        art.dialog({
            title: title,
            content: content,
            background: "gray",
            opacity: 0.4,
            ok: function () {
                if (typeof okCallback === "function") {
                    var res = okCallback();
                    if (res == false) {
                        return false;
                    }
                }
            },
            okVal: '确定',
            width: 730,
            height: 550,
            lock: true,
            esc: false
        });
        //        $(".aui_buttons button").each(function () {
        //            var valText = $(this).html();
        //            if (valText == "取消") {
        //                $(this).css({"display": "none"});
        //            }
        //        });

    },

    /**
     * 无按钮
     * @param title
     * @param content
     */
    noButton: function (title, content) {
        art.dialog({
            title: title,
            content: content,
            background: "gray",
            opacity: 0.4,
            width: 500,
            height: "auto",
            lock: true,
            esc: false
        });
    },

    /**
     *
     * @param title
     * @param content
     * @param okCallback
     * @param cancelCallback
     */
    okAndCancel: function (title, content, okCallback, cancelCallback, okTitle, cancelTitle) {
        art.dialog({
            title: title,
            content: content,
            background: "gray",
            opacity: 0.2,
            ok: function () {
                if (typeof okCallback === "function") {
                    okCallback();
                }
            },
            okVal: okTitle ? okTitle : '确定',
            cancel: function () {
                if (typeof cancelCallback === "function") {
                    cancelCallback();
                }
            },
            cancelVal: cancelTitle ? cancelTitle : '取消',
            lock: true,
            esc: false
        });
    },

    /**
     *
     * @param title
     * @param content
     * @param okCallback
     * @param cancelCallback
     * @param paramInfo
     */
    okAndCel: function (title, content, okCallback, cancelCallback, paramInfo) {
        var okTitle = paramInfo.okTitle;
        art.dialog({
            title: title,
            content: content,
            background: "gray",
            opacity: 0.4,
            ok: function () {
                if (typeof okCallback === "function") {
                    okCallback(paramInfo);
                }
            },
            okVal: okTitle ? okTitle : '确定',
            cancel: function () {
                if (typeof cancelCallback === "function") {
                    cancelCallback(paramInfo);
                }
            },
            cancelVal: '取消',
            lock: true,
            esc: false
        });
    },
    mask: function (title, content) {
        art.dialog({
            title: title,
            lock: true,
            background: 'gray', // 背景色
            opacity: 0.2,	// 透明度
            content: content,
            width: "auto",
            cancel: false,
            esc: false
        });
    },

    cancelDisplay: function (title, content, cancelCallback) {
        art.dialog({
            title: title,
            lock: false,
            content: content,
            drag: true,
            resize: true,
            cancelVal: '关闭',
            height: "auto",
            cancel: function () {
                if (typeof cancelCallback === "function") {
                    cancelCallback();
                }
            },
            esc: false
        })
    },

    /**
     * 自动关闭提示对话框
     * @param time
     * @param content
     */
    autoClose: function (time, content) {
        art.dialog({
            time: time,
            background: "gray",
            opacity: 0.4,
            width: 500,
            height: "auto",
            lock: true,
            content: content
        });
    },
    /**
     * 自动关闭提示对话框
     * add by xlzhou2 2014-11-12 11:22:00
     * @param time
     * @param content
     */
    autoClosed: function (time, content) {
        art.dialog({
            title: "温馨提示",
            time: time,
            background: "gray",
            opacity: 0.4,
            width: 300,
            height: "auto",
            lock: true,
            content: content
        });
    },

    /**
     * 为纯文本添加样式
     * @param str
     * @returns {string}
     */
    addTextStyle: function (str) {
        return '<center style="font-size:18px;">' + str + '</center>';
    }


};


var CommonText = {
    nullData: '<div><center style="font-size:18px;">数据获取失败，请联系管理员！</center></div>',
    /**
     * 班级获取为空，提示语
     */
    classNull: '<div><center style="font-size:18px;">班级获取失败，请确认该年级内是否创建班级！</center></div>',

    exception: '<center style="font-size:18px;">数据异常，请联系管理员</center>',

    "getFormatMassage": function (msg) {
        return '<div><center style="font-size:18px;">' + msg + '</center></div>'
    }
};
/**
 * 退出登录功能
 */
function logout() {
    var edusso = new EduSSO(serviceUrl, casUrl);
    edusso.logout(function (result) {
        if (result && result.result != "success") {
            self.location.reload();
        } else {
            self.location.reload();
        }
    });
}