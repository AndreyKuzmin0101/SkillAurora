import {sendAuthenticatedRequest} from "./auth.js";


let check_auth_request = sendAuthenticatedRequest('/api/v1/auth/check', {method: 'GET'})
let authenticated = check_auth_request.then(res => {
    return res.status === 200;
})

let editor = ClassicEditor
    .create( document.querySelector( '#content' ), {
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

authenticated.then(res => {
    if (res === true) {
        $('#submit-button').on('click', function () {
            let title = $('#title').val();
            let content = window.editor.getData();
            let tags = getTagsFromSelectedTags();

            let json= {
                title: title,
                content: content,
                tags: tags
            }
            let jsonString = JSON.stringify(json);

            sendAuthenticatedRequest('/create/question', {
                method: 'POST',
                body: jsonString,
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(res => {
                if (res.status === 201) {
                    return res.json();
                }
                return res.json().then(error => Promise.reject(error));
            }).then(res => {
                window.location.replace(res.url);
            }).catch((reason) => {
                alert(JSON.stringify(reason));
            })
        });
    } else {
        alert('Войдите в аккаунт, чтобы задать вопрос')
        window.location.replace('/login');
    }
})
