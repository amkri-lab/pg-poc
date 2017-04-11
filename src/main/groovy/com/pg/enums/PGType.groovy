package com.pg.enums

public enum PGType {

    CC("Credit Card"),
    NB("Net Banking"),
    DC("Debit Card"),
    CASH("Cash Card"),
    EMI("Emi")


    final String displayName

    PGType(String displayName) {
        this.displayName = displayName
    }

}
