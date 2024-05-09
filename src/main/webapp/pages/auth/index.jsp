<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>login-page</title>
</head>
<body>

<br/>
<form action="controller">
        <input type="hidden" name="command" value="login"/>
    Login: <input type="text" name="username" value=""/>
        <br/>
    Password: <input type="password" name="password" value=""/>
        <br/>
        <input type="submit" name="sub" value="Submit"/>
        <br/>
        ${login_error}
</form>
<form action="controller">
    <input type="hidden" name="command" value="signup">
    <input type="submit" value="signup">
</form>
</body>
</html>