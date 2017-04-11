package com.pg.util

import groovy.util.logging.Log4j
/*
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.apache.http.client.HttpResponseException
*/

@Log4j
class RestApiUtil {

    /*def makeApiCall(String uri, ContentType contentType, Method method, Map requestData = [:]) {
        log.info("Making HTTP ${method.toString()} request. \nURI: ${uri} \nBase URL: ${baseUrl} \nRequest Data: ${requestData}")
        try {
            String urlAsString = baseUrl
            if (uri) {
                urlAsString = baseUrl + uri
            }
            def client = new HTTPBuilder(urlAsString)
            client.setContentType(contentType)
            def response
            switch (method) {
                case Method.POST:
                    response = client.post(path: null, body: requestData)
                    break
                case Method.GET:
                    response = client.get(path: null)
                    break
            }
            return response
        } catch (HttpResponseException ex) {
            ex.printStackTrace()
            return null
        } catch (ConnectException ex) {
            ex.printStackTrace()
            return null
        } catch (Exception ex) {
            ex.printStackTrace()
            return null
        }
    }

    String getBaseUrl(){
        ConfigHelper.payuUrl
    }
*/}
