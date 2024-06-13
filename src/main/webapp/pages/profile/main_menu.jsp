<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28.05.24
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User-page</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <script type="text/javascript" src="js/scripts.js"></script>
</head>
<body class= "button-container background-color">
<form action="controller">
    <input type="hidden" name="command" value="play">
    <button type="submit" class="custom-button">Play</button>
</form>
<form action="controller">
    <input type="hidden" name="command" value="profile">
    <button type="submit" class="custom-button">Profile</button>
</form>
<form action="controller">
    <input type="hidden" name="command" value="logout">
    <button type="submit" class="negative-user-button">logout</button>
</form>
</body>
</html>
