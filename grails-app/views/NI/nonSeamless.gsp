<html>
<head>
    <title>Payu | Non Seamless Request</title>
</head>

<body>
<g:form controller="NI" action="makeNIRequest" method='post'>

    <table>
        <tr>
            <td>Merchant Order Number</td>
            <td><input type="text" name="MerchantOrderNumber" value="${nimap?.MerchantOrderNumber}"/>
            </td>
        </tr>
        <tr>
            <td>Currency</td>
            <td><input type="text" name="Currency" value="${nimap?.Currency}"/>
            </td>
        </tr>
        <tr>
            <td>Amount</td>
            <td><input type="text" name="Amount" value="${nimap?.Amount}"/>
            </td>
        </tr>
        <tr>
            <td>
                Success Url
            </td>
            <td><input type="text" name="SuccessURL" value="${nimap?.SuccessURL}"/>
            </td>
        </tr>
        <tr>
            <td>
                Failure Url
            </td>
            <td>
                <input type="text" name="FailureURL" value="${nimap?.FailureURL}"/>
            </td>
        </tr>
        <tr>
            <td>Transaction Type</td>
            <td><input type="text" name="TransactionType" value="${nimap?.TransactionType}"/>
            </td>
        </tr>
        <tr>
            <td>Transaction Mode</td>
            <td><input type="text" name="TransactionMode" value="${nimap?.TransactionMode}"/>
            </td>
        </tr>
        <tr>
            <td>Pay Mode Type</td>
            <td><input type="text" name="PayModeType" value="${nimap?.PayModeType}"/>
            </td>
        </tr>
        <tr>
            <td>Bill to First Name</td>
            <td><input type="text" name="BillToFirstName" value="${nimap?.BillToFirstName}"/></td>
        </tr>
        <tr>
            <td>Bill to Last Name</td>
            <td><input type="text" name="BillToLastName" value="${nimap?.BillToLastName}"/></td>
        </tr>
        <tr>
            <td>Bill to Street1</td>
            <td><input type="text" name="BillToStreet1" value="${nimap?.BillToStreet1}"/></td>
        </tr>
        <tr>
            <td>Bill to City</td>
            <td><input type="text" name="BillToCity" value="${nimap?.BillToCity}"/></td>
        </tr>
        <tr>
            <td>Bill to State</td>
            <td><input type="text" name="BillToState" value="${nimap?.BillToState}"/></td>
        </tr>
        <tr>
            <td>Bill to Postal Code</td>
            <td><input type="text" name="BillToPostalCode" value="${nimap?.BillToPostalCode}"/></td>
        </tr>
        <tr>
            <td>Bill to Country</td>
            <td><input type="text" name="BillToCountry" value="${nimap?.BillToCountry}"/></td>
        </tr>
        <tr>
            <td>Bill to Email</td>
            <td><input type="text" name="BillToEmail" value="${nimap?.BillToEmail}"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">Credit Card Related</td>
        </tr>
        <tr>
            <td>Credit Card Number</td>
            <td>
                <input type="text" name="CreditCardNumber" value=""/>
            </td>
        </tr>
        <tr>
            <td>CVV</td>
            <td>
                <input type="text" name="CVV" value=""/>
            </td>
        </tr>
        <tr>
            <td>Expiry Month</td>
            <td>
                <input type="text" name="ExpiryMonth" value=""/>
            </td>
        </tr>
        <tr>
            <td>Expiry Year</td>
            <td>
                <input type="text" name="ExpiryYear" value=""/>
            </td>
        </tr>
        <tr>
            <td>Card Type</td>
            <td>
                <select name="CardType" id="CardType">
                    <option value="MASTERCARD">Master Card</option>
                    <option value="VISA">Visa</option>
                    <option value="DINERS">Diners</option>
                    <option value="AMEX">Amex</option>
                    <option value="JCB">JCB</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="submit">
            </td>
        </tr>
    </table>
</g:form>
</body>
</html>