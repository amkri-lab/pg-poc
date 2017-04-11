package poc

import com.toml.dp.util.EncDec
import grails.transaction.Transactional
import grails.web.mapping.LinkGenerator
import groovy.json.JsonSlurper

//import groovyx.net.http.ContentType
//import groovyx.net.http.Method
//import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.json.JSONException
import com.pg.ResponseLog
import com.pg.Transaction
import com.pg.util.ConfigHelper
import com.pg.util.NIConfig
import com.pg.util.SHA512Encryption
import com.pg.util.Encryption

import java.util.regex.Pattern

@Transactional
class PaymentService {

    LinkGenerator grailsLinkGenerator
    NIConfig niConfig

    def logRequestData(def params) {

        log.info("params : ${params}")
        String text = "eCwWELxi|${params.status}||||||${params.ud5 ?: ''}|${params.ud4 ?: ''}|${params.ud3 ?: ''}|${params.ud2 ?: ''}|" +
                "${params.ud1 ?: ''}|${params.email}|${params.firstname}|${params.productinfo}" +
                "|${params.amount}|${params.txnid}|${params.key}"
        println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>${text}"
        String hash = SHA512Encryption.encryptTextInSHA512(text)
        println ">>>>>>>>>>>>>>>>>>>>>>>hash1>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>${hash}"
        println ">>>>>>>>>>>>>>>>>>>>>>>hash2>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>${params.hash}"
        if (hash == params.hash) {
            println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>success"
        } else {
            println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Hash is not matched."
        }
    }

    /* def makeApiCall() {
         String testUrl = ConfigHelper.payuApiUrl;
         String method = "verify_payment";

         //Salt of the merchant
         String salt = ConfigHelper.payuSalt;

         //Key of the merchant
         String key = ConfigHelper.payuKey;

         String var1 = "4039937155099450102345678901"; // transaction id


         String toHash = key + "|" + method + "|" + var1 + "|" + salt;
         String Hashed = Encryption.hashCal(toHash);
         String Poststring = "key=" + key + "&command=" + method + "&hash=" + Hashed + "&var1=" + var1;
         //String Poststring = "key=" + key +  "&command=" + method +  "&hash=" + Hashed + "&var1=" + var1 + "&var2=" + var2 + "&var3=" + var3 ;
         println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>${Poststring}"
         RestApiUtil restApiUtil = new RestApiUtil()
         Map requestMap = [key: key, method: method, hash: Hashed, var1: var1]
         restApiUtil.makeApiCall("", ContentType.URLENC, Method.POST, requestMap)
     }*/


    Map makePaymentVerificationCall(String transactionId) {
        makeUrlConnectionCall("verify_payment", transactionId)
    }

    Map makeUrlConnectionCall(String command, String transactionId) {
        String testUrl = ConfigHelper.payuApiUrl;
        String method = command;

        //Salt of the merchant
        String salt = ConfigHelper.payuSalt;

        //Key of the merchant
        String key = ConfigHelper.payuKey;

        String var1 = transactionId; // transaction id
        String response = ""
        Map map = [:]

        String toHash = key + "|" + method + "|" + var1 + "|" + salt;
        String Hashed = Encryption.hashCal(toHash);
        String Poststring = "key=" + key + "&command=" + method + "&hash=" + Hashed + "&var1=" + var1;
        //String Poststring = "key=" + key +  "&command=" + method +  "&hash=" + Hashed + "&var1=" + var1 + "&var2=" + var2 + "&var3=" + var3 ;
        println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>${Poststring}"
        // Create connection

        try {
            URL url = new URL(testUrl);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(Poststring);
            wr.flush();

            conn.connect()
            if (conn.responseCode == 200) {
                response = conn.content.text
                println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>${response}"
                try {
                    def jsonSlurper = new JsonSlurper()
                    map = jsonSlurper.parseText(response) as Map
                    println(map)
                } catch (JSONException e) {
                }

            } else {
                println "An error occurred:"
                println connection.responseCode
                println connection.responseMessage
            }
            // Get the response
        } catch (IOException e) {

        }
        return map
    }

    Map nonSeamlessRequest() {
        log.info("non seamless method called")
        String key, txnid, amount, productinfo, firstname, email, salt, surl, furl, curl, mobileNumber
        key = ConfigHelper.payuKey
        salt = ConfigHelper.payuSalt
        amount = "10"
        productinfo = ConfigHelper.payuProductInfo
        firstname = "tushar"
        email = "tushar.saxena@tothenew.com"
        mobileNumber = "9999999999"
        Transaction transaction = Transaction.last()
        txnid = "POCS${transaction.id}"

//format key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5||||||SALT
        surl = grailsLinkGenerator.link(controller: 'payu', action: 'success', absolute: true)
        furl = grailsLinkGenerator.link(controller: 'payu', action: 'failure', absolute: true)
        curl = grailsLinkGenerator.link(controller: 'payu', action: 'cancel', absolute: true)
        String paramSequence = "${key}|${txnid}|${amount}|${productinfo}|${firstname}|${email}|||||||||||${salt}"
        String hash1 = SHA512Encryption.encryptTextInSHA512(paramSequence)
        println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>${hash1}"
        transaction = new Transaction(id: 1, status: "dsf").save(failOnError: true, flush: true)
        log.info("logging request")
        [firstname  : firstname, key: key, hash: hash1, txnid: txnid, amount: amount, email: email,
         productInfo: productinfo, surl: surl, furl: furl, curl: curl, mobileNumber: mobileNumber, url: ConfigHelper.payuUrl]
    }

