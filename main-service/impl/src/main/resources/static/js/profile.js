import {sendAuthenticatedRequest} from "./auth.js";


let response = sendAuthenticatedRequest('/api/v1/users/me/profile', {method: 'GET'});

response.then(res => {
    if (res.status === 200) {
        return res.json();
    }
    return Promise.reject();
}).then(data => {
    $('#username').html(data.username);
    if ('realName' in data) {
        $('#realName').html(data.realName);
    }
    if ('age' in data) {
        $('#age').html(data.age);
    }
    $('#email').html(data.email);
    if ('country' in data) {
        $('#country').html(data.country)
    }
    if ('city' in data) {
        $('#city').html(data.city);
    }
    $('#registerDate').html(data.registerDate);
    $('#rating').html(data.rating);
    $('#profile-img').attr('src', data.profileImage);
    data.skills.forEach(skill => {
        $('#skills').append('<span class="tag-style">' + skill.name + '</span>');
        $('#selected-tags').append('<span class="tag-style new-tag">' + skill.name + '</span>');
    });

    $('.new-tag').on('click', function () {
        $(this).remove();
    });
}).catch(() => {
    window.location.replace('/');
    alert('Войдите, чтобы зайти в профиль');
});