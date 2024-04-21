<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Article</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <h2 class="mb-4">Create New Article</h2>
            <form action="/create/article" method="post">
                <div class="mb-3">
                    <label for="articleTitle" class="form-label">Title</label>
                    <input type="text" class="form-control" id="articleTitle" name="title" required>
                </div>
                <div class="mb-3">
                    <label for="articleContent" class="form-label">Content</label>
                    <textarea class="form-control" id="articleContent" name="content" rows="8"></textarea>
                </div>
                <div class="mb-3">
                    <label for="articleTags" class="form-label">Tags</label>
                    <select multiple class="form-select" id="articleTags" name="tags" required>
                        <option value="Java">Java</option>
                        <option value="Python">Python</option>
                        <option value="JavaScript">JavaScript</option>
                        <option value="HTML">HTML</option>
                        <option value="CSS">CSS</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Publish</button>
            </form>
        </div>
    </div>
</div>
<script src="/js/ckeditor/build/ckeditor.js"></script>
<script src="/js/article-create.js"></script>
</body>
</html>
