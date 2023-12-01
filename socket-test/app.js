const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/blog-websocket',
    connectHeaders: {
        Authorization: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEuanBnIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MDIwNTMyNjIsInVzZXJuYW1lIjoic3NhciJ9.pXNUF6rjx9C7JaVlqg0yuNrAnMUtxGRIRp-WvXHOBcHxeJuUVns2aewt-9eExKv_O2i-1qFogtjr7_hfKNLn1Q', // 여기에 사용자의 인증 토큰을 넣어주세요
        // 다른 필요한 헤더들도 추가할 수 있습니다.
    },
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/sub/chat', (message) => {
        console.log("값다음");
        showGreeting(JSON.parse(message.body).message);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    console.log("값", $("#name").val());
    stompClient.publish({
        destination: "/pub/chat",
        headers: {
            Authorization: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpbWdVcmwiOiIvaW1hZ2VzLzEuanBnIiwic3ViIjoibWV0YWNvZGluZyIsImlkIjoxLCJleHAiOjE3MDIwNTMyNjIsInVzZXJuYW1lIjoic3NhciJ9.pXNUF6rjx9C7JaVlqg0yuNrAnMUtxGRIRp-WvXHOBcHxeJuUVns2aewt-9eExKv_O2i-1qFogtjr7_hfKNLn1Q', // 여기에 사용자의 인증 토큰을 넣어주세요
        },
        body: JSON.stringify({'message': $("#name").val()})
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});