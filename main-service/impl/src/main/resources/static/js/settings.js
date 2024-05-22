import {sendAuthenticatedRequest} from "./auth.js";

function update_field(field, new_value, button) {
    sendAuthenticatedRequest('/api/v1/users/settings',
        {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({[field]: new_value})
        }).then(res => {
        if (res.status === 200) {
            $('#' + field).html(new_value)
            let targetId = $(button).data('target');
            $('#edit' + targetId).toggle();
        }
    });
}

$(document).ready(function () {
    $('#age-button').on('click', function () {
        let age = $('#age-input').val()
        update_field('age', age, this);
    });

    $('#country-button').on('click', function () {
        let country = $('#country-input').val()
        update_field('country', country, this);
    });

    $('#city-button').on('click', function () {
        let city = $('#city-input').val()
        update_field('city', city, this);
    });
    $('#email-button').on('click', function () {
        let email = $('#email-input').val()
        if (!validate_email(email)) {
            update_field('email', email, this);
        } else {
            alert("Email не прошёл верификацию.")
        }
    });
    $('#realName-button').on('click', function () {
        let realName = $('#realName-input').val()
        update_field('realName', realName, this)
    });

    $('#password-button').on('click', function () {
        let password = $('#password-input').val()
        let confirmedPassword = $('#confirmedPassword-input').val()
        if (!validate_password(password)) {
            alert("Пароль не прошёл верификацию.")
            return;
        }
        if (password !== confirmedPassword) {
            alert("Пароли не совпадают.")
            return;
        }

        update_field('password', password, this)
    });

    $('#skills-button').on('click', function () {
        let skills = [];
        $('#selected-tags span').each(function () {
            // Получаем текст каждого span и выводим его в консоль
            let spanText = $(this).text();
            let jsonSkill = {}
            jsonSkill.name = spanText;
            skills.push(jsonSkill);
        });

        sendAuthenticatedRequest('/api/v1/users/settings', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({'skills': skills})
        }).then(res => {
            if (res.status === 200) {
                $('#skills').empty();
                skills.forEach(function (skill) {
                    $('#skills').append('<span class="tag-style">' + skill.name + '</span>')
                })
                $('#editSkills').toggle();
            }
        });
    });

    $('.edit-btn').click(function () {
        let targetId = $(this).data('target');
        $('#edit' + targetId).toggle();
    });

    $('#upload-image').click(function () {
        let formData = new FormData();
        let fileInput = $('#image-input')[0].files[0];
        formData.append('upload', fileInput);


        sendAuthenticatedRequest('/api/v1/users/settings/profile-image', {
            method: 'PUT',
            body: formData,
        }).then(response => {
            if (response.status === 200) {
                return response.json();
            }
            return Promise.reject();
        }).then(data => {
            $('#profile-image').empty();
            $('#profile-image').append('<img src="' + data.url + '" style="max-width: 230px">');
        });
    });

});
