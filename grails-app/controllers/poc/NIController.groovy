package poc

import com.pg.util.ConfigHelper

class NIController {

    def paymentService

    static defaultAction = "nonSeamless"

    def nonSeamless() {
        Map responseMap = paymentService.nonSeamlessRequestForNI()
        [nimap: responseMap]
    }

    def makeNIRequest() {
        String url = ConfigHelper.NIConfig.url
        [requestParameter: paymentService.encryptNIRequest(params), url: url]
    }

    def success(String responseParameter) {
        println responseParameter
        Map map = paymentService.decryptNIRequest(responseParameter)
        [map: map, keys: map.keySet(), emptyKeys: map.findAll { !it.value }.collect { it.key }]
    }

}
