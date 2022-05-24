<%--
  Created by IntelliJ IDEA.
  User: Vikto
  Date: 04.05.2022
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new Book</title>
    <meta charset="utf-8" />
</head>
<body>
<form class="auth__form" action="/viclay.com?command=add_new_book" method="post">
    <h1 class="auth__title">Создание новой книги</h1>
    <div class="auth__wrapper">
        <span class="auth__text">ISBN</span>
        <input
                class="auth__item"
                type="text"
                name="isbn"
                placeholder="Введите ISBN"
                required
        />
    </div>
    <div class="auth__wrapper">
        <span class="auth__text">Название книги</span>
        <input
                class="auth__item"
                type="text"
                name="title"
                placeholder="Введите название книги"
                required

        />
    </div>
    <div class="auth__wrapper">
        <span class="auth__text">Выберите жанр</span>
        <select name="genreName" required >
            <option value="1">Фэнтези</option>
            <option value="2">Художественная</option>
            <option value="3">Научная</option>
            <option value="4">Историческая</option>
            <option value="5">Сказки</option>
        </select>
    </div>
    <div class="auth__wrapper">
        <span class="auth__text">Выберите издательство</span>
        <select name="publishingName" required >
            <option value="1">Питер</option>
            <option value="2">Издательский дом "Звязда"</option>
            <option value="3">Просвещение</option>
            <option value="4">Инапресс</option>
            <option value="5">Аврора</option>
        </select>
    </div>
    <div class="auth__wrapper">
        <span class="auth__text">Год издательства</span>
        <input
                class="auth__item"
                type="text"
                name="year"
                placeholder="Введите год издательства"
        />
    </div>

    <div class="auth__wrapper">
        <span class="auth__text">Цена книги</span>
        <input
                class="auth__item"
                type="text"
                name="price"
                placeholder="Введите цену книги"
        />
    </div>
    <div class="auth__wrapper">
        <span class="auth__text" >Автор</span>
        <input
                class="auth__item"
                type="text"
                name="author_name"
                placeholder="Введите автора"
        />
    </div>
    <div class="auth__wrapper">
        <span class="auth__text" >Введите страну автора</span>
        <input
                class="auth__item"
                type="text"
                name="author_country"
                placeholder="Введите страну"
        />
    </div>
    <div class="auth__wrapper">
        <input class="auth__item btn" type="submit" value="создать книгу" />
    </div>

</form>
</body>
</html>
