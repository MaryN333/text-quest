<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Game Result</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="card">
    <h2>Game finished!</h2>
    <h2>${step.text}</h2>

    <p>Player: ${playerName}</p>
    <p>Total games: ${gamesPlayed}</p>
    <p class="win">Wins: ${wins}</p>
    <p class="lose">Losses: ${loses}</p>

    <br/>

    <a href="${pageContext.request.contextPath}/start?questId=${questId}">Play again</a>

    <br/>

    <a href="${pageContext.request.contextPath}/home">Back to menu</a>
</div>
</body>
</html>