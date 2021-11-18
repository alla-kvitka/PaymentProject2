<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
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

    <form align="center" method=post>
        <div class="row">
            <input id="login" placeholder="Login" type='text' name='login'/>
        </div>

        <div class="row">
            <input placeholder="Email" type='text' name='email'/>
        </div>
        <div class="row">
            <input placeholder="Password" type='text' name='password'/>
        </div>
        <div class="row">
            <input placeholder="Repeat pass" type='text' name='password-repeat'/>
        </div>
        <div class="row">
            <input type='submit' name='submit'/>
        </div>
    </form>

</div>
</body>
</html>
