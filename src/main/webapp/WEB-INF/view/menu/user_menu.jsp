<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <title>Account information</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
      img.sticky {
        position: -webkit-sticky;
        position: sticky;
        top: 0;
        width: 200px;
      }
    </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<img class="sticky" src="https://html5css.ru/edithtm/img_avatar.png" alt="Avatar">
<h2>Information about user:</h2>
<c:choose>
    <c:when test="${user.login!=null}">
        <p>NickName of user: ${user.login}</p>
        <p>User role: ${user.role}<p/>
        <p>User id: ${user.id}</p>

        <a href="/viclay.com">Перейти на главную</a>
    </c:when>
    <c:otherwise>
        <a href="/viclay.com">Перейти на главную</a>
    </c:otherwise>
</c:choose>
</body>
</html>