<%--
  Created by IntelliJ IDEA.
  User: Vikto
  Date: 26.04.2022
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
            rel="stylesheet"
            href="../style/output.css"
    />
    <link rel="stylesheet" href="../style/output.css" />
    <link rel="stylesheet" href="../style/nullStyle.css" />
    <title>Вход в систему</title>
</head>
<body>
<div class="auth">
    <div class="container">
        <form class="auth__form" action="/viclay.com?command=login" method="post">
            <h1 class="auth__title">Авторизация</h1>
            <div class="auth__wrapper">
                <span class="auth__text">Логин</span>
                <input
                        class="auth__item"
                        type="text"
                        name="login"
                        placeholder="Введите логин"
                />
            </div>
            <div class="auth__wrapper">
                <span class="auth__text" >Пароль</span>
                <input
                        type="password"
                        class="auth__item"
                        type="text"
                        name="password"
                        placeholder="Введите пароль"
                />
            </div>
            <div class="auth__wrapper">
                <input class="auth__item btn" type="submit" value="Войти" />
            </div>
            <div class="auth">
                <a
                        href="/viclay.com?command=registration_page"
                        class="auth__link"
                >У вас нету аккаунта? Регистрация</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>


</body>
</html>
