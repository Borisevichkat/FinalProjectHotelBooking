<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Регистрация</title>
</head>
<body>
<div class="container">

    <div class="">
        <img class="avatar" src="">
        <h1>Регистрация</h1>
        <form action="RegisterServlet" method="post">
            <p>Логин</p>
            <input type="text" placeholder="Username" name="name" required>
            <p>E-mail</p>
            <input type="text" placeholder="Useremail" name="email" required>
            <p>Пароль</p>
            <input type="password" placeholder="Password" name="password" required>
            <input type="submit" value="Register">
        </form>
    </div>

</div>

</body>
</html>