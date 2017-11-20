package com.discover.cpp.settlement.data.interfaces;

import java.util.Set;

public interface Context {

    <T> T put(ContextKey key, Object value);
    
    <T> T get(ContextKey key);
    
    <T> T remove(Context key);
    
    Set<ContextKey> keys();
}
