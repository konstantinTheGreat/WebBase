<%@ include file="../set_language.jsp" %>

<html lang="${lang}">
<head>
    <title>Title</title>
</head>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="add_user"/>
    <fmt:message key="verificationCode" />
    <input type="text" name="verification_code" value=""/>
    <br/>
    <input type="submit" name="sub" value="<fmt:message key="buttonSubmit"/>"/>
    <br/>
    ${verification_error}
</form>
</body>
</html>
