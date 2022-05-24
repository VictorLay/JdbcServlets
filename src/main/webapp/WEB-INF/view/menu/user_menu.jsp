<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<%String login = (String) session.getAttribute("login");%>--%>
<div>Привет
    <c:choose>
        <c:when test="${login!=null}">
            <ul>
                <li>
                        ${login}
                </li>

                <li>
                        ${role}
                </li>

                <li>
                        ${user.id}
                </li>
                <li>
                    <a href="/viclay.com?command=book_showing">Посмотреть все книги</a>
                </li>
            </ul>



        </c:when>
        <c:otherwise>
            кто ты ?
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
