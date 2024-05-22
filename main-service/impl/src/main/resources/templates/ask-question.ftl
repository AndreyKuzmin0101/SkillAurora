<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Создать статью</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link href="/css/styles.css" rel="stylesheet">
    <script src="/js/tags.js"></script>
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <h2 class="mb-4">Задать вопрос</h2>
            <div class="mb-3">
                <label for="title" class="form-label">Суть вопроса</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">Подробное описание</label>
                <textarea class="form-control" id="content" name="content" rows="8" required></textarea>
            </div>
            <div class="mb-3">
                <h5 class="form-label">Теги</h5>
                <input class="form-control me-2" type="search" placeholder="Поиск тегов" id="tag-search"
                       style="width: 200px; margin-bottom: 5px;">
                <div id="selected-tags" style="margin-top: 10px; margin-bottom: 10px;"></div>
                <div class="mb-3">
                    <select multiple class="form-select" id="tag-list">
                    </select>
                </div>
            </div>
            <button id="submit-button" class="btn btn-primary">Опубликовать</button>
        </div>
    </div>
</div>
<script src="/js/ckeditor/build/ckeditor.js"></script>
<script type="module" src="/js/question-create.js"></script>
</body>
</html>
