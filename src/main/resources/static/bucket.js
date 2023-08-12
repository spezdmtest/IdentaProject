var stomp = null;

window.onload = function () {
    connect();
}

function connect() {
    var socket = new SockJS('/socket');
    stomp = Stomp.over(socket);
    stomp.connect({}, function (frame) {
        console.log("Connected: " + frame);
        stomp.subscribe('/topic/bucket', function (bucket) {
            renderItem(bucket);
        })
    })
}

function renderItem(bucketJson) {
    var bucket = JSON.parse(bucketJson.body);
    $("#table").append("<tr>" +
        "<td>" + bucket.sum + "</td>" +
        "</tr>" +
        "<tr>" +
        "<td>" + bucket.detail.title + "</td>" +
        "<td>" + bucket.detail.total + "</td>" +
        "<td>" + bucket.price + "</td>" +
        "</tr>");

}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#send").click(function () {
        sendContent();
        location.href = location.href + '?_=' + new Date().getTime();
    });
});


function sendContent() {
    stomp.send("/app/bucket", {}, JSON.stringify({
        'id': $("#id").val()
    }));
}



