<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 26.04.24
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="controller">
    <input type="hidden" name="command" value="add_user"/>
    verification code: <input type="text" name="verification_code" value=""/>
    <br/>
    <input type="submit" name="sub" value="Submit"/>
    <br/>
    ${verification_error}
</form>
</body>
</html>
