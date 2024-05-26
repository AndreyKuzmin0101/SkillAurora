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