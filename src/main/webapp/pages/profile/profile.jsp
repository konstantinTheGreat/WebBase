<%--
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User-page</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <script type="text/javascript" src="js/scripts.js"></script>
</head>

<body>
Profile
<br/>
<% String imagePath = (String) session.getAttribute("imagePath"); %>
<% if (imagePath != null && !imagePath.isEmpty()) { %>
<img src="<%= imagePath %>" alt="Uploaded Image" class="image"/>
<% } %>
<br/>
Username: ${username}
<br/>
Email: ${email}
<br/>
<form action="controller">
    <input type="hidden" name="command" value="go_to_change_password">
    <input type="submit" value="change password">
</form>
<form action="controller">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="logout">
</form>
<form action="controller" method="post" onsubmit="return confirmDelete();">
    <input type="hidden" name="command" value="delete_user">
    <button type="submit" class="custom-button">Delete Account</button>
</form>
<form action="controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="upload_file">
    <input type="file" id="myFile" name="file" >
    <input type="submit" value="submit">
</form>



</body>
</html>




<%--<input id="myFile" type="file"/> <br/>--%>
<%--<button onclick="uploadFile()"> Upload </button>--%>