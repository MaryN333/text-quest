<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Game Result</title>
</head>
<body>

<h2>Game finished!</h2>
<h2>${step.text}</h2>

<p>Player: ${playerName}</p>
<p>Total games: ${gamesPlayed}</p>
<p>Wins: ${wins}</p>
<p>Losses: ${loses}</p>

<br/>

<a href="start?questId=${questId}">Play again</a>

<br/>

<a href="${pageContext.request.contextPath}/home">Back to menu</a>

</body>
</html>