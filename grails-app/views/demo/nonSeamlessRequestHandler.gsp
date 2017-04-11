<%@ page import="java.net.URLEncoder" %>
<%
    /*
       This is the sample Checkout Page JSP script. It can be directly used for integration with CCAvenue if your application is developed in JSP. You need to simply change the variables to match your variables as well as insert routines (if any) for handling a successful or unsuccessful transaction.
    */
%>
<html>
<head>
    <title>Sub-merchant checkout page</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>

<body>
Non Seamless kit
<form id="nonseamless" method="post" name="redirect" action="${url}">
<input type="hidden" id="encRequest" name="encRequest" value="${encRequest}">
<input type="hidden" name="access_code" id="access_code" value="${accessCode}">
<input type="hidden" name="merchant_id" id="merchant_id" value="${merchantId}">
<script language='javascript'>document.redirect.submit();</script>
</form>


</body>
</html>
