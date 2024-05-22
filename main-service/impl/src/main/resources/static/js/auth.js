function saveToken(access_token, refresh_token) {
    localStorage.setItem('access_token', access_token);
    localStorage.setItem('refresh_token', refresh_token);
}

export function login(username, password) {
    $.ajax('/api/v1/auth/login', {
        method: 'POST',
        data: JSON.stringify({
            username: username,
            password: password
        }),
        contentType: 'application/json'
    }).done(function (data, textStatus, jqXHR) {
        if (jqXHR.status === 200) {
            saveToken(data.accessToken, data.refreshToken);
            window.location.replace('/');
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(JSON.stringify(jqXHR.responseJSON));
    });

}

function refreshTokens(refresh_token) {
     return fetch('/api/v1/auth/refresh', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({token: refresh_token})
    }).then((res) => {
        if (res.status === 200) {
            return res.json();
        }
        return Promise.reject();
    }).then(data => {
        saveToken(data.accessToken, data.refreshToken);
        return Promise.resolve(true);
    }).catch(() => {
        return Promise.reject(false);
     })
}

// resolved() только в случае, если пользователь успешно аутентифицирован
export async function sendAuthenticatedRequest(url, settings) {
    let access_token = localStorage.getItem('access_token');
    let refresh_token = localStorage.getItem('refresh_token');

    if (!access_token || !refresh_token) {
        return Promise.reject();
    }

    if (!settings.headers) {
        settings.headers = {};
    }
    settings.headers.Authorization = `Bearer ${access_token}`;
    let response = await fetch(url, settings);

    if (response.status === 401) {
        let refreshed = await refreshTokens(refresh_token);

        if (refreshed) {
            access_token = localStorage.getItem('access_token');
            settings.headers.Authorization = `Bearer ${access_token}`;
            response = await fetch(url, settings);
        }
    }
    return response;
}