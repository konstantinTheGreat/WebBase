<%--
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User-page</title>
</head>
<body>
Profile
<br/>
Username: ${user_name}
<br/>
Email: ${email}
<br/>
<form action="controller">
    <input type="hidden" name="command" value="go_to_change_password">
    <input type="submit" value="change password">
</form>
<form action="controller">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="logout">
</form>
</body>
</html>
