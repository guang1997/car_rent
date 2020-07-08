<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>手机验证</title>
</head>
<link rel="stylesheet" href="${PATH}/static/layui/css/layui.css" media="all">
<body>
<%--这里是手机验证的前端代码，与此项目无关，是之前做的放到这里了--%>
<br>
<br>
<br>
<br>
<div class="layui-form-item" align="center">
    <div class="layui-inline">
        <div class="layui-input-inline">
            <input id="phoneNum" type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入手机号">
        </div>
        <button type="button" class="layui-btn layui-btn-primary" id="sendCode" lay-filter="sendCode">发送验证码</button>
    </div>
    <br>
    <div class="layui-inline">
        <div class="layui-input-inline">
            <input id="code" type="text" name="code"  autocomplete="off" class="layui-input" placeholder="请输入验证码">
        </div>
        <button type="button" class="layui-btn layui-btn-primary" id="confirmCode" lay-filter="confirmCode">确认</button>
    </div>
    ${errorMsg}
</div>
<script src="${PATH}/static/layui/layui.js"></script>
<script>
    layui.use(['jquery', 'form', 'element', 'layer'], function () {
        var $ = layui.jquery;
        var form = layui.form;
        var element = layui.element;
        var layer = layui.layer;
        $("#sendCode").click(function () {
            var phone = $("#phoneNum").val();
            $.post("${PATH}/code/getcode", {phone:phone}, function (data) {
                layer.msg("您的验证码为：" + data + "有效期为2分钟，请尽快输入");
            });
        });
        $("#confirmCode").click(function () {
            var code = $("#code").val();
            alert(code);
            window.location = "${PATH}/code/confirmCode?code=" + code;
        });
    });
</script>
</body>
</html>

