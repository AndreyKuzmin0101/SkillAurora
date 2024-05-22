$(document).ready(function (){
    $('#register-btn').on('click', function () {
        let realName = $('#realName').val();
        let age = $('#age').val();
        let username = $('#username').val();
        let country = $('#country').val()
        let city = $('#city').val()
        let email = $('#email').val();
        let password = $('#password').val();
        let confirmPassword = $('#confirmPassword').val();

        if (!validate_age(age)) {
            alert('Возраст не может быть меньше 0 и превышать 120.');
            return;
        }

        if (!validate_email(email)) {
            alert('Email не прошёл валидацию.');
            return;
        }

        if (!validate_password(password)) {
            alert('Пароль не удовлетворяет требованиям.');
            return;
        }

        if (password !== confirmPassword) {
            alert('Пароли не совпадают.')
            return;
        }

        let skills = getTagsFromSelectedTags();
        let json= {
            realName: realName,
            age: age,
            username: username,
            country: country,
            city: city,
            email: email,
            password: password,
            skills: skills
        }

        let jsonString = JSON.stringify(json);

        $.ajax({
            type: "POST",
            url: "/api/v1/users",
            data: jsonString,
            contentType: 'application/json'
        }).done(function (){
            window.location.replace("/login")
        });
    });
});