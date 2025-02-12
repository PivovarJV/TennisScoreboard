<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/app.js"></script>

    <style>
        /* Стили для модального окна */
        .modal {
            display: none; /* Скрываем по умолчанию */
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .modal-content {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
            width: 300px;
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }
    </style>
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
                <a class="nav-link" href="#">Matches</a>
            </nav>
        </div>
    </section>
</header>

<main>
    <div class="container">
        <h1>Current match</h1>
        <div class="current-match-image"></div>
        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                </tr>
                </thead>
                <tbody>
                <tr class="player1">
                    <td class="table-text">${sessionScope.playerOne}</td>
                    <td class="table-text">${sessionScope.setsOnePlayer}</td>
                    <td class="table-text">${sessionScope.gamesOnePlayer}</td>
                    <td class="table-text">${sessionScope.pointsOnePlayer}</td>
                    <td class="table-text">
                        <form method="post">
                            <button type="submit" class="score-btn">Score</button>
                            <input type="hidden" name="playerId" value="1">
                        </form>
                    </td>
                </tr>
                <tr class="player2">
                    <td class="table-text">${sessionScope.playerTwo}</td>
                    <td class="table-text">${sessionScope.setsTwoPlayer}</td>
                    <td class="table-text">${sessionScope.gamesTwoPlayer}</td>
                    <td class="table-text">${sessionScope.pointsTwoPlayer}</td>
                    <td class="table-text">
                        <form method="post">
                            <button type="submit" class="score-btn">Score</button>
                            <input type="hidden" name="playerId" value="2">
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
</main>

<%-- Модальное окно, которое появляется только если есть победитель --%>
<%
    String winner = (String) session.getAttribute("winner");
    if (winner != null) {
%>
<div class="modal" id="winnerModal">
    <div class="modal-content">
        <h2>Match Finished!</h2>
        <p>Winner: <span><%= winner %></span></p>
        <form action="matches" method="get">
            <button type="submit">View Completed Matches</button>
        </form>
    </div>
</div>

<script>
    // Показать модальное окно, если есть победитель
    document.getElementById("winnerModal").style.display = "flex";
</script>
<%
    }
%>

<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>
</body>
</html>
