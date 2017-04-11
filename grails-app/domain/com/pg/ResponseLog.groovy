package com.pg

class ResponseLog {

    Long id
    String status
    String message

    static constraints = {
        id(unique: true,nullable: true)
        status(nullable: true)
        message(nullable: true)
    }

    static mapping = {
        id generator: 'increment'
        message sqlType: 'varchar(1000)'
    }
}
