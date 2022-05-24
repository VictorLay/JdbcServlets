<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Vikto
  Date: 27.04.2022
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:choose>--%>
<%--    <c:when test="${sessionScope.language != null}">--%>
<%--        <fmt:setLocale value="${sessionScope.language}" variant="en"/>--%>
<%--    </c:when>--%>
<%--    <c:otherwise>--%>
<%--        <fmt:setLocale value="en"/>--%>
<%--    </c:otherwise>--%>
<%--</c:choose>--%>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="lang" var="lang"/>
<fmt:message bundle="${lang}" key="header.title" var="test_text"/>

<html>
<head>
    <title>Letter's home</title>

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
      * {
        box-sizing: border-box;
      }

      body {
        margin: 0;
        font-family: Arial;
        font-size: 17px;
      }

      .container {
        position: relative;
        max-width: 1920px;
        margin: 0 auto;
      }

      .container img {
        vertical-align: middle;
      }

      .container .content {
        font-family: 'Sofia';
        font-size: 22px;
        position: absolute;
        bottom: 0;
        background: rgba(0, 0, 0, 0.5); /* Black background with transparency */
        color: #f1f1f1;
        width: 100%;
        padding: 20px;
      }

      /* Column container */
      .row {
        display: -ms-flexbox; /* IE10 */
        display: flex;
        -ms-flex-wrap: wrap; /* IE10 */
        flex-wrap: wrap;
      }

      /* Create two unequal columns that sits next to each other */
      /* Sidebar/left column */
      .side {
        -ms-flex: 30%; /* IE10 */
        flex: 30%;
        background-color: #f1f1f1;
        padding: 20px;
      }

      /* Main column */
      .main {
        -ms-flex: 70%; /* IE10 */
        flex: 70%;
        background-color: white;
        padding: 20px;
      }

      /* Fake image, just for this example */
      .fakeimg {
        background-color: #aaa;
        width: 100%;
        padding: 20px;
      }

      /* Footer */
      .footer {
        padding: 20px;
        text-align: center;
        background: #ddd;
      }

      .log {
        float: right;
      }
    </style>

</head>
<body>

<div class="container">
    <img src="https://kanzlei-pozniak.de/wp-content/uploads/2018/01/o-kance.jpg" alt="Notebook" style="width:100%;">
    <!-- <img src="https://www.ristorantealcavin.it/wp-content/uploads/policy.jpg" alt="Notebook" style="width:100%;"-->
    <div class="content">
        <h1>The book house ${test_text}</h1>
        <p>A room without books is like a body without a soul.</p>
    </div>
</div>

<div class="topnav">
    <a class="active" href="#home">Home</a>
    <a href="/viclay.com?command=book_showing">Books</a>
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
<%--        <a href="/viclay.com?command=login_page">Login</a>--%>
<%--        <a href="/viclay.com?command=registration_page">Registration</a>--%>
    </div>


</div>







<%--some content--%>
<div>
    <div class="row">
        <div class="side">
            <h2>About Me</h2>
            <h5>Photo of me:</h5>
            <div class="fakeimg" style="height:200px;">Image</div>
            <p>Some text about me in culpa qui officia deserunt mollit anim..</p>
            <h3>More Text</h3>
            <p>Lorem ipsum dolor sit ame.</p>
            <div class="fakeimg" style="height:60px;">Image</div>
            <br>
            <div class="fakeimg" style="height:60px;">Image</div>
            <br>
            <div class="fakeimg" style="height:60px;">Image</div>
        </div>
        <div class="main">
            <h2>TITLE HEADING</h2>
            <h5>Title description, Dec 7, 2017</h5>
            <div class="fakeimg" style="height:200px;">Image</div>
            <p>Some text..</p>
            <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod
                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
                ullamco.</p>
            <br>
            <h2>TITLE HEADING</h2>
            <h5>Title description, Sep 2, 2017</h5>
            <div class="fakeimg" style="height:200px;">Image</div>
            <p>Some text..</p>
            <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod
                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
                ullamco.</p>
        </div>
    </div>

    <div class="footer">
        <h2>Footer</h2>
    </div>

</div>
</body>
</html>