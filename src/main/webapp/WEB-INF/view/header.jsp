<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${language==null}">
    <c:set scope="session" var="language" value="en"/>

</c:if>

<c:choose>
    <c:when test="${language != null}">
        <fmt:setLocale value="${language}" variant="en"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="lang" var="lang"/>
<fmt:message bundle="${lang}" key="header.store_name" var="store_name_text"/>
<fmt:message bundle="${lang}" key="header.lyric" var="lyric_text"/>
<fmt:message bundle="${lang}" key="header.home" var="home_text"/>
<fmt:message bundle="${lang}" key="header.books" var="books_text"/>
<fmt:message bundle="${lang}" key="header.login" var="login_text"/>
<fmt:message bundle="${lang}" key="header.sign_out" var="sign_out_text"/>
<fmt:message bundle="${lang}" key="header.registration" var="registration_text"/>

<link href='https://fonts.googleapis.com/css?family=Sofia' rel='stylesheet'>
<link rel="stylesheet" href="../../style/header_style.css">
<a href="${pageContext.request.contextPath}?language=ru&command=change_lang_command">ru</a>
<a href="${pageContext.request.contextPath}?language=en&command=change_lang_command">en</a>

<div class="headerImageContainer">
    <img src="../../img/header.webp" alt="pan">
    <div class="headerImageContent">
        <h1>${store_name_text}</h1>
        <p>${lyric_text}</p>
    </div>
</div>

<div class="headerNavigation">
    <a class="activeHome" href="/viclay.com">${home_text}</a>
    <a class="activeBooks" href="/viclay.com?command=book_showing">${books_text}</a>

    <div class="headerLog">
        <c:set var="adm_role" value="admin_user" scope="session"/>
        <c:set var="empl_role" value="employee_user" scope="session"/>
        <c:set var="cstm_role" value="signed_user" scope="session"/>
        <c:choose>
            <c:when test='${role==empl_role}'>
                <a href="/viclay.com?command=employee_menu_page">Employee room</a>
                <a href="/viclay.com?command=login">${login}</a>
                <a href="/viclay.com?command=sign_out">${sign_out_text}</a>
            </c:when>
            <c:when test='${role==adm_role}'>
                <a href="/viclay.com?command=admin_menu_page">Admin room</a>
                <a href="/viclay.com?command=login">${login}</a>
                <a href="/viclay.com?command=sign_out">${sign_out_text}</a>
            </c:when>
            <c:when test='${role==cstm_role}'>
                <a href="/viclay.com?command=login">${login}</a>
                <a href="/viclay.com?command=sign_out">${sign_out_text}</a>
            </c:when>
            <c:otherwise>
                <a href="/viclay.com?command=login_page">${login_text}</a>
                <a href="/viclay.com?command=registration_page">${registration_text}</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>