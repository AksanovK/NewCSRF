<%@ page import="ru.itis.javalab.models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Камиль
  Date: 20.10.2020
  Time: 2:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="color: ${cookie.get("color").value}">USERS</h1>
<form action="/users" method="post">
    <input name="password" type="password">
    <select name="color">
        <option value="red">RED</option>
        <option value="green">GREEN</option>
        <option value="blue">BLUE</option>
    </select>
    <input type="submit" value="OK">
</form>

<table>
    <th>ID</th>
    <th>FIRST NAME</th>
    <th>LAST NAME</th>
    <th>AGE</th>
    <%
        List<User> users = (List<User>) request.getAttribute("usersForJsp");
        for (int i = 0; i < users.size(); i++) {
            %>
    <tr>
        <td><%=users.get(i).getId()%></td>
        <td><%=users.get(i).getEmail()%></td>
        <td><%=users.get(i).getPassword()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
