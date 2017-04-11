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


%{--iframe start--}%
<center>
    <br><br>
    <!-- width required mininmum 482px -->
    <iframe  width="482" height="500" scrolling="No" frameborder="0"  id="paymentFrame" src="${url}&merchant_id=${merchantId}&encRequest=${encRequest}&access_code=${accessCode}">
    </iframe>
</center>

<script type="text/javascript">
    $(document).ready(function(){
        $('iframe#paymentFrame').load(function() {
            window.addEventListener('message', function(e) {
                $("#paymentFrame").css("height",e.data['newHeight']+'px');
            }, false);
        });
    });
</script>
%{--iframe end--}%
</body>
</html>
