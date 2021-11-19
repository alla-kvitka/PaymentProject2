<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${requestScope.lang}" />
<fmt:setBundle basename="message" />
<html lang="${requestScope.lang}">
<head>
    <title>Login</title>
    <link rel="stylesheet" href="index.styl">
</head>
<body>
<div class="line"></div>
<div class="wrapper">
    <header role="banner">
        <nav role="navigation">
            <h1><a href="index">Payments</a></h1>
            <ul class="nav-ul">
                <dir></dir>
                <dir><a href="registration"><fmt:message key="header.Registration"/></a></dir>
                <dir><a href="login"><fmt:message key="header.LogIn"/></a></dir>
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

    <h2 align="center"><fmt:message key="message.ENTERYOUR"/></h2>
    <h2 align="center"><fmt:message key="message.CREDENTIALS"/></h2>
    <form align="center" method=post>
        <div class="row">
            <input placeholder=<fmt:message key="form.Login"/> name='login' type='text'/>
        </div>
        <div class="row">
            <input placeholder=<fmt:message key="form.Password"/>  name='password' type='password'/>
        </div>
        <div class="row">
            <input type='submit' name=<fmt:message key="bottom.submit"/>/>
        </div>
    </form>
</div>
<script>
    function settingsLang(lang) {
        document.cookie = "lang=" + lang + "; path=/; max-age=" + (365 * 24 * 60 * 60);
        location.reload();
    }
</script>
</body>
</html>
