<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="module" src="/js/login.js"></script>
</head>
<body>

<div class="container" style="margin-top: 14%">
    <div class="row justify-content-center">
        <div class="col-lg-4">
            <h2 class="mb-4">Вход</h2>
            <div style="margin-bottom: 20px">
                <div class="mb-3">
                    <label for="username" class="form-label">Имя пользователя</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Пароль</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button id="submit-btn" type="submit" class="btn btn-primary">Войти</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>