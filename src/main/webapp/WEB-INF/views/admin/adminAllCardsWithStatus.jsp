<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${requestScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${requestScope.lang}">
<head>
    <title>All cards</title>
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
                <dir><a href="block">Block Card</a></dir>
                <dir><a href="unblock">Unblock Card</a></dir>
                <dir><a href="userBlock">Block User</a></dir>
                <dir><a href="userUnblock">Unblock User</a></dir>
                <dir><a href="allCards">All users</a></dir>
                <dir><a href="logout">LogOut</a></dir>
            </ul>
        </nav>
    </header>
    <h2 align="center">All Cards</h2>
    <table width="100%">
        <tr>
            <td>Card ID</td>
            <td>Sum</td>
            <td>Card Status</td>
            <td>User ID</td>
            <td>User Status</td>
        </tr>
        <p></p>
        <c:forEach items="${requestScope.allCards}" var="card">
            <tr>
                <td>
                    <c:out value="${card.cardId}"/>
                </td>
                <td>
                    <c:out value="${card.cardSum}"/>
                </td>
                <td>
                    <c:out value="${card.isCardStatus()}"/>
                </td>
                <td>
                    <c:out value="${card.userId}"/>
                </td>
                <td>
                    <c:out value="${card.userStatus}"/>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
