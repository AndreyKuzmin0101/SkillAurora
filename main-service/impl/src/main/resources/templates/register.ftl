<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация пользователя</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link href="/css/styles.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="/js/validate.js"></script>
    <script src="/js/register.js"></script>
    <script src="/js/tags.js"></script>
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-6">
            <h2 class="mb-4">Регистрация пользователя</h2>
            <div style="margin-bottom: 20px">
                <div class="mb-3">
                    <label for="realName" class="form-label">Реальное имя</label>
                    <input type="text" class="form-control" id="realName" name="realName">
                </div>
                <div class="mb-3">
                    <label for="age" class="form-label">Возраст</label>
                    <input type="number" class="form-control" id="age" name="age">
                </div>
                <div class="mb-3">
                    <label for="username" class="form-label">Имя пользователя</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Пароль</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div></div>
                <h6>Имеет длину не менее 8 символов.</h6>
                <h6>По крайней мере, одна заглавная английская буква.</h6>
                <h6>Хотя бы одна строчная буква английского алфавита.</h6>
                <h6>Должен содержать хотя бы одну цифру.</h6>
                <h6>По крайней мере, один специальный символ. (#?!@$%^&*-)</h6>
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label">Повторите пароль</label>
                    <input type="password" class="form-control" id="confirmPassword" required>
                </div>
                <h5 class="form-label">Навыки</h5>
                <input class="form-control me-2" type="search" placeholder="Поиск тегов" id="tag-search" style="width: 200px; margin-bottom: 5px;">
                <div id="selected-tags" style="margin-top: 10px; margin-bottom: 10px;"></div>
                <div class="mb-3">
                    <select multiple class="form-select" id="tag-list">
                    </select>
                </div>
                <button id="register-btn" class="btn btn-primary">Зарегистрироваться</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>