<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Home Page</title>
    <link rel="stylesheet" href="index.styl">
</head>
<body>
<header role="banner">
    <nav role="navigation">
        <h1><a href="homepage">Home</a></h1>
        <ul class="nav-ul">
            <dir></dir>
            <dir><a href="#">Block cards</a></dir>
            <dir><a href="#">Unblock cards</a></dir>
            <dir><a href="logout">LogOut</a></dir>
        </ul>
    </nav>
</header>

<h1>
    <p><img src="avatar.jpg" width="250" height="250" align="middle">
    <li>login: <%= request.getAttribute("login")%>
    </li>
    <li> email: <%= request.getAttribute("email")%>
    </li>
    </p>
</h1>

<footer>
    <p class="copy" align="left">&copy; Alla Kvitka 2021</p>
</footer>
</body>
</html>
