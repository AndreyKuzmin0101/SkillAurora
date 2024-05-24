import {sendAuthenticatedRequest} from "./auth.js";

let check_auth_request = sendAuthenticatedRequest('/api/v1/auth/check', {method: 'GET'})
let authenticated = check_auth_request.then(res => {
    return res.status === 200;
})

authenticated.then(res => {
    let userId = window.location.pathname.split('/')[2];
    if (res === true) {
        $('#chat-btn').on('click', function () {
            sendAuthenticatedRequest('/api/v1/chats', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({'secondUserId': userId})
            }).then(res => {
                if (res.status === 201) {
                    window.location.replace('/chats')
                    return Promise.resolve();
                }
                return Promise.reject(res.json());
            }).catch(reason => {alert(JSON.stringify(reason))});
        })
    }
})