    Map nonSeamlessRequestForNI() {
        log.info("non seamless method for NI called")
        Transaction transaction = Transaction.last()
//        Map niConfig = ConfigHelper.NIConfig
        Map responseMap = [:]
        responseMap.url = niConfig.url
        responseMap.MerchantOrderNumber = "POCS${transaction.id}"
        responseMap.Currency = niConfig.currency
        responseMap.Amount = 10
        responseMap.SuccessURL = grailsLinkGenerator.link(controller: "NI", action: 'success', absolute: true)
        responseMap.FailureURL = grailsLinkGenerator.link(controller: 'NI', action: 'success', absolute: true)
        responseMap.TransactionType = "01"
        responseMap.TransactionMode = "Internet"
        responseMap.PayModeType = "DD"
        responseMap.BillToFirstName = "Tushar"
        responseMap.BillToLastName = "Saxena"
        responseMap.BillToStreet1 = "Sector 127"
        responseMap.BillToCity = "Ghaziabad"
        responseMap.BillToState = "Uttar Pradesh"
        responseMap.BillToPostalCode = "201009"
        responseMap.BillToCountry = "IN"
        responseMap.BillToEmail = "tushar.saxena@tothenew.com"
        transaction = new Transaction(id: 1, status: "dsf").save(failOnError: true, flush: true)
        log.info("logging request")
        responseMap
    }

    String encryptNIRequest(def params){
//        Map niReqParamMap = nonSeamlessRequestForNI()
        Map niReqParamMap = params
        String merchantId = niConfig.merchantId
        println "Merchant ID : ${merchantId}"
        String encKey = niConfig.encKey
        println "Enc Key : ${encKey}"
        String message = "${niReqParamMap.MerchantOrderNumber}|${niReqParamMap.Currency}|${niReqParamMap.Amount}|${niReqParamMap.SuccessURL}|${niReqParamMap.FailureURL}|${niReqParamMap.TransactionType}" +
                "|${niReqParamMap.TransactionMode}|${niReqParamMap.PayModeType}|${niReqParamMap.CreditCardNumber}|${niReqParamMap.CVV}|${niReqParamMap.ExpiryMonth}|${niReqParamMap.ExpiryYear}|${niReqParamMap.CardType}|" +
                "${niReqParamMap.BillToFirstName}|${niReqParamMap.BillToLastName}|${niReqParamMap.BillToStreet1}||${niReqParamMap.BillToCity}|${niReqParamMap.BillToState}" +
                "|${niReqParamMap.BillToPostalCode}|${niReqParamMap.BillToCountry}|${niReqParamMap.BillToEmail}||||||||||||||||||||||||||||||"
        println "meesage to be encrypted : ${message}"
        EncDec aesEncrypt = new EncDec(encKey)
        String encryptedMessage = aesEncrypt.encrypt(message)
        println "encrypted text : ${encryptedMessage}"
        String requestedParameter = "${merchantId}|${encryptedMessage}"
        requestedParameter
    }

    Map decryptNIRequest(String responseParameter) {
        EncDec aesDecrypt = new EncDec(niConfig.encKey,responseParameter)
        String message = aesDecrypt.decrypt()
        println "decrypted message : ${message}"
        List splittedList = message.split(Pattern.quote("|"))
        Map niMap =[:]
        niMap.OrderNumber = splittedList[0]
        niMap.NIOnlineRefID= splittedList[1]
        niMap.Currency= splittedList[2]
        niMap.Amount = splittedList[3]
        niMap.Status = splittedList[4]
        niMap.BankReferenceNumber = splittedList[5]
        niMap.PayModeType= splittedList[6]
        niMap.CardType = splittedList[7]
        niMap.ErrorCode = splittedList[8]
        niMap.ErrorResponseMessage = splittedList[9]
        niMap.TransactionType = splittedList[10]
        niMap.CardEnrollmentResponse= splittedList[11]
        niMap.ECI_Values= splittedList[12]
        niMap.PGAuthCode = splittedList[13]
        niMap.FraudDecision = splittedList[14]
        niMap.FraudReason = splittedList[15]
        niMap.DCC_Converted = splittedList[16]
        niMap.DCC_ConvertedAmount = splittedList[17]
        niMap.DCC_ConvertedCurrency= splittedList[18]
        niMap.udf1 = splittedList[19]
        niMap.udf2 = splittedList[20]
        niMap.udf3 = splittedList[21]
        niMap.udf4 = splittedList[22]
        niMap.udf5 = splittedList[23]
        niMap.DCC_ExchangeRate = splittedList[24]
        niMap.DCC_Margin_Rate = splittedList[25]
        niMap.NeO_Field1 = splittedList[26]
        niMap.NeO_Field2 = splittedList[27]
        niMap.NeO_Field3 = splittedList[28]
        niMap.NeO_Field4 = splittedList[29]
        niMap.NeO_Field5 = splittedList[30]
        niMap.NeO_Field6 = splittedList[31]
        niMap.NeO_Field7 = splittedList[32]
        niMap.NeO_Field8 = splittedList[33]
        niMap.NeO_Field9 = splittedList[34]
        niMap.NeO_Field10 = splittedList[35]
        logResponse(niMap)
        niMap
    }

    def logResponse(Map niMap){
        ResponseLog responseLog = new ResponseLog()
        responseLog.message = niMap.toString()
        responseLog.status = niMap.Status
        responseLog.save(failOnError: true,flush: true)
    }
}
