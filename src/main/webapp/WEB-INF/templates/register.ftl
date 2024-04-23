<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация пользователя</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="js/validate-register-form.js"></script>
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-6">
            <h2 class="mb-4">Регистрация пользователя</h2>
            <form action="/register/process" method="post" onsubmit="submit_form(event)">
                <div class="mb-3">
                    <label for="realName" class="form-label">Реальное имя</label>
                    <input type="text" class="form-control" id="realName" name="realName">
                </div>
                <div class="mb-3">
                    <label for="age" class="form-label">Возраст</label>
                    <input type="number" class="form-control" id="age" name="age">
                </div>
                <div class="mb-3">
                    <label for="country" class="form-label">Страна</label>
                    <input type="text" class="form-control" id="country" name="country">
                </div>
                <div class="mb-3">
                    <label for="city" class="form-label">Город</label>
                    <input type="text" class="form-control" id="city" name="city">
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
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label">Повторите пароль</label>
                    <input type="password" class="form-control" id="confirmPassword" required>
                </div>
                <div class="mb-3">
                    <label for="skills" class="form-label">Теги-навыки</label>
                    <select multiple class="form-select" id="skills" name="skills">
                        <option value="Java">Java</option>
                        <option value="Python">Python</option>
                        <option value="JavaScript">JavaScript</option>
                        <option value="HTML">HTML</option>
                        <option value="CSS">CSS</option>
                        <option value="SQL">SQL</option>
                        <!-- Добавьте другие теги-навыки по вашему выбору -->
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
