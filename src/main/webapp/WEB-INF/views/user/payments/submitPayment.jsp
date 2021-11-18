<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
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
                <dir><a href="user-cards">My cards</a></dir>
                <dir><a href="transactions">Transactions history</a></dir>
                <dir><a href="payment">Create Payment</a></dir>
                <dir><a href="paymentSubmit">Submit Payment</a></dir>
                <dir><a href="logout">LogOut</a></dir>
            </ul>
        </nav>
    </header>
    <h2 align="center">SUBMIT YOUR PAYMENTS</h2>
</div>
<table  width="80%" align="center">
    <thead>
    <tr>
        <th>Card ID</th>
        <th>Payment Sum</th>
        <th>Payment type</th>
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

</body>
</html>
