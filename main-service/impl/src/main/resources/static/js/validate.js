function validate_email(email) {
    const emailRegex = /^\S+@\S+\.\S+$/;
    return !(email.length !== 0 && !emailRegex.test(email));

}

function validate_password(password) {
    const passwordRegex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
    return !(password.length !== 0 && !passwordRegex.test(password));

}

function validate_age(age) {
    return !(Number(age) > 120 || Number(age) < 0);

}


function submit_register_form(event){
    let age = $('#age').val();
    if (!validate_age(age)) {
        event.preventDefault();
        alert('Возраст не может быть меньше 0 и превышать 120.');
    }

    let email = $('#email').val();

    let password = $('#password').val();

    if (!validate_email(email)) {
        event.preventDefault();
        alert('Email не прошёл верификацию.');
    }

    if (!validate_password(password)) {
        event.preventDefault();
        alert('Пароль не удовлетворяет требованиям.');
    }

    let confirmPassword = $('#confirmPassword').val();
    if (password !== confirmPassword) {
        event.preventDefault();
        alert('Пароли не совпадают.')
    }
}