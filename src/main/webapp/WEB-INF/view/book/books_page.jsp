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

      .bookContainerMain {
        margin-left: auto;
        margin-right: auto;
        width: 75%;
        display: table;
      }

      .containerBook {
        margin: 50px;
        padding: 20px;
        float: left;
        text-align: center;
        position: relative;
        height: 200px;
        width: 200px;
        /*display:table-cell;*/
      }

      .containerBook a {
        text-decoration: none;
        color: #dddddd;
      }

      .containerBook:hover .image {
        opacity: 0.3;
      }

      .containerBook:hover .middle {
        opacity: 1;
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
      .headerNavigation a.activeBooks {
        background-color: #4CAF50;
        color: white;
      }
    </style>
</head>
<body>
<div>
    <jsp:include page="../header.jsp"/>
</div>
<div style="padding-left:16px">
    <h2>Books storage</h2>
    <p>Choose book for yourself or your friends ..</p>
</div>
<div>
    <div class="bookContainerMain">
        <c:forEach var="book" items="${books}">
            <div class="containerBook">
                <img src="https://avatars.mds.yandex.net/i?id=e8464ed6cb3e00549dfc2d34a8b02e10-5204968-images-thumbs&n=13"
                     alt="Avatar" class="image" style="width:100%">
                <div class="middle">
                    <div class="text">
                        <form style="margin: 0px" method="get" action="/viclay.com">
                            <input hidden name="book_id" value="${book.id}">
                            <input hidden name="command" value="show_book">
                            <button style="background: none; border: none" type="submit" ;>MORE
                            </button>
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
</div>

</body>
</html>
