function update_field(field, new_value, button) {
    $.ajax({
        url: '/settings',
        method: 'PUT',
        data:JSON.stringify({[field]: new_value}),
        contentType: 'application/json'
    }).done(function (){
        $('#'+field).html(new_value)
        let targetId = $(button).data('target');
        $('#edit' + targetId).toggle();
    });
}

$(document).ready(function() {
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
        if(password !== confirmedPassword) {
            alert("Пароли не совпадают.")
            return;
        }

        update_field('password', password, this)
    });

    $('#skills-button').on('click', function () {
        let skills = [];
        $('#selected-tags span').each(function(){
            // Получаем текст каждого span и выводим его в консоль
            let spanText = $(this).text();
            let jsonSkill = {}
            jsonSkill.name = spanText;
            skills.push(jsonSkill);
        });

        $.ajax({
            url: '/settings',
            method: 'PUT',
            data:JSON.stringify({'skills': skills}),
            contentType: 'application/json'
        }).done(function (){
            $('#skills').empty();
            skills.forEach(function (skill) {
                $('#skills').append('<span class="tag-style">' + skill.name + '</span>')
            })
            $('#editSkills').toggle();
        });
    });

    $('.edit-btn').click(function() {
        let targetId = $(this).data('target');
        $('#edit' + targetId).toggle();
    });
});