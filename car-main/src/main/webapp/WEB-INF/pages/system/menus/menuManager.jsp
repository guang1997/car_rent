<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单管理</title>
</head>
<!-- 如果使用frameset 的包含页面  主页面不能有 body-->
<frameset cols="250,*" border="1">
    <frame src="${PATH}/sys/toMenuLeft" name="left">
    <frame src="${PATH}/sys/toMenuRight" name="right">
</frameset>
</html>
