<%@ include file="../set_language.jsp" %>
<html lang="${lang}">
<head>
    <title>Title</title>
</head>
<body>
<fmt:message key="successful" />
<form action="controller" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="<fmt:message key="backButton"/>">
</form>
</body>
</html>
