<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="card">
    <h3>${step.text}</h3>

    <c:forEach var="opt" items="${step.options}" varStatus="i">
        <form method="post">
            <input type="hidden" name="choice" value="${i.index}"/>
            <button type="submit">${opt.text}</button>
        </form>
    </c:forEach>

    <hr>

    <p>Player: ${playerName}</p>

    <h4>Statistics:</h4>
    <p>Games played: ${gamesPlayed}</p>
    <p>Wins: ${wins}</p>
    <p>Losses: ${loses}</p>

    <h4>History:</h4>
    <ol>
        <c:forEach var="h" items="${history}">
            <li>${h}</li>
        </c:forEach>
    </ol>

    <hr>

    <c:if test="${empty step.options}">
        <a href="${pageContext.request.contextPath}/start?questId=${questId}">Restart</a><br/>
    </c:if>

    <a href="${pageContext.request.contextPath}/home">Exit to menu</a>
</div>
</body>
</html>