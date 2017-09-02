function simpleUpdate() {
    var prefixValue = document.getElementById('servletSimplePrefix').value;
    $.ajax({
        url: '../SimpleAjaxServlet',
        data: {prefix: prefixValue},
        type: 'get',
        cache: false,
        success: simpleUpdateInfo,
        error: errorSimpleUpdateInfo
    });
}

function simpleUpdateInfo(data) { // callback, should be used in that way
    var response = "" + data;
    document.getElementById('servletSimpleReply').value = response;
}

function errorSimpleUpdateInfo() { // callback, should be used in that way
    document.getElementById('servletSimpleReply').value = 'Error';
}

function clearDiv() {
    document.getElementById("hiddenDiv").innerHTML = "";
    // $("#hiddenDiv").html('');
    $("#hiddenDiv").hide();
}

function listUpdate() {
    var prefixValue = document.getElementById('servletListPrefix').value;
    $.ajax({
        url: '../ListAjaxServlet',
        data: {prefix: prefixValue},
        type: 'get',
        cache: false,
        success: listUpdateInfo,
        error: errorListUpdateInfo
    });
}

function listUpdateInfo(data) { // callback, should be used in that way
    var $ul = $("<ul>").appendTo($("#hiddenDiv")); // Create HTML <ul> element and append it to HTML DOM element with ID "somediv".
    var arrayLength = data.length; // data is json
    for (var i = 0; i < arrayLength; i++) {
        $("<li>").text(data[i]).appendTo($ul);        // Create HTML <li> element, set its text content with currently iterated item and append it to the <ul>.
    }
    $("#hiddenDiv").show();
}

function errorListUpdateInfo() { // callback, should be used in that way
    document.getElementById('hiddenDiv').value = 'Error';
}