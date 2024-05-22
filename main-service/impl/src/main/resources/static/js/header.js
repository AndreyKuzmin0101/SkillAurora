import {sendAuthenticatedRequest} from "./auth.js";

let response = sendAuthenticatedRequest('/api/v1/users/me/profile-image', {method: 'GET'});

response.then((res) => {
    if (res.status === 200) {
        return res.text();
    } else {
        return Promise.reject();
    }
}).then((res) => {
    $('#header-buttons').append(
        '<div class = "write-article"><a href = "/create/article"><img src = "/images/pencil.png"></a></div>',
        '<div class = "profile-mini-img"><a href="/profile"><img src="' + res + '"></a></div>',
        '<div style="display: inline-block"><a href="/logout"><button class="header-button" style="background-color: #808080">Выйти</button></a></div>'
    );
}).catch(() => {
    $('#header-buttons').append(
        '<div style="display: inline-block"><a href="/login"><button class="header-button">Войти</button></a></div>',
        '<div style="display: inline-block"><a href="/register"><button class="header-button">Регистрация</button></a></div>'
    );
});

