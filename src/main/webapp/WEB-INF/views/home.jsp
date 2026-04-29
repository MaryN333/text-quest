<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<h2>Quest Game</h2>

<c:if test="${empty playerName}">
    <form action="reset" method="post">
        Name: <input type="text" name="playerName" required/>
        <button type="submit">Save</button>
    </form>
</c:if>

<c:if test="${not empty playerName}">
    <p>Playing as: <strong>${playerName}</strong></p>

    <form action="reset" method="post" style="display:inline">
        <button type="submit">Change Player</button>
    </form>

    <hr/>

    <h3>Choose your quest:</h3>

    <form action="start" method="get" style="display:inline">
            <input type="hidden" name="questId" value="quest1"/>
            <button type="submit">Quest 1</button>
    </form>

    <form action="start" method="get" style="display:inline">
        <input type="hidden" name="questId" value="quest2"/>
        <button type="submit">Quest 2</button>
    </form>

    <hr/>

    <c:if test="${gamesPlayed != null && gamesPlayed > 0}">
        <h3>Statistics:</h3>
        <p>Games played: ${gamesPlayed}</p>
        <p>Wins: ${gamesWon != null ? gamesWon : 0}</p>
        <p>Losses: ${gamesLost != null ? gamesLost : 0}</p>
    </c:if>
</c:if>

</body>
</html>