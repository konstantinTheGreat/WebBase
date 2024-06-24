<%@ include file="../set_language.jsp" %>
<html lang="${lang}">
<head>
    <title>User-page</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <script type="text/javascript" src="js/scripts.js"></script>
</head>
<body class= "button-container background-color">
<form action="controller" method="post">
    <input type="hidden" name="command" value="go_to_game">
    <button type="submit" class="custom-button"><fmt:message key="buttonPlay"/></button>
</form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="profile">
    <button type="submit" class="custom-button"><fmt:message key="buttonProfile"/></button>
</form>
<form action="change_language" method="post">
    <input type="hidden" name="command" value="change_language">
    <input type="hidden" name="current_page" value="${pageContext.request.servletPath}">
    <input type="hidden" name="current_language" value="${lang}">
    <button type="submit" name="change_language" class="custom-button"><fmt:message key="change_language"/></button>
</form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="logout">
    <button type="submit" class="negative-user-button"><fmt:message key="buttonLogout"/></button>
</form>

</body>
</html>
