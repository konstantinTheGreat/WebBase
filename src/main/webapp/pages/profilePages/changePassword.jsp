<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25.04.24
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="controller">
    <input type="hidden" name="command" value="change_password"/>
    Old password: <input type="password" name="old_password" value=""/>
        <br/>
    New password: <input type="password" name="new_password" value=""/>
        <br/>
    <input type="submit" name="sub" value="Submit"/>
        <br/>
</form>
</body>
</html>
