var prefix = "http://localhost:8081/item/";
var createItem = function () {
    var description = $("input#idescription-value").val();
    var price = $("input#price-value").val();
    var currency = $("input#currency-value").val();
    var state = $("input#state-value").val();

    var JsonObject = {
        name: name
    }
    console.log("Request: " + name);
    $.ajax({
        type: "POST",
        url: prefix,
        data: "json",
        async: true,
        contentType: "multipart/form-data",
        data: JSON.stringify(JsonObject),
        success: function (result) {
            console.log(result)
            $(".hello-reaction").html('<h1>' + result.id + ' ' + result.name + '</h1>')
        },
        error: function (jqXHR, textStatus, errorThrow) {
            console.log("ERROR: ");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrow);
        }
    })
}