package com.discover.cpp.settlement.data.common;

public enum DataTypes {
    FILE("FILE"),
    BATCH("BATCH"),
    TXN("TXN");
    
    String name;
    
    DataTypes(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
