package com.discover.cpp.settlement.data.enricher;

import java.util.HashMap;
import java.util.Map;

import com.discover.cpp.settlement.data.interfaces.Context;
import com.discover.cpp.settlement.data.interfaces.Enricher;
import com.discover.cpp.settlement.data.metadata.FieldMetadata;

public class CounterBasedFieldDataEnricher implements Enricher<FieldMetadata, String> {

    private Map<String, Long> counterMap = new HashMap<>();

    public CounterBasedFieldDataEnricher() {

    }

    @Override
    public String enrich(FieldMetadata input, Context context) {
        Long count = counterMap.get("COUNT");
        if (count != null) {
            count += 1L;
            counterMap.put("COUNT", count);
        } else {
            count = 1L;
            counterMap.put("COUNT", count);
        }
        StringBuilder lengthFormat = new StringBuilder();
        lengthFormat.append("%0");
        lengthFormat.append(input.getLength());
        lengthFormat.append("d");
        
        return String.format(lengthFormat.toString(), count); 
    }

}
