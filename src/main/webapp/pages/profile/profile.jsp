<%@ include file="../set_language.jsp" %>
<html lang="${lang}">

<head>
    <title>User-page</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <script type="text/javascript" src="js/scripts.js"></script>
</head>

<body class= "button-container background-color">

<h1><fmt:message key="profile"/></h1>
<br/>
<% String imagePath = (String) session.getAttribute("imagePath"); %>
<% if (imagePath != null && !imagePath.isEmpty()) { %>
<img src="<%= imagePath %>" alt="Uploaded Image" class="image"/>
<% } %>
<br/>

<h2><fmt:message key="username"/> ${user.getUsername()}</h2>
<br/>

<h2><fmt:message key="email"/> ${user.getEmail()}</h2>


<br/>
<form action="controller" method="post">
    <input type="hidden" name="command" value="go_to_change_password">
    <input type="submit" value="<fmt:message key="buttonChangePassword"/>">
</form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="<fmt:message key="buttonLogout"/>">
</form>
<form action="controller" method="post" onsubmit="return confirmDelete();">
    <input type="hidden" name="command" value="delete_user">
    <button type="submit" class="custom-button"><fmt:message key="buttonDeleteAccount"/></button>
</form>
<form action="controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="upload_file">
    <input type="file" id="myFile" name="file" >
    <input type="submit" value="<fmt:message key="buttonSubmit"/>">
</form>
<form action="change_language" method="post">
    <input type="hidden" name="command" value="change_language">
    <input type="hidden" name="current_page" value="${pageContext.request.servletPath}">
    <input type="hidden" name="current_language" value="${lang}">
    <input type="submit" name="change_language" value="<fmt:message key="change_language"/>"/>
</form>



</body>
</html>



