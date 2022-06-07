<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href='https://fonts.googleapis.com/css?family=Sofia' rel='stylesheet'>
<link rel="stylesheet" href="../../style/header_style.css">

<div class="headerImageContainer">
    <img src="../../img/header.webp" alt="pan">
    <div class="headerImageContent">
        <h1>The book house ${test_text}</h1>
        <p>A room without books is like a body without a soul.</p>
    </div>
</div>

<div class="headerNavigation">
    <a class="activeHome" href="/viclay.com">Home</a>
    <a class="activeBooks" href="/viclay.com?command=book_showing">Books</a>

    <div class="headerLog">
        <c:choose>
            <c:when test="${login!=null}">
                <a href="/viclay.com?command=employee_menu_page">Employee room</a>
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