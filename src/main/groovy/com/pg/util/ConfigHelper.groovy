package com.pg.util

import grails.util.Holders

class ConfigHelper {
    static Map getConfig() {
        Holders.config
    }

    static Map getGrails() {
        getConfig().grails
    }

    static Map getPayuConfig(){
        getConfig().payu
    }

    static Map getNIConfig(){
        getConfig().ni
    }

    static String getPayuKey(){
        payuConfig.key
    }

    static String getPayuSalt(){
        payuConfig.salt
    }

    static String getPayuProductInfo(){
        payuConfig.productInfo
    }

    static String getPayuUrl(){
        payuConfig.url
    }

    static String getPayuApiUrl(){
        payuConfig.apiUrl
    }
}
