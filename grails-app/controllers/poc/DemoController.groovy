package poc

import com.ccavenue.security.AesCryptUtil
import grails.converters.JSON

class DemoController {

    def demoService

    def iframe() {
        Map map =  demoService.CCAvenueConfig
        String merchantId = map.merchantId
        String orderId = "${new Date().time}"
        [merchantId: merchantId, orderId: orderId, responseUrl: createLink(controller: 'demo', action: 'response', absolute: true)]
    }


    def nonSeamless() {
        Map map =  demoService.CCAvenueConfig
        String merchantId = map.merchantId
        String orderId = "AS${new Date().time}"
        [merchantId: merchantId, orderId: orderId, responseUrl: createLink(controller: 'demo', action: 'response', absolute: true)]
    }

    def customCheckoutForm() {
        Map map =  demoService.CCAvenueConfig
        String merchantId = map.merchantId
        String orderId = "AS${new Date().time}"
        [merchantId: merchantId, orderId: orderId, accessCode: map.accessCode, responseUrl: createLink(controller: 'demo', action: 'response', absolute: true)]
    }

    def iframeRequest() {
        Map map =  demoService.CCAvenueConfig
        String merchantId = map.merchantId
        String accessCode = map.accessCode
        String workingKey = map.workingKey
        Enumeration enumeration = request.getParameterNames();
        String ccaRequest = "", pname = "", pvalue = "";
        while (enumeration.hasMoreElements()) {
            pname = "" + enumeration.nextElement();
            pvalue = request.getParameter(pname);
            ccaRequest = ccaRequest + pname + "=" + pvalue + "&";
        }

        AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
        String encRequest = aesUtil.encrypt(ccaRequest);
        println ">>>>>>>>>>>>>>>>>>>>>>>EncRequest>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>${encRequest}"
        println ">>>>>>>>>>>>>AccessCode>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>${accessCode}"
        String url = map.url
        render(view: 'iframeRequestHandler', model: [encRequest: encRequest, accessCode: accessCode, url: url, merchantId: merchantId])
    }

    def nonSeamlessRequest() {
        Map map =  demoService.CCAvenueConfig
        String merchantId = map.merchantId
        String accessCode = map.accessCode
        String workingKey = map.workingKey
        Enumeration enumeration = request.getParameterNames();
        String ccaRequest = "", pname = "", pvalue = "";
        while (enumeration.hasMoreElements()) {
            pname = "" + enumeration.nextElement();
            pvalue = request.getParameter(pname);
            ccaRequest = ccaRequest + pname + "=" + pvalue + "&";
        }

        AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
        String encRequest = aesUtil.encrypt(ccaRequest);
        println ">>>>>>>>>>>>>>>>>>>>>>>EncRequest>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>${encRequest}"
        println ">>>>>>>>>>>>>AccessCode>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>${accessCode}"
        String url = map.url
        render(view: 'nonSeamlessRequestHandler', model: [encRequest: encRequest, accessCode: accessCode, url: url, merchantId: merchantId])
    }

    def customCheckoutFormRequest() {
        Map map =  demoService.CCAvenueConfig
        String merchantId = map.merchantId
        String accessCode = map.accessCode
        String workingKey = map.workingKey
        Enumeration enumeration = request.getParameterNames();
        String ccaRequest = "", pname = "", pvalue = "";
        while (enumeration.hasMoreElements()) {
            pname = "" + enumeration.nextElement();
            pvalue = request.getParameter(pname);
            ccaRequest = ccaRequest + pname + "=" + pvalue + "&";
        }

        AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
        String encRequest = aesUtil.encrypt(ccaRequest);
        println ">>>>>>>>>>>>>>>>>>>>>>>EncRequest>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>${encRequest}"
        println ">>>>>>>>>>>>>AccessCode>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>${accessCode}"
        String url = map.url
        render(view: 'customCheckoutFormRequestHandler', model: [encRequest: encRequest, accessCode: accessCode, url: url, merchantId: merchantId])
    }

    def response() {
        Map map =  demoService.CCAvenueConfig
        String workingKey = map.workingKey
        //32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
        String encResp = request.getParameter("encResp");
        AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
        String decResp = aesUtil.decrypt(encResp);
        StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
        Hashtable hs = new Hashtable();
        List paramList = []
        String pair = null, pname = null, pvalue = null;
        while (tokenizer.hasMoreTokens()) {
            pair = (String) tokenizer.nextToken();
            if (pair != null) {
                StringTokenizer strTok = new StringTokenizer(pair, "=");
                pname = ""; pvalue = "";
                if (strTok.hasMoreTokens()) {
                    pname = (String) strTok.nextToken();
                    if (strTok.hasMoreTokens())
                        pvalue = (String) strTok.nextToken();
                    hs.put(pname, URLDecoder.decode(pvalue));
                    paramList << [pname: pname, pvalue: pvalue]
                }
            }
        }
        render(view: 'ccavResponseHandler', model: [paramList: paramList])
    }

    def ccavenuejson(){
        Map map =  demoService.CCAvenueConfig
        String url = "https://secure.ccavenue.com/transaction/transaction.do?command=getJsonData&access_code=${map.accessCode}&currency=INR&amount=${500}"
        List list = demoService.makeUrlConnectionCall(url)
        render (["abc":list] as JSON)
    }

}