<html>
<head>
    <title>Payu | Non Seamless Request</title>
</head>

<body>
<form action="${url}" method='post'>

    <table>
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstname" value="${firstname}"/></td>
        </tr>
        <tr>
            <td>
                Success Url
            </td>
            <td><input type="text" name="surl" value="${surl}"/>
            </td>
        </tr>
        <tr>
            <td>
                Mobile Number
            </td>
            <td>
                <input type="text" name="phone" value="${mobileNumber}"/>
            </td>
        </tr>
        <tr>
            <td>
                Key
            </td>
            <td><input type="text" name="key" value="${key}"/>
            </td>
        </tr>
        <tr>
            <td>
                Hash
            </td>
            <td><input type="text" name="hash" value="${hash}"/>
            </td>
        </tr>
        <tr>
            <td>
                Cancel Url
            </td>
            <td><input type="text" name="curl" value="${curl}"/>
            </td>
        </tr>
        <tr>
            <td>
                Failure Url
            </td>
            <td><input type="text" name="furl" value="${furl}"/>
            </td>
        </tr>
        <tr>
            <td>Transaction Id</td>
            <td><input type="text" name="txnid" value="${txnid}"/>
            </td>
        </tr>
        <tr><td>Product Info</td>
            <td><input type="text" name="productinfo" value="${productInfo}"/>
            </td>
        </tr>
        <tr>
            <td>Amount</td>
            <td><input type="text" name="amount" value="${amount}"/>
            </td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="${email}"/>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="submit">
            </td>
        </tr>
    </table>
</form>
</body>
</html>