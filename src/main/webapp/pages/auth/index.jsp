<%@ include file="../set_language.jsp" %>

<!DOCTYPE html>
<html lang="${lang}">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<head>
    <title>login-page</title>


</head>
<body class= "button-container background-color">

<br/>

<form action="login" method="post">
    <input type="hidden" name="command" value="login"/>
    <fmt:message key="login" />
    <input type="text" name="username" value=""/>
    <br/>
    <br/>
    <fmt:message key="password" />
    <input type="password" name="password" value=""/>
    <br/>
    <br/>
    <input type="submit" name="sub" value="<fmt:message key="buttonSubmit"/>"/>
    <br/>
    ${login_error}
</form>
    <br/>
<form action="controller" method="post">
    <input type="hidden" name="command" value="signup">
    <input type="submit" value="<fmt:message key="buttonSignUp"/>">
    <br/>
</form>
    <br/>
<form action="change_language" method="post">
    <input type="hidden" name="command" value="change_language">
    <input type="hidden" name="current_page" value="${pageContext.request.servletPath}">
    <input type="hidden" name="current_language" value="${lang}">
    <input type="submit" name="change_language" value="<fmt:message key="change_language"/>"/>
</form>


</body>
</html>