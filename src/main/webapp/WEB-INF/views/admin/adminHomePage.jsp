<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${requestScope.lang}"/>
<fmt:setBundle basename="message"/>

<html lang="${requestScope.lang}">
<head>
    <title>Admin Home Page</title>
    <link rel="stylesheet" href="index.styl">
</head>
<body>
<div class="line"></div>
<div class="wrapper">
    <header role="banner">
        <nav role="navigation">
            <h1><a href="adminHomepage">Home</a></h1>
            <ul class="nav-ul">
                <dir></dir>
                <dir></dir>
                <dir><a href="allCards"><fmt:message key="header.AllCards"/></a></dir>
                <dir><a href="allUsers"><fmt:message key="header.AllUsers"/></a></dir>
                <dir><a href="logout"><fmt:message key="header.LogOut"/></a></dir>
            </ul>
        </nav>
        <c:choose>
            <c:when test="${requestScope.lang == 'en'}">
                <a href="javascript:settingsLang('uk')"
                   class="nav-link text-secondary"><span
                        class="text-center text-muted">UK</span></a>
            </c:when>
            <c:otherwise>
                <a href="javascript:settingsLang('en')"
                   class="nav-link text-secondary"><span
                        class="text-center text-muted">EN</span></a>
            </c:otherwise>
        </c:choose>
    </header>
    <div>
        <h2>
            <p><img src="avatar.jpg" width="250" height="250" align="middle">
            <li><%= request.getAttribute("login")%>
            </li>
            <li><%= request.getAttribute("email")%>
            </li>
            </p>
        </h2>
    </div>
</div>
<script>
    function settingsLang(lang) {
        document.cookie = "lang=" + lang + "; path=/; max-age=" + (365 * 24 * 60 * 60);
        location.reload();
    }
</script>
</body>
</html>
