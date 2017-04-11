<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>NI Checkout</title>
    <asset:javascript src="jquery-2.2.0.min.js"/>
    <g:javascript>
    // $(document).ready(function(){
    //   $("#submit").trigger('click');
    // });
</g:javascript>
</head>

<body>
<form name="ecom" id="ecom" method="post" action="${url}">
    <input type="text" name="requestParameter" value="${requestParameter}" style="width: 100%">
    <input type="submit" name="submit" id="submit" value="Submit">
</form>
</body>
</html>
