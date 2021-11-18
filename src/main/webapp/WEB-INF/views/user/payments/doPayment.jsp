<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Do Payment</title>
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
    <h2 align="center">DO PAYMENT</h2>
    <form align="center" method=post>
        <div class="row">
            <input type='number' name='userCardId' placeholder="card number"/>
        </div>
        <div class="row">
            <input type='number' name='sum' placeholder="payment sum"/>
        </div>
        <div class="row">
            <input type='text' name='trType' placeholder="positive/negative"/>
        </div>
        <div class="row">
            <input type='submit' name='submit'/>
        </div>
    </form>


</div>
</body>
</html>
