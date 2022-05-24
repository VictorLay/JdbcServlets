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
    <link href='https://fonts.googleapis.com/css?family=Sofia' rel='stylesheet'>

    <style>
      body {
        margin: 0;
        font-family: Arial, Helvetica, sans-serif;
      }

      .topnav {
        overflow: hidden;
        background-color: #333;
      }

      .topnav a {
        float: left;
        color: #f2f2f2;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-size: 17px;
      }

      .topnav a:hover {
        background-color: #ddd;
        color: black;
      }

      .topnav a.active {
        background-color: #4CAF50;
        color: white;
      }

      .log {
        float: right;
      }
    </style>
    <style>
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

    </style>

    <style>
      .container1 {
        position: relative;
        max-width: 1920px;
        margin: 0 auto;
      }

      .container1 img {
        vertical-align: middle;
      }

      .container1 .content {
        font-family: 'Sofia';
        font-size: 22px;
        position: absolute;
        bottom: 0;
        background: rgba(0, 0, 0, 0.5); /* Black background with transparency */
        color: #f1f1f1;
        width: 100%;
        padding: 20px;
      }
    </style>


</head>
<body>
<div class="container1">
    <img src="https://kanzlei-pozniak.de/wp-content/uploads/2018/01/o-kance.jpg" alt="Notebook" style="width:100%;">
    <!-- <img src="https://www.ristorantealcavin.it/wp-content/uploads/policy.jpg" alt="Notebook" style="width:100%;"-->
    <div class="content">
        <h1>Book information</h1>

    </div>
</div>


<div class="topnav">
    <a href="/viclay.com">Home</a>
    <a class="active" href="/viclay.com?command=book_showing">Books</a>
    <a href="#contact">Contact</a>
    <a href="#about">About</a>
    <div class="log">
        <c:choose>
            <c:when test="${login!=null}">
                <a href="/viclay.com?command=login">${login}</a>
                <a href="/viclay.com?command=sign_out">Sign out</a>
            </c:when>
            <c:otherwise>
                <a href="/viclay.com?command=login_page">Login</a>
                <a href="/viclay.com?command=registration_page">Registration</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="new">
    <div class="new1">
        <img style="max-width: 90%; margin: 5%"
             src="https://avatars.mds.yandex.net/get-zen_doc/118017/pub_5c0b67639c61f000aaf69ca3_5c0b6b666f88d200aae63353/scale_1200">

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
