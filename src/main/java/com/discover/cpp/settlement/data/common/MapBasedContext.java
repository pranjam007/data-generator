package com.discover.cpp.settlement.data.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.discover.cpp.settlement.data.interfaces.Context;
import com.discover.cpp.settlement.data.interfaces.ContextKey;

public class MapBasedContext implements Context {

    private final Map<ContextKey, Object> contextMap;
    
    public MapBasedContext() {
        this.contextMap = new HashMap<>();
    }
    
    public MapBasedContext(Map<ContextKey, Object> contextMap) {
        this.contextMap = contextMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T put(ContextKey key, Object value) {
        return (T) contextMap.put(key, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(ContextKey key) {
        return (T) contextMap.get(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T remove(Context key) {
        return (T) contextMap.remove(key);
    }

    @Override
    public Set<ContextKey> keys() {
        return contextMap.keySet();
    }
    
    
    
}
