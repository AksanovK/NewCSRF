<%--
  Created by IntelliJ IDEA.
  User: Камиль
  Date: 28.10.2020
  Time: 8:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignIn</title>
</head>
<body>
<h1>Login</h1>
<div>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input name="email" placeholder="Your email">
        <input type="password" name="password" placeholder="Your password">
        <input type="submit" value="Send">
    </form>
</div>
<!--<form method="post" action="/login">
    <fieldset>
        <legend>Your credentials</legend>
        <label>Username <input name = "username" type="text" required></label>
        <label>Password <input name="password" type="password" required></label>
    </fieldset>
    <input type="submit" value="Enter">
</form>-->

</body>
</html>
