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



    <style>
      * {
        box-sizing: border-box;
      }

      body {
        margin: 0;
        font-family: Arial;
        font-size: 17px;
      }



      .container img {
        vertical-align: middle;
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

      body {
        margin: 0;
        font-family: Arial, Helvetica, sans-serif;
      }
    </style>

    <style>
      .headerNavigation a.activeHome {
        background-color: #4CAF50;
        color: white;
      }
    </style>

</head>
<body>

<jsp:include page="../header.jsp"/>






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