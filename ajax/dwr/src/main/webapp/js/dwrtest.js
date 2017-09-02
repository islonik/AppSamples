function simpleUpdate() {
    var prefix = dwr.util.getValue("dwrSimplePrefix");
    DwrGateway.getPrefixId(prefix, simpleUpdateInfo);
}

function simpleUpdateInfo(data) { // callback, should be used in that way
    var response = "" + data;
    //dwr.util.setValue("dwrSimpleReply", response);
    document.getElementById('dwrSimpleReply').value = response;
}

function complexUpdate() {
    var prefix = dwr.util.getValue("dwrComplexPrefix");
    DwrGateway.getDataObject(prefix, complexUpdateInfo);
}

function complexUpdateInfo(data) { // callback, should be used in that way
    //var response = "" + data;
    //dwr.util.setValue("dwrComplexReply", response);
    document.getElementById('dwrComplexReply1').value = data.id;
    document.getElementById('dwrComplexReply2').value = data.prefix;
}