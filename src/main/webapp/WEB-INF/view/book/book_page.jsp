<%--
  Created by IntelliJ IDEA.
  User: Vikto
  Date: 11.05.2022
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style>
      body {
        margin: 0;
        font-family: Arial, Helvetica, sans-serif;
      }

      .new {
        background-color: yellow;
        min-height: 1020px;
        margin: 3% 15% 5%;
      }

      .new1 {
        background-color: black;
        max-height: 400px;
        min-height: 400px;
        max-width: 300px;
        min-width: 300px;
      }

      .new2 {
        background-color: peru;
        max-height: 400px;
        min-height: 400px;
        max-width: 300px;
      }

      .new1 img {
        max-width: 90%;
        margin: 5%
      }
    </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="new">
    <div class="new1">
        <img src="https://avatars.mds.yandex.net/get-zen_doc/118017/pub_5c0b67639c61f000aaf69ca3_5c0b6b666f88d200aae63353/scale_1200">
    </div>

    <div class="new2">
        <div>ISBN: ${book.id}</div>
        <div>title: ${book.title}</div>
        <div>genre: ${book.genre}</div>
        <div>publishing: ${book.publishing}</div>
        <div>year: ${book.year}</div>
        <div>price: ${book.price}</div>
        <div>
            Authors:
            <c:forEach var="author" items="${book.authors}">
                ${author.name}
            </c:forEach>
        </div>

    </div>
</div>
</body>
</html>
