<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Game Result</title>
</head>
<body>

<h2>Game finished!</h2>

<p>Player: ${playerName}</p>
<p>Total games: ${gamesPlayed}</p>
<p>Wins: ${wins}</p>
<p>Losses: ${loses}</p>

<br/>

<a href="start?questId=${questId}">Play again</a>

<br/>

<a href="home">Back to menu</a>

</body>
</html>