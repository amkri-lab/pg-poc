<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Response Handler</title>
</head>
<body>
<center>
    <font size="4" color="blue"><b>Response Page</b></font>
    <table border="1">
        <g:each in="${paramList}" var="param" status="i">
            <tr>
                <td>${param.pname}</td>
                <td>${param.pvalue}</td>
            </tr>

        </g:each>
    </table>
</center>
</body>
</html>