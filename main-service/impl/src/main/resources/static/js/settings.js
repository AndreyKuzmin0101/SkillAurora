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
        } else {
            return res.json().then(error => Promise.reject(error));
        }
    }).catch(reason => {alert(JSON.stringify(reason))});
}

$(document).ready(function () {
    $('#username-button').on('click', function () {
        let username = $('#username-input').val()
        update_field('username', username, this);
    });


    $('#age-button').on('click', function () {
        let age = $('#age-input').val()
        update_field('age', age, this);
    });


    let country;
    $('#search-countries').on('click', function () {
        $('#country-list').empty()
        let name = $('#country-search').val();

        $.getJSON('/api/v1/countries?name=' + name).done(function (data) {
            data.forEach(country => {
                $('#country-list').append('<option value="' + country.name + '">' + country.name + '</option>')
            })
        })
    });

    $('#country-button').on('click', function () {
        country = $('#country-list').val();

        update_field('country', country, this);
    });

    $('#search-cities').on('click', function () {
        $('#city-list').empty()
        let name = $('#city-search').val();

        if (!country && $('#country').text() !== '') {
            $.getJSON('/api/v1/countries?name=' + $('#country').text()).done(function (data) {
                if (data.length > 0) {
                    country = data[0].iso2;
                    $.getJSON('/api/v1/cities?name=' + name + '&country=' + country).done(function (data) {
                        data.forEach(country => {
                            $('#city-list').append('<option value="' + country.name + '">' + country.name + '</option>')
                        })
                    })
                }
            })
        } else {
            $.getJSON('/api/v1/cities?name=' + name + '&country=' + country).done(function (data) {
                data.forEach(country => {
                    $('#city-list').append('<option value="' + country.name + '">' + country.name + '</option>')
                })
            })
        }
    });

    $('#city-button').on('click', function () {
        let city = $('#city-list').val();

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
            } else {
                return res.json().then(error => Promise.reject(error))
            }
        }).catch(reason => alert(JSON.stringify(reason)));
    });


    $('.edit-btn').click(function () {
        let targetId = $(this).data('target');
        $('#edit' + targetId).toggle();
    });

    $('#delete-confirm-btn').on('click', function () {
        sendAuthenticatedRequest('api/v1/users/me', {method: 'DELETE'}).then(res => {
            if (res.status === 200) {
                alert('Аккаунт успешно удалён')
                localStorage.clear();
                window.location.replace("/");
            } else {
                return res.json().then(error => Promise.reject(error));
            }
        }).catch(reason => {alert(JSON.stringify(reason))})
    });

    $('#delete-cancel-btn').on('click', function () {
        $('#editDelete').toggle();
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
            return response.json().then(error => Promise.reject(error));
        }).then(data => {
            $('#profile-image').empty();
            $('#profile-image').append('<img src="' + data.url + '" style="max-width: 230px">');
        }).catch(reason => alert(JSON.stringify(reason)));
    });

});
