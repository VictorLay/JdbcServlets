<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../../style/book_creator_page_style.css"/>
</head>
<body>
<jsp:include page="../header.jsp"/>
<h3>Для обновления книги введите её ISBN номер и заполните обновлённые данные о книге</h3>

<form class="auth__form" action="/viclay.com?command=update_book" method="post">
    <h1>Обновление книги</h1>
    <div>
        <span> ISBN </span>
        <input
                type="text"
                name="book_id"
                placeholder="Введите ISBN"
                required
        />
    </div>
    <div>
        <span>Название книги</span>
        <input
                type="text"
                name="title"
                placeholder="Введите название книги"
                required
        />
    </div>
    <div>
        <span>Выберите жанр</span>
        <select name="genreName" required>
            <option value="1">Фэнтези</option>
            <option value="2">Художественная</option>
            <option value="3">Научная</option>
            <option value="4">Историческая</option>
            <option value="5">Сказки</option>
        </select>
    </div>
    <div>
        <span>Выберите издательство</span>
        <select name="publishingName" required>
            <option value="1">Питер</option>
            <option value="2">Издательский дом "Звязда"</option>
            <option value="3">Просвещение</option>
            <option value="4">Инапресс</option>
            <option value="5">Аврора</option>
        </select>
    </div>
    <div>
        <span>Год издательства</span>
        <input
                type="text"
                name="year"
                placeholder="Введите год издательства"
        />
    </div>
    <div>
        <span>Цена книги</span>
        <input
                type="text"
                name="price"
                placeholder="Введите цену книги"
        />
    </div>
    <div>
        <span>Автор</span>
        <input
                type="text"
                name="author_name"
                placeholder="Введите автора"
        />
    </div>
    <div>
        <span>Введите страну автора</span>
        <input
                type="text"
                name="author_country"
                placeholder="Введите страну"
        />
    </div>
    <div>
        <input
                type="submit"
                value="Обновить информацию о книге"
        />
    </div>
</form>

</body>
</html>
