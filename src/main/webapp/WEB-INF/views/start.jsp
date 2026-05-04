<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="card">
    <c:choose>
        <c:when test="${not empty playerName}">
            <h2>Welcome back, ${playerName}! Start new game?</h2>
            <form method="post">
                <input type="hidden" name="questId" value="${questId}"/>
                <button type="submit">Start New Game</button>
            </form>
        </c:when>
        <c:otherwise>
            <h2>Welcome to the Quest</h2>
            <form method="post">
                Name: <input type="text" name="playerName"/>
                <input type="hidden" name="questId" value="${questId}"/>
                <button type="submit">Start</button>
            </form>
        </c:otherwise>
    </c:choose>

    <a href="${pageContext.request.contextPath}/home">Back to menu</a>
</div>
</body>
</html>