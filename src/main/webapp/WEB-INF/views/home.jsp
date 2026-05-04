<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="card">
    <h2>Quest Game</h2>

    <c:if test="${empty playerName}">
        <form action="${pageContext.request.contextPath}/reset" method="post">
            Name: <input type="text" name="playerName" required/>
            <button type="submit">Save</button>
        </form>
    </c:if>

    <c:if test="${not empty playerName}">
        <p>Playing as: <strong>${playerName}</strong></p>

        <form action="${pageContext.request.contextPath}/reset" method="post" style="display:inline">
            <button type="submit">Change Player</button>
        </form>

        <hr/>

        <h3>Choose your quest:</h3>

        <form action="${pageContext.request.contextPath}/start" method="get" style="display:inline">
            <input type="hidden" name="questId" value="prague-night"/>
            <button type="submit">Play Prague Night</button>
        </form>

        <form action="${pageContext.request.contextPath}/start" method="get" style="display:inline">
            <input type="hidden" name="questId" value="negotiation"/>
            <button type="submit">Play Negotiation Quest</button>
        </form>

        <form action="${pageContext.request.contextPath}/start" method="get" style="display:inline">
            <input type="hidden" name="questId" value="forest"/>
            <button type="submit">Play Forest Quest</button>
        </form>

        <hr/>
        <c:if test="${gamesPlayed != null && gamesPlayed > 0}">
            <div class="stats">
                <h3>Statistics:</h3>
                <p>Games played: ${gamesPlayed}</p>
                <p>Wins: ${gamesWon != null ? gamesWon : 0}</p>
                <p>Losses: ${gamesLost != null ? gamesLost : 0}</p>
            </div>
        </c:if>
    </c:if>
</div>
</body>
</html>