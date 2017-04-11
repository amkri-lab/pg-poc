package com.pg.util

import grails.util.Holders
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "ni")
@Component
class NIConfig {

    String url
    String merchantId
    String currency
    String encKey
}
