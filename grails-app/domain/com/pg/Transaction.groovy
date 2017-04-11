package com.pg

class Transaction {

    Long id
    String status

    static constraints = {
        id(unique: true,nullable: true)
        status(nullable: true)
    }

    static mapping = {
        id generator: 'increment'
    }
}
