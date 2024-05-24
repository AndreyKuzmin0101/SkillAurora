import {sendAuthenticatedRequest} from "./auth.js";

let check_auth_request = sendAuthenticatedRequest('/api/v1/auth/check', {method: 'GET'})
let authenticated = check_auth_request.then(res => {
    return res.status === 200;
})

authenticated.then(res => {
    if (res === true) {
        let chats_response = sendAuthenticatedRequest('/api/v1/chats/me', {method: 'GET'});
        chats_response.then(res_chats => {
            if (res_chats.status === 200) {
                return res_chats.json();
            }
            return Promise.reject(res_chats.json());
        }).then(data => {
            data.forEach(chat => {
                $('#chats').append(
                    '<div data-target="' + chat.id + '" class="chat">' +
                        '<img class="chat-profile-mini-img" src="' + chat.secondUser.profileImage + '">' +
                        '<strong>' + chat.secondUser.username + '</strong>' +
                    '</div>'
                )
                $('.chat').on('click', function () {
                    disconnect();
                    chatId = $(this).data('target');
                    let messages_response = sendAuthenticatedRequest('/api/v1/chats/' + chatId + '/messages', {method: 'GET'})
                    messages_response.then(res_messages => {
                        if (res_messages.status === 200) {
                            connect(chatId);
                            return res_messages.json();
                        }
                        return Promise.reject(res_messages.json());
                    }).then(messages => {
                        messages.forEach(message => {
                            showMessage(
                                '<div>' +
                                '<div><span style="color: gray">' + message.sendTime + '</span></div>' +
                                '<img class="chat-profile-mini-img" src="' + message.sender.profileImage + '">' +
                                '<span>' + message.content + '</span>' +
                                '</div>'
                            )
                        })
                    }).catch(reason => alert(JSON.stringify(reason)))
                });
            })
        }).catch(reason =>  alert(JSON.stringify(reason)));
    } else {
        alert('Войдите в аккаунт, чтобы отправить сообщение');
        window.location.replace('/login');
    }
});
let stompClient = null;
let chatId = null;

function connect(chatId) {
    drawChat()
    console.log("Trying to connect");
    let token = localStorage.getItem('access_token');
    let socket = new SockJS("/message-websocket?token=" + token);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("Connected: " + frame);
        stompClient.subscribe("/chats/" + chatId + "/queue/messages", function (message) {
            let mess = JSON.parse(message.body);
            showMessage(
                '<div>' +
                '<div><span style="color: gray">' + mess.sendTime + '</span></div>' +
                '<img class="chat-profile-mini-img" src="' + mess.sender.profileImage + '">' +
                '<span>' + mess.content + '</span>' +
                '</div>'
            );
        })
    })
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected")
}

function sendMessage() {
    stompClient.send("/app/chats", {},
        JSON.stringify({'content': $("#message").val(), 'chatId': chatId}
    ));
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>")
}

$(window).on('beforeunload', function () { disconnect() })

function drawChat() {
    $('#chat').html('<div class="container" id="main-content">' +
        '<table id="conversation" class="table table-striped">' +
        '<thead>' +
        '<tr>' +
        '<th>Messages</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody id="messages">' +
        '</tbody>' +
        '</table>' +
        '<form class="inline">' +
        '<div class="form-group">' +
        '<input type="text" id="message" placeholder="Введите сообщение..." class="form-control">' +
        '<button id="send" class="default-button" style="margin-top: 10px" type="button">Отправить</button>' +
        '</div>' +
        '</form>' +
        '</div>');
    $("#send").click(function () {
        sendMessage();
    });
}