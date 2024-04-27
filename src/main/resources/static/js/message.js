let stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }

    $("#messages").html("");
}

function connect() {
    console.log("Trying to connect");
    let socket = new SockJS("/message-websocket");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("Connected: " + frame);
        setConnected(true);
        stompClient.subscribe("/chats/" + window.location.pathname.split('/')[2] + "/queue/messages", function (message) {
            let mess = JSON.parse(message.body);
            showMessage(mess.sender + ': ' + mess.content);
        })
    })
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected")
}

function sendMessage() {
    stompClient.send("/app/chats", {}, JSON.stringify({'content': $("#message").val(), 'chatId': window.location.pathname.split('/')[2],
        'sender': $("#name").val()}
    ));
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>")
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });

    $("#send").click(function () {
        sendMessage();
    });
})