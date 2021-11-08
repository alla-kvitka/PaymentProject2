
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


<form align="center" method = "post">
    <h4>login</h4>
    <label>
        <input type = "text" name="login"/>
    </label>
    <h4>password</h4>
    <label>
        <input type = "text" name="password"/>
    </label>
    </br>
    </br>
    <button type="submit">Confirm</button>
</form>


    <footer>
        <p class="copy" align="left">&copy; Alla Kvitka 2021</p>
    </footer>
</div>
</body>
</html>
