<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<h2>Quest Game</h2>

<p>Player: ${playerName}</p>

<form action="start" method="get">
    <button type="submit">Start Quest</button>
</form>

<form action="reset" method="post">
    <button type="submit">New Player</button>
</form>

</body>
</html>