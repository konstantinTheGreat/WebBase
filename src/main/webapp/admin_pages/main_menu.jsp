

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User-page</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <script type="text/javascript" src="js/scripts.js"></script>
</head>
<body class= "button-container background-color">
<form action="controller">
    <input type="hidden" name="command" value="see_all_users">    // add a list of all users and deletion option
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

