<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>DWR test</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script type='text/javascript' src='../dwr/engine.js'></script>
    <script type='text/javascript' src='../dwr/util.js'></script>
    <script type='text/javascript' src='../dwr/interface/DwrGateway.js'></script>
    <script type='text/javascript' src='../js/dwrtest.js'></script>
</head>

<body bgcolor=white>

<br />
<p>Dwr examples:</p>
*************
<br/>

<p>
    <br/>
    *************
    <br/>
    Simple example
    <br/>
    *************
    <br/>
    Name:
    <input type="text" id="dwrSimplePrefix" />
    <input type="button" id="dwrSimpleSend" value="Send" onclick="simpleUpdate()"/>
    <br/>
    Reply: <input type="text" id="dwrSimpleReply" readonly/>

    <br/>
    <br/>
    *************
    <br/>
    Complex example
    <br/>
    *************
    <br/>
    <br/>
    Name:
    <input type="text" id="dwrComplexPrefix" />
    <input type="button" id="dwrComplexSend" value="Send" onclick="complexUpdate()"/>
    <br/>
    Reply1: <input type="text" id="dwrComplexReply1" readonly/>
    <br/>
    Reply2: <input type="text" id="dwrComplexReply2" readonly/>
</p>


</body>
</html>