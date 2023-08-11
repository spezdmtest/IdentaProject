var stomp = null;

window.onload = function () {
    connect();
}

function connect() {
    var socket = new SockJS('/socket');
    stomp = Stomp.over(socket);
    stomp.connect({}, function (frame) {
        console.log("Connected: " + frame);
        stomp.subscribe('/topic/users', function (user) {
            renderItem(user)
        })
    })
}

function renderItem(userJson) {
    var user = JSON.parse(userJson.body);
    $("#table").append("<tr>" +
        "<td>" + user.email + "</td>" +
        "</tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#send").click(function () {
        sendContent();
    });
});

function sendContent() {
    stomp.send("/app/users", {}, JSON.stringify({
        'email': $("#email").val()
    }));
}








