package poc

import grails.converters.JSON

class PayuController {

    def paymentService

    def seamless() {
        paymentService.nonSeamlessRequest()
    }

    def nonSeamless() {
       paymentService.nonSeamlessRequest()
    }

    def success() {
        paymentService.logRequestData(params)
        List emptyKeys = params.findAll{!it.value}.collect{it.key}
        [emptyKeys:emptyKeys,keys: (params.keySet() - emptyKeys),map:params,responsePage:"Success Page"]
    }

    def failure() {
        paymentService.logRequestData(params)
        List emptyKeys = params.findAll{!it.value}.collect{it.key}
        render(view:'success',model:[emptyKeys:emptyKeys,keys: (params.keySet() - emptyKeys),map:params,responsePage:"Failure Page"])
    }

    def cancel() {
        paymentService.logRequestData(params)
        List emptyKeys = params.findAll{!it.value}.collect{it.key}
        render(view:'success',model:[emptyKeys:emptyKeys,keys: (params.keySet() - emptyKeys),map:params,responsePage:"Failure Page"])
    }

    def makeApiCall(){
    }

    def verifyPaymentApi(String transactionId){
        Map map = paymentService.makePaymentVerificationCall(transactionId)
        render map as JSON
    }
}
