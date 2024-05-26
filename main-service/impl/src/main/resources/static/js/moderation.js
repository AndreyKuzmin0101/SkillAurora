import {sendAuthenticatedRequest, sendWithTokensIfExist} from "./auth.js";

sendWithTokensIfExist('/api/v1/auth/check-role-access?url=/moderation', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    }
}).then(res => {
    if (res.status === 200) {
        return res.text();
    }
    return Promise.reject(res);
}).then(res => {
    if (res === 'false') {
        return Promise.reject();
    }
    return Promise.resolve();
}).then(() => {
    let page = 0;
    let size = 10;
    sendAuthenticatedRequest('/api/v1/articles/moderation?page='+ page + '&size=' + size , {method: 'GET'}).then(res => {
        if (res.status === 200) {
            return res.json();
        }
        return Promise.reject(res.json());
    }).then(data => {
        data.forEach(article => {
            appendArticle(article)
        });
    }).catch(reason => {alert(JSON.stringify(reason))});
}).catch((reason) => {
    if (reason.status === 401) {
        alert("Вы не вошли в аккаунт");
        window.location.replace('/login');
    } else if (reason.status === 403) {
        alert('Вы не являетесь модератором!');
        window.location.replace('/');
    } else {
        alert("Something went wrong.")
        window.location.replace('/');
    }
})

function appendArticle(article) {
    $('#moderation-articles').append(
        '<div id="article-' + article.id + '" class="article">' +
        '<div><img src="'+ article.author.profileImage +'" class="article-profile-mini-img">' +
        '<strong>'+ article.author.username +'</strong>' +
        '<span style="color: gray">Дата публикации: ' + article.publicationDate + '</span>' +
        '</div>' +
        '<h3><strong>'+ article.title + '</strong></h3>' +
        '<img src="'+ article.cover + '">' +
        '<br>' +
        '<span>' + article.description + '</span>' +
        '<div id="tags-' + article.id + '"></div>' +
        '<div style="display: flex; justify-content: space-between">' +
        '<a href="/articles/' + article.id + '" style="text-decoration: none;">' +
        '<button class="btn btn-outline-success">Читать далее</button>' +
        '</a>' +
        '<div style="display:inline-block;">' +
        '<button style="margin-right: 10px" id="confirm-btn-' + article.id + '" class="confirm-button">ПРИНЯТЬ</button>' +
        '<button id="reject-btn-' + article.id + '" class="reject-button">ОТКЛОНИТЬ</button>' +
        '</div>' +
        '</div>' +
        '</div>'
    );

    article.tags.forEach(function (tag) {
        $('#tags-' + article.id).append('<span class="tag-style">' + tag.name + '</span>')
    });

    $('#confirm-btn-' + article.id).on('click', function () {
        sendAuthenticatedRequest('/api/v1/articles/' + article.id + '/moderation/confirm', {method: 'PUT'}).then(res => {
            if (res.status === 200) {
                $('#article-' + article.id).remove();
            } else {
                return res.json().then(error => Promise.reject(error));
            }
        })
    })
    $('#reject-btn-' + article.id).on('click', function () {
        sendAuthenticatedRequest('/api/v1/articles/' + article.id + '/moderation/reject', {method: 'PUT'}).then(res => {
            if (res.status === 200) {
                $('#article-' + article.id).remove();
            } else {
                return res.json().then(error => Promise.reject(error));
            }
        })
    })
}