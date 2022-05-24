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
    </style>
    <style>
      .container {
        margin: 50px;
        padding: 20px;
        float: left;
        text-align: center;
        position: relative;
        height: 200px;
        width: 200px;
        /*display:table-cell;*/
      }

      .image {
        opacity: 1;
        display: block;
        width: 100%;
        height: auto;
        transition: .5s ease;
        backface-visibility: hidden;
      }

      .middle {
        transition: .5s ease;
        opacity: 0;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        -ms-transform: translate(-50%, -50%);
        text-align: center;
      }

      .container:hover .image {
        opacity: 0.3;
      }

      .container:hover .middle {
        opacity: 1;
      }

      .text {
        text-align: center;
        vertical-align: center;
        background-color: #4CAF50;
        color: white;
        font-size: 16px;
        padding: 16px 32px;
      }
    </style>

    <style>
      .bookContainer {
        margin-left: auto;
        margin-right: auto;
        width: 75%;
        display: table;
      }

      .container a {
        text-decoration: none;
        color: #dddddd;
      }

      .log {
        float: right;
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
        <h1>Enjoy yourself with books</h1>

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
                <a href="/viclay.com?command=add_new_book_page">Employee room</a>
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

<div style="padding-left:16px">
    <h2>Books storage</h2>
    <p>Choose book for yourself or your friends ..</p>
</div>
<div class="bookContainer">
    <c:forEach var="book" items="${books}">
        <div class="container">
            <img src="https://avatars.mds.yandex.net/i?id=e8464ed6cb3e00549dfc2d34a8b02e10-5204968-images-thumbs&n=13"
                 alt="Avatar" class="image" style="width:100%">
            <div class="middle">
                <div class="text">
                    <form style="margin: 0px" method="get" action="/viclay.com">
                        <input hidden name="book_id" value="${book.id}">
                        <input hidden name="command" value="show_book">
                        <button style="background: none; border: none" type="submit" ;>MORE</button>
                    </form>

                </div>
            </div>
            <div>${book.title}</div>
            <div>
                <c:forEach var="author" items="${book.authors}">
                    ${author.name}
                </c:forEach>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
