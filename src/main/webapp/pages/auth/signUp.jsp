<%@ include file="../set_language.jsp" %>

<html lang="${lang}">
<head>
    <title>Title</title>
</head>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="verification"/>
    <fmt:message key="username" />
    <input type="text" name="username" value=""/>
    <br/>
    <fmt:message key="password" />
    <input type="password" name="password" value=""/>
    <br/>
    <fmt:message key="email" />
    <input type="email" name="email" value=""/>
        <br/>
        <input type="submit" name="sub" value="<fmt:message key="buttonSubmit"/>"/>
        <br/>
        ${signup_error}
</form>
</body>
</html>
