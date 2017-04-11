<%@ page import="com.pg.enums.PGType" %>
<html>
<head>
    <title>Payu | Seamless Request</title>
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
            <td colspan="2">
                Seamless Parameters
            </td>
        </tr>

        <tr>
            <td>
                Payment Gateway Type
            </td>
            <td>
                <g:select name="pg" from="${com.pg.enums.PGType.values()}"/>
            </td>
        </tr>

        <tr>
            <td>
                Drop Category
            </td>
            <td>
                <g:select name="drop_category" noSelection="['':'select']" from="${com.pg.enums.PGType.values()}"/>
            </td>
        </tr>

        <tr>
            <td>
                Bank
            </td>
            <td>
                <g:textField name="bankcode" value=""/>
            </td>
        </tr>

        <tr>
            <td colspan="2" style="border-bottom: 1px solid black">
                Credit Card Detail
            </td>
        </tr>

        <tr>
            <td>
                Credit Card Number
            </td>
            <td>
                <g:textField name="ccnum" value="5123456789012346"/>
            </td>
        </tr>
        <tr>
            <td>
                Name
            </td>
            <td>
                <g:textField name="ccname" value="abc"/>
            </td>
        </tr>

        <tr>
            <td>
                CVV no.
            </td>
            <td>
                <g:textField name="ccvv" value="123"/>
            </td>
        </tr>

        <tr>
            <td>
                Exp Year
            </td>
            <td>
                <g:textField name="ccexpyr" value="05"/>
            </td>
        </tr>

        <tr>
            <td>
                Exp Month
            </td>
            <td>
                <g:textField name="ccexpmon" value="22"/>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="submit">
            </td>
        </tr>
    </table>
</form>
</body></html>
