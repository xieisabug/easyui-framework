$(function () {
    //**************下面是实现原理*************************
    $.extend($.fn.validatebox.defaults.rules, {
        minLength:{
            validator:function (value, param) {
                return value.length >= param[0];
            },
            message:'至少输入{0}个字符.'
        }
    });
    $.extend($.fn.validatebox.defaults.rules, {
        reapet:{
            validator:function (value, param) {
                return value == $(param[0]).val();
            },
            message:'和第一次输入的密码不匹配.'
        }
    });
    $.extend($.fn.validatebox.defaults.rules, {
        maxLength:{
            validator:function (value, param) {
                return value.length <= param[0];
            },
            message:'最多输入{0}个字符.'
        }
    });
    $.extend($.fn.validatebox.defaults.rules, {
        between:{
            validator:function (value, param) {
                if (value.length >= param[0] && value.length <= param[1])
                    return true;
                return false;
            },
            message:'{3}'
        }
    });
    $.extend($.fn.validatebox.defaults.rules, {
        equals_one:{
            validator:function (value, param) {
                if (value.length == param[0])
                    return true;
                return false;
            },
            message:'{2}'
        }
    });
    $.extend($.fn.validatebox.defaults.rules, {
        equals_two:{
            validator:function (value, param) {
                if (value.length == param[0] || value.length == param[1])
                    return true;
                return false;
            },
            message:'{3}'
        }
    });
    $.extend($.fn.validatebox.defaults.rules, {
        equals_three:{
            validator:function (value, param) {
                if (value.length == param[0] || value.length == param[1] || value.length == param[2])
                    return true;
                return false;
            },
            message:'{4}'
        }
    });
    $.extend($.fn.validatebox.defaults.rules, {
        mobile:{
            validator:function (value) {
                return /^1[0-9]{10}$/i.test($.trim(value));
            },
            message:'手机号码格式错误.'
        }
    });
    $.extend($.fn.validatebox.defaults.rules, {
        email:{
            validator:function (value) {
                return /^[a-zA-Z0-9_+.-]+\@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/i
                    .test($.trim(value));
            },
            message:'电子邮箱格式错误.'
        }
    });
    $.extend($.fn.validatebox.defaults.rules, {
        date1:{
            validator:function (value) {
                return /^[0-9]{4}[-][0-9]{2}[-][0-9]{2}$/i.test($.trim(value));
            },
            message:'曰期格式错误,如2012-09-11.'
        }
    });
    $.extend($.fn.validatebox.defaults.rules, {
        date2:{
            validator:function (value) {
                return /^[0-9]{4}[-][0-9]{2}$/i.test($.trim(value));
            },
            message:'曰期格式错误,如2012-09.'
        }
    });
    $.extend($.fn.validatebox.defaults.rules, {
        validateIdCard:{
            validator:function (value, param) {
                if (value.length == param[0] || param[1] == value.length) {
                    $.ajax({
                        type:"POST",
                        url:"/zk/validateStuStudent",
                        dataType:"json",
                        data:{
                            "idCard":value
                        },
                        success:function (data) {
                            if (data.result == "true") {
                                $.jBox.prompt('此学生已经存在', '警告', '',
                                    {
                                        closed:function () {
                                            $("#idCard").val("");
                                        }
                                    }
                                );
                                return false;
                            }
                        }
                    });
                } else {
                    param[2] = "身份证只有15和18位";
                    return false;
                }
                param[2] = "可继续输入.";
                return true;
            },
            message:"{2}"
        }
    });

    //******************下面是调用的方法*****************

    /**
     * 自动寻找required属性，并且设置不能为空
     * 简化页面的代码
     */
    $("*").each(function () {
        if ($(this).attr('required')) {
            $(this).validatebox({
                required:true,
                missingMessage:'不能为空'
            });
        }
    });

    /**
     * 用于表单校验，如果表单没有检验
     * 成功，就不能提交表单
     */
    form = function (id) {
        $("#" + id).submit(function () {
            if ($("#" + id).form('validate')) {

            } else {
                return false;
            }
        });
    };
    /**
     * 下面分别是
     * 最小长度
     * 最大长度
     * 处于某个长度之间
     */
    minLength = function (id, min) {
        $('#' + id).validatebox({
            validType:"minLength['" + min + "']"
        });
    };
    maxLength = function (id, max) {
        $('#' + id).validatebox({
            validType:"maxLength['" + max + "']"
        });
    };
    between = function (id, min, max, msg) {
        $('#' + id).validatebox({
            validType:"between['" + min + "','" + max + "','" + msg + "']",
            invalidMessage:msg
        });
    };
    /**
     * 用于多个数值长度的等于判断
     * 如身份证为15或18位等
     * equals* 就是判断*个参数
     */
    equals_one = function (id, fv, msg) {
        $('#' + id).validatebox({
            validType:"equals_one['" + fv + "','" + msg + "']",
            invalidMessage:msg
        });
    };
    equals_two = function (id, fv, sv, msg) {
        $('#' + id).validatebox({
            validType:"equals_two['" + fv + "','" + sv + "','" + msg + "']",
            invalidMessage:msg
        });
    };
    equals_three = function (id, fv, sv, tv, msg) {
        $('#' + id).validatebox({
            validType:"equals_three['" + fv + "','" + sv + "','" + tv + "','" + msg + "']",
            invalidMessage:msg
        });
    };
    /**
     * 用于密码重复输入的校验
     * first_id 指的是第一次输入密码的输入框id
     * reapet_id 指的是重复输入密码的输入框id
     */
    reapet = function (first_id, reapet_id, msg) {
        first_id = '#' + first_id;
        $('#' + reapet_id).validatebox({
            validType:"reapet['" + first_id + "']",
            invalidMessage:msg
        });
    };
    /**
     * 检测电话号码是否合格
     */
    mobile = function (id) {
        $('#' + id).validatebox({
            validType:"mobile[]"
        });
    };
    /**
     * ajax校验idCard是否重复
     */
    validateIdCard = function (fv, sv, msg) {
        $('#idCard').validatebox({
            validType:"validateIdCard['" + fv + "','" + sv + "','" + msg + "']"
        });
    }
});