<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
                <dir><a href="registration">Registration</a></dir>
                <dir><a href="login">LogIn</a></dir>
            </ul>
        </nav>
    </header>

    <h2 align="center">ENTER YOUR</h2>
    <h2 align="center">CREDENTIALS</h2>
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

</div>
</body>
</html>
