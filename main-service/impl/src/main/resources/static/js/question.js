import {sendAuthenticatedRequest} from "./auth.js";

window.hasBestAnswer = false;
window.isAuthor = false;

let check_auth_request = sendAuthenticatedRequest('/api/v1/auth/check', {method: 'GET'})
let authenticated = check_auth_request.then(res => {
    return res.status === 200;
})

authenticated.then(res => {
    if (res === true) {
        loadAnswers()

        $('#write-answer').html('<h3>Оставьте свой ответ</h3>' +
            '<textarea class="form-control" id="answer-content" name="content" rows="8" placeholder="Напишите ваш ответ..." required></textarea>' +
            '<button style="margin-top: 10px" id="send-button" class="default-button" type="button">Ответить</button>')

        let editor = ClassicEditor
            .create( document.querySelector( '#answer-content' ), {
                simpleUpload: {
                    uploadUrl: 'http://localhost:8080/api/v1/files/upload/image',
                    types: ['png', 'jpg', 'jpeg'],
                    withCredentials: true,
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('access_token')
                    }
                }
            } )
            .then( editor => {
                window.editor = editor;
            } )
            .catch( handleSampleError );

        function handleSampleError( error ) {
            const issueUrl = 'https://github.com/ckeditor/ckeditor5/issues';

            const message = [
                'Oops, something went wrong!',
                `Please, report the following error on ${ issueUrl } with the build id "b2jez7pwo5fg-80e0rp4748r8" and the error stack trace:`
            ].join( '\n' );

            console.error( message );
            console.error( error );
        }

        window.editor = editor;

        $('#send-button').on('click', function (){sendAnswer()});
    }
})

function sendAnswer() {
    let questionId = window.location.pathname.split('/')[2];
    let content = window.editor.getData();
    sendAuthenticatedRequest('/api/v1/questions/' + questionId + '/answers', {
        method: 'POST',
        body: JSON.stringify({
            content: content
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        if (res.status === 201) {
            return res.json();
        }
        return res.json().then(error => Promise.reject(error));
    }).then(data => {
        appendAnswer(data)
    }).catch(reason => {alert(JSON.stringify(reason))});
}

function loadAnswers() {
    let questionId = window.location.pathname.split('/')[2];
    sendAuthenticatedRequest('/api/v1/questions/' + questionId + '/answers', {method: 'GET'}).then(res => {
        if (res.status === 200) {
            return res.json();
        }
        return res.json().then(error => Promise.reject(error));
    }).then(data => {
        data.forEach(answer => {
            appendAnswer(answer)

            if (answer.bestAnswer === true) {
                window.hasBestAnswer = true;
                $('#info-' + answer.id).append('<img src="/images/mark.png" width="40px">')
                $('#mark-label-' + answer.id).html('Снять метку')
                $('#btn-check-outlined-' + answer.id).prop('checked', true);
            }
        })

        $('.manage-answer').hide();

        sendAuthenticatedRequest('/questions/' + questionId + '/is-author', {method: 'GET'}).then(res => {
            if (res.status === 200) {
                return res.text();
            }
            return res.json().then(error => Promise.reject(error));
        }).then(isAuthor => {
            if (isAuthor === 'true') {
                window.isAuthor = true;
                $('.manage-answer').show();

                $('.btn-check').change(function () {
                    let targetId = $(this).data('target');
                    if ($(this).is(':checked')) {
                        sendAuthenticatedRequest('/api/v1/questions/answers/' + targetId + '/mark', {method: 'PUT'}).then(res => {
                            if (res.status === 200) {
                                $('#mark-label-' + targetId).html('Снять метку')
                            } else {
                                return res.json().then(error => Promise.reject(error));
                            }
                        }).catch(reason => alert(JSON.stringify(reason)));
                    } else {
                        sendAuthenticatedRequest('/api/v1/questions/answers/' + targetId + '/unmark', {method: 'PUT'}).then(res => {
                            if (res.status === 200) {
                                $('#mark-label-' + targetId).html('Пометить лучшим')
                            } else {
                                return res.json().then(error => Promise.reject(error));
                            }
                        }).catch(reason => alert(JSON.stringify(reason)));
                    }
                });

                if (window.hasBestAnswer === false) {
                    $('#manage-question').append(
                        '<button id="close-question" class="default-button">Закрыть</button>'
                    );
                    $('#close-question').on('click', function () {
                        sendAuthenticatedRequest('/questions/' + questionId + '/close', {method: 'PUT'}).then(res => {
                            if (res.status === 200) {
                                $('#close-question').hide()
                                alert('Вопрос закрыт!')
                            } else {
                                return res.json().then(error => Promise.reject(error));
                            }
                        }).catch(reason => {alert(JSON.stringify(reason))})
                    });
                }
            }
        }).catch(reason => {alert(JSON.stringify(reason))});

    }).catch(reason => {alert(JSON.stringify(reason))})
}


function appendAnswer(answer) {
    $('#answers').append(
        '<div class="answer">' +
        '<div class="manage-answer" style="display: flex; justify-content: right">' +
        '<input data-target="' + answer.id + '" type="checkbox" class="btn-check" id="btn-check-outlined-' + answer.id +'" autoComplete="off">' +
        '<label id="mark-label-' + answer.id + '" class="btn btn-outline-primary" for="btn-check-outlined-' + answer.id + '">Пометить лучшим</label>' +
        '</div>' +
        '<div id="info-' + answer.id + '" class="answer-info">' +
        '</div>' +
        '<div class="answer-content">' +
        '<span style="color: gray">Ответ дан: ' + answer.createdDate + '</span>' +
        '<div>' + answer.content  + '</div>' +
        '</div>' +
        '<div style="background-color: white" class="question-author-info">' +
        '<div class="question-header-author-info">' +
        '<img class="article-profile-mini-img" src="'+ answer.author.profileImage + '">' +
        '<span>' + answer.author.username + '</span>' +
        '</div>' +
        '</div>' +
        '<hr>' +
        '</div>'
    )
    $('.manage-answer').hide();

    if (window.isAuthor === true) {
        $('.manage-answer').show();
    }

}