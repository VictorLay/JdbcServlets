<%--
  Created by IntelliJ IDEA.
  User: Vikto
  Date: 07.06.2022
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee menu</title>
</head>
<body>
<jsp:include page="../header.jsp"/>

<h1>This is the employee menu. In this menu you can create, update and delete any book.</h1>
<h2>Please choose action</h2>
<a href="/viclay.com?command=add_new_book_page">Add new book</a>
<a href="/viclay.com?command=update_book_page">Update book</a>
</body>
</html>
