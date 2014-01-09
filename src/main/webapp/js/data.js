
var jsonRequest = function (url, data, requestType) {
    console.log(JSON.stringify(data));
    $.ajax({
        url: url,
        data: JSON.stringify(data),
        type: requestType,
        contentType: "application/json; charset=utf-8",
        mimeType: 'application/json',
        async: false,
        cache: false,
        processData:false
    });
}

var baseUrl = "http://localhost:8080";

$("#btn1").click(function() {
    var data = {
        code: "af sadf asdf",
        name: "sdas dfasd fasd f"
    };

    jsonRequest(baseUrl + "/post", data, 'POST');

});
