<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${requestScope.lang}"/>
<fmt:setBundle basename="message"/>
<html lang="${requestScope.lang}">
<head>
    <title>Submit payment</title>
    <link rel="stylesheet" href="index.styl">

</head>
<body>
<div class="line"></div>
<div class="wrapper">
    <header role="banner">
        <nav role="navigation">
            <h1><a href="homepage">Home</a></h1>
            <ul class="nav-ul">
                <dir></dir>
                <dir><a href="user-cards"><fmt:message key="header.MyCards"/></a></dir>
                <dir><a href="transactions"><fmt:message key="header.TransactionsHistory"/></a></dir>
                <dir><a href="payment"><fmt:message key="header.CreatePayment"/></a></dir>
                <dir><a href="paymentSubmit"><fmt:message key="header.SubmitPayment"/></a></dir>
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
    <h2 align="center"><fmt:message key="message.SUBMITYOURPAYMENTS"/></h2>
</div>
<table  width="80%" align="center">
    <thead>
    <tr>
        <th><fmt:message key="table.CardID"/></th>
        <th><fmt:message key="table.PaymentSum"/></th>
        <th><fmt:message key="table.Paymenttype"/></th>
    </tr>
    <thead>
    <tbody>

    <c:forEach items="${requestScope.payments}" var="payment">
        <tr>
            <td>
                <c:out value="${payment.cardId}"/>
            </td>
            <td>
                <c:out value="${payment.paymentSum}"/>
            </td>
            <td>
                <c:out value="${payment.transactionType}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form align="center" method=post>
    <h1><input type='submit' name='submit'/></h1>
</form>

<script>
    function settingsLang(lang) {
        document.cookie = "lang=" + lang + "; path=/; max-age=" + (365 * 24 * 60 * 60);
        location.reload();
    }
</script>
</body>
</html>
