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
            <ul class="nav-ul">
                <dir></dir>
                <dir><a href="index">Payments </a></dir>
                <dir><a href="aboutUs">About Us</a></dir>
                <dir><a href="registration">Registration</a></dir>
                <dir><a href="login">LogIn</a></dir>
            </ul>
        </nav>
    </header>


<h4 align="center">login*</h4>
<form align="center" method = post>
    <label>
        <input type = 'text' name='login'/>
    </label>
    <h4>email*</h4>
    <label>
        <input type = 'text' name='email'/>
    </label>
    <h4>password*</h4>
    <label>
        <input type = 'text' name='password'/>
    </label>
    <h4>repeat password*</h4>
    <label>
        <input type = 'text' name='password-repeat'/>
    </label>

    </br>
    </br>
    <input type = 'submit' name='submit'/>
</form>


    <footer>
        <p class="copy" align="left">&copy; Alla Kvitka 2021</p>
    </footer>
    <div class="line"></div>
</div>
</body>
</html>
