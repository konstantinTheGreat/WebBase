<%@ include file="../set_language.jsp" %>
<html lang="${lang}">
<head>
    <title>Title</title>
</head>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="change_password"/>
    <fmt:message key="oldPassword" />
    <input type="password" name="old_password" value=""/>
    <br/>
    <fmt:message key="newPassword" />
    <input type="password" name="new_password" value=""/>
    <br/>
    <input type="submit" name="sub" value="<fmt:message key="buttonSubmit"/>"/>
    <br/>
</form>
</body>
</html>
