<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Home Page</title>
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
<div>
    <h2>
        <p><img src="avatar.jpg" width="250" height="250" align="middle">
        <li>login: <%= request.getAttribute("login")%>
        </li>
        <li> email: <%= request.getAttribute("email")%>
        </li>
        </p>
    </h2>
</div>
</div>
</body>
</html>
