<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Unblock user</title>
    <link rel="stylesheet" href="index.styl">
</head>
<body>
<div class="line"></div>
<div class="wrapper">
    <header role="banner">
        <nav role="navigation">
            <h1><a href="adminHomepage">Home</a></h1>
            <ul class="nav-ul">
                <dir><a href="block">Block Card</a></dir>
                <dir><a href="unblock">Unblock Card</a></dir>
                <dir><a href="userBlock">Block User</a></dir>
                <dir><a href="userUnblock">Unblock User</a></dir>
                <dir><a href="allCards">All users</a></dir>
                <dir><a href="logout">LogOut</a></dir>
            </ul>
        </nav>
    </header>

    <h1 align="center">ENTER USER TO BLOCK</h1>
    <form align="center" method=post>
        <label>
            <input type='text' name='userUnblock'/>
        </label>
        <input type='submit' name='submit'/>
    </form>

</div>
</body>
</html>