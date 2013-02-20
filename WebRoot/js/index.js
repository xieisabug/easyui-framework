var login_user = {};
var tabs = {
    checkTabExists:function (text) {
        return $("#content").tabs('exists', text);
    },
    select:function (text) {
        $('#content').tabs('select', text);
    },
    open:function (text, url, js) {
        if (js) {
            $.get('js/' + js);
        }
        if (this.checkTabExists(text)) {
            this.select(text);
        } else {
            $('#content').tabs('add', {
                title:text,
                closable:true,
                href:url
            });

        }
    }
};
var jbox = {
    options:{
        width:1000,
        height:550,
        top:'5%',
        buttons:{
            '取消':false
        }
    },
    open:function (title, type, href, options) {
        var option = this.options;
        if (options) {
            if (options.width)option.width = options.width;
            if (options.height)option.height = options.height;
            if (options.top)option.top = options.top;
            if (options.buttons)option.buttons = options.buttons;
        }
        if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
            $.jBox(type + ':' + href, {
                title:title,
                width:option.width,
                height:option.height,
                top:option.top,
                draggable:false,
                buttons:option.buttons
            });
        } else {
            $.jBox(type + ':' + href, {
                title:title,
                width:option.width,
                height:option.height,
                top:option.top,
                buttons:option.buttons
            });
        }
    }
};
function refresh() {
};
$(function () {
    var msg = "";
    msg += "<div id='welcome_msg' style='height:200px;'>";
    msg += "</div>";
    $.ajax({
        type:"post",
        url:"/zk/getInfoWelcome",
        data:{
            "level":login_user.level,
            "siteId":login_user.site,
            "d":new Date().getTime()
        },
        success:function (data) {
            var result = eval('(' + data + ')');
            var html = "";
            var tip = [
                "咦？在列头单击右键，出现了什么？",
                "有时候，双击一行数据可能有惊喜的功能哦~",
                "最新的分页工具条可以改变每页显示的数据条数了~",
                "查询选项不用的时候可以收起来，这样更方便显示哦~",
                "修改了数据但是没变化？单击刷新数据试试看吧。",
                "系统的功能再慢慢完善，请耐心等待新的惊喜哦~",
                "可以拖动一列了~~快试试吧。"
            ];
            var i = Math.floor(Math.random() * ( tip.length ));
            html += "<p>尊敬的" + login_user.name + ",祝您今天有好的心情！</p>";
            html += "<p>目前系统中已经有 <span style='color:#f00;'>" + result.stuNum + "</span> 位学生。</p>";
            html += "<p>下一次的考试时间为 <span style='color:#f00;'>" + result.examDate + "</span>。请尽快做好准备</p><br><br>";
            html += "<p>小提示： <span style='color:#f00; font-size:15px;'> " + tip[i] + "</span></p>";
            $("#welcome_msg").append(html);
        }
    });

    $.jBox.messager(msg, '欢迎您');
    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            refresh();
        }
    });
});