<%--
  Created by IntelliJ IDEA.
  User: Камиль
  Date: 31.10.2020
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration</h1>
<!--<form method="post" action="${pageContext.request.contextPath}/registration">
    <fieldset>
        <legend>Credentials</legend>
        <label>Email<input name="email" type="text" required></label>
        <label>Password<input name="password" type="password" required></label>
    </fieldset>
    <input type="submit" value="Save">
</form>-->
<div>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input name="email" placeholder="Your email">
        <input type="password" name="password" placeholder="Your password">
        <input type="submit" value="Send">
    </form>
</div>

</body>
</html>
