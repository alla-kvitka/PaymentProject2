<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${requestScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${requestScope.lang}">
<head>
    <title>All users</title>
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
                <dir><a href="allCards">All cards</a></dir>
                <dir><a href="allUsers">All users</a></dir>
                <dir><a href="logout">LogOut</a></dir>
            </ul>
        </nav>
    </header>
    <h2 align="center">All Cards</h2>
    <table width="100%">
        <tr>
            <td>User ID</td>
            <td>User Status</td>
            <td>Block user</td>
            <td>Unblock user</td>
        </tr>
        <p></p>
        <c:forEach items="${requestScope.allCards}" var="card">
            <tr>
                <td>
                    <c:out value="${card.userId}"/>
                </td>
                <td>
                    <c:out value="${card.userStatus}"/>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/allUsers" method="post">
                        <input type="hidden" name="hidden" value="${card.userId}">
                        <input onclick="setTimeout(function () { window.location.reload(); }, 3)" type="submit"
                               name="button3" value="Block user"/>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/allUsers" method="post">
                        <input type="hidden" name="hidden" value="${card.userId}">
                        <input onclick="setTimeout(function () { window.location.reload(); }, 3)" type="submit"
                               name="button4" value="Unblock user">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
