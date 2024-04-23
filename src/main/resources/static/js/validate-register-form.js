function submit_form(event){
    let age = $('#age').val();
    if (Number(age) > 120 || Number(age) < 0) {
        event.preventDefault();
        alert('Возраст не может быть меньше 0 и превышать 120.');
    }

    let email = $('#email').val();
    const emailRegex = /^\S+@\S+\.\S+$/;

    let password = $('#password').val();
    const passwordRegex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;

    if (email.length !== 0 && !emailRegex.test(email)) {
        event.preventDefault();
        alert('Email не прошёл верификацию.');
    }

    if (password.length !== 0 && !passwordRegex.test(password)) {
        event.preventDefault();
        alert('Пароль не удовлетворяет требованиям.');
    }

    let confirmPassword = $('#confirmPassword');
    if (password !== confirmPassword) {
        event.preventDefault();
        alert('Пароли не совпадают.')
    }
}