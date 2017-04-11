<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>
<body>
<H1>${responsePage}</H1>
<g:link controller="NI" action="nonSeamless">Back</g:link>
<table>
    <g:each in="${keys.grep()}" var="key" status="i">
        <tr>
            <td>${key}</td>
            <td>${map[key]}</td>
        </tr>
    </g:each>
</table>
<br/>
Empty Response Parameters are:
<br/>
${emptyKeys}
</body>
</html>