import { login } from './auth.js';


$(document).ready(function (){

    $('#submit-btn').on('click', function () {
        let username = $('#username').val();
        let password = $('#password').val();

        login(username, password);
    })
});