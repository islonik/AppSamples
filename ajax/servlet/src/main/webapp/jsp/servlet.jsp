<%@ page import="java.util.UUID" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Servlet test</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script type='text/javascript' src='../js/jquery-3.2.1.min.js'></script>
    <script type='text/javascript' src='../js/servlet.js'></script>
</head>

<body bgcolor=white>

    <br />
    <p>Servlet examples:</p>
    *************
    <br/>

    <p>
        <% String id = UUID.randomUUID().toString();%>
        <br/>
        *************
        <br/>
        Simple example + <%= id %>
        <br/>
        *************
        <br/>
        Name:
        <input type="text" id="servletSimplePrefix" />
        <input type="button" id="servletSimpleSend" value="Send" onclick="simpleUpdate()"/>
        <br/>
        Reply: <input type="text" id="servletSimpleReply" readonly/>

        <br/>
        <br/>
        *************
        <br/>
        List example
        <br/>
        *************
        <br/>
        <br/>
        Name:
        <input type="text" id="servletListPrefix" />
        <input type="button" id="servletListSend" value="Send" onclick="listUpdate()"/>
        <input type="button" id="servletListClear" value="Clear" onclick="clearDiv()"/>
        <br/>
        <div id="hiddenDiv" hidden/>
        <br/>
    </p>


</body>
</html>