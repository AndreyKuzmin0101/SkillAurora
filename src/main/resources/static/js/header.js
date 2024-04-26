$.get('/auth-check', function (response) {
    if (response === true) {
        let profile_image;
        $.get("/api/v1/users/me/profile-image", function (response) {
           profile_image = response;
            $('#header-buttons').append(
                '<div class = "write-article"><a href = "/create/article"><img src = "/images/pencil.png"></a></div>',
                '<div class = "profile-mini-img"><a href="/profile"><img src="' + profile_image + '"></a></div>',
                '<div style="display: inline-block"><a href="/logout"><button class="header-button" style="background-color: #808080">Выйти</button></a></div>'
            );
        });
    } else {
        $('#header-buttons').append(
            '<div style="display: inline-block"><a href="/login"><button class="header-button">Войти</button></a></div>',
            '<div style="display: inline-block"><a href="/register"><button class="header-button">Регистрация</button></a></div>'
        );
    }
});