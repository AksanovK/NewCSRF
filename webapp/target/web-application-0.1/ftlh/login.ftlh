<html lang="en">
<head>
    <title>SignIn</title>
</head>
<body>
<h1>Login</h1>
<div>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label>
            <input name="email" placeholder="Your email">
        </label>
        <label>
            <input type="password" name="password" placeholder="Your password">
        </label>
        <input type="hidden" value="${_csrf_token}" name="_csrf_token">
        <input type="submit" value="Send">
    </form>
</div>
</body>
</html>