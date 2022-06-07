<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="../../style/nullStyle.css" />
    <script type="text/javascript" src="../../validate_functions.js"></script>


    <link
            rel="stylesheet"
            href="../../style/output.css"
    />
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="auth">
    <div class="container">
        <form class="auth__form" action="/viclay.com?command=registration" method="post" onsubmit="return validate(this)">
            <h1 class="auth__title">Регистрация</h1>
            <div class="auth__wrapper">
                <span class="auth__text">Имя</span>
                <input
                        id="forename"
                        class="auth__item"
                        type="text"
                        name="name"
                        placeholder="Введите имя"
                />
            </div>
            <div class="auth__wrapper">
                <span class="auth__text">Фамилия</span>
                <input
                        id="surename"
                        class="auth__item"
                        type="text"
                        name="lastname"
                        placeholder="Введите фамилию"
                />
            </div>
            <div class="auth__wrapper" >
                <span class="auth__text">Логин</span>
                <input
                        id="username"
                        class="auth__item"
                        type="text"
                        name="login"
                        placeholder="Логин"
                />
            </div>
            <div class="auth__wrapper">
                <span class="auth__text">Пароль</span>
                <input
                        id="password"
                        class="auth__item"
                        type="text"
                        name="password"
                        placeholder="Пароль"
                />
            </div>
            <script type="text/javascript">
              valid_nm(document.getElementById("forename"));
              valid_nm(document.getElementById("surename"));
              ajax_valid(document.getElementById("username"))
              valid_pwd(document.getElementById("password"));

            </script>
            <div class="auth__wrapper">
                <%request.setAttribute("command", "registration");%>
                <input
                        class="auth__item btn"
                        type="submit"
                        value="Зарегестрироваться"
                />
            </div>
        </form>
    </div>
</div>
<div>
    <a href="/viclay.com?command=login_page">login</a>
</div>


</body>

</html>

