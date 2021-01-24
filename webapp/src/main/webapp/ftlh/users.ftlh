<html lang="en">
<head>
    <title>Title</title>
</head>
<body>
<h1 style="color:${color.value}">USERS</h1>
<form action="/users" method="post">
    <!--<label>
        <input name="password" type="password">
    </label>-->
    <label>
        <select name="color">
            <option value="red">RED</option>
            <option value="green">GREEN</option>
            <option value="blue">BLUE</option>
        </select>
    </label>
    <input type="submit" value="OK">
</form>

<table>
    <th>ID</th>
    <th>FIRST NAME</th>
    <th>LAST NAME</th>
    <th>AGE</th>
    <#list users as listUser>
        <tr>
            <td>${listUser.id}</td>
            <td>${listUser.email}</td>
            <td>${listUser.password}</td>
        </tr>
    </#list>
</table>
</body>
</html>