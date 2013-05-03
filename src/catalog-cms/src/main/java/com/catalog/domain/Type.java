package com.catalog.domain;

public enum Type {
    LOCAL(0),
    REMOTE(1);
    
    private final int typeCode;
    
    Type(int code){
    	typeCode = code;    	
    }
    
    public int code(){
    	return typeCode;
    }
    
}