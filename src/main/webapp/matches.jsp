<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <script src="js/app.js"></script>
</head>

<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="/">Home</a>
                <a class="nav-link" href="/matches">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
            <form action="matches" method="GET">
                <input class="input-filter" name="filter_by_player_name" placeholder="Filter by name" type="text"
                       value="${param.filter_by_player_name}" />
                <button type="submit" class="btn-filter">Search</button>
                <a href="matches">
                    <button type="button" class="btn-filter">Reset Filter</button>
                </a>
            </form>
        </div>
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>  <!-- Покажем сообщение об ошибке -->
        </c:if>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>

            <c:forEach var="match" items="${sessionScope.listMatch}">
                <tr>
                    <td>${match.nameOnePlayer}</td>
                    <td>${match.nameTwoPlayer}</td>
                    <td><span class="winner-name-td">${match.nameWinnerPlayer}</span></td>
                </tr>
            </c:forEach>


        </table>

        <div class="pagination">
            <c:if test="${page > 1}">
                <a class="prev" href="matches?page=${page - 1}&filter_by_player_name=${param.filter_by_player_name}"> < </a>
            </c:if>

            <c:forEach var="i" begin="1" end="${totalPage}">
                <a class="num-page ${i == page ? 'current' : ''}" href="matches?page=${i}&filter_by_player_name=${param.filter_by_player_name}">${i}</a>
            </c:forEach>

            <c:if test="${page < totalPage}">
                <a class="next" href="matches?page=${page + 1}&filter_by_player_name=${param.filter_by_player_name}"> > </a>
            </c:if>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>
