<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Payu Apis</title>
</head>

<body>
<g:form controller="payu" action="verifyPaymentApi">
    Transaction Id : <input type="text" name="transactionId"/>
    <g:submitButton name="submit" value="submit"/>
</g:form>
</body>
</html>