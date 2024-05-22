<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><@title/></title>
    <link rel="stylesheet" href="/css/base.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <@headtags/>
</head>
<body>
<header>
    <div style="display: inline-block">
        <div class="logo">
            <img src="/images/logo.png" alt="Логотип">
        </div>
        <h1 class="title">SkillShare</h1>
    </div>
    <div style="display: inline-block" id="header-buttons"></div>
</header>
<script type="module" src="/js/header.js"></script>
<nav>
    <ul>
        <li><a href="">Поток</a></li>
        <li><a href="/questions">Вопросы</a></li>
        <li><a href="#">Вакансии</a></li>
        <li><a href="#">Резюме</a></li>
        <li><a href="#">Модерация</a></li>
    </ul>
</nav>

<@content/>

<footer>
    <div style="text-align: left">
        <div style="display:inline-block; width: 63%; text-align: right">
            2024 Экосистема для обмена навыками и знаниями
        </div>
    </div>
</footer>
</body>
</html>
