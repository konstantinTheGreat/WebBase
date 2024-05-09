<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 15.04.24
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="controller">
    <input type="hidden" name="command" value="verification"/>
    Username: <input type="text" name="username" value=""/>
        <br/>
    Password: <input type="password" name="password" value=""/>
        <br/>
    Email: <input type="email" name="email" value=""/>
        <br/>
        <input type="submit" name="sub" value="Submit"/>
        <br/>
        ${signup_error}
</form>
</body>
</html>
