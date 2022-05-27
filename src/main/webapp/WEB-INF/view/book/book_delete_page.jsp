<%--
  Created by IntelliJ IDEA.
  User: Vikto
  Date: 27.05.2022
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete book menu</title>
</head>
<body>
<jsp:include page="../header.jsp"/>

<form method="post" action="/viclay.com?command=delete_book">
    <input name="id" placeholder="Введите id книги"/>
    <input type="submit" value="Удалить"/>
</form>
</body>
</html>
