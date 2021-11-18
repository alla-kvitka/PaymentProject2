<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
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
    <block1>
            <img src="avatar.jpg" width="250" height="250" align="middle">
    </block1>

    <block2>
        <h2 align="center"> Hello,here you can create new card</h2>
        <form align="center" method=post>
            <div class="row">
                <input placeholder='Login' name='login' type='text'/>
            </div>
            <div class="row">
                <input placeholder='Password'  name='password' type='password'/>
            </div>
            <div class="row">
                <input type='submit' name='submit'/>
            </div>
        </form>
    </block2>
</div>
</body>
</html>
