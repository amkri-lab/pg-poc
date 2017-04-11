import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        println "-------------- Running App on ${Environment.current.name()} ------------------"
    }
    def destroy = {
    }
}
