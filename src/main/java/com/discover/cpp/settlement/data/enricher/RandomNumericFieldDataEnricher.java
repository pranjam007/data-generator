package com.discover.cpp.settlement.data.enricher;

import java.util.Random;

import com.discover.cpp.settlement.data.interfaces.Context;
import com.discover.cpp.settlement.data.interfaces.Enricher;
import com.discover.cpp.settlement.data.metadata.FieldMetadata;

public class RandomNumericFieldDataEnricher implements Enricher<FieldMetadata, String> {

    private final String numbers = "0123456789";

    @Override
    public String enrich(FieldMetadata input, Context context) {
        Random r = new Random();
        char[] ch = new char[Integer.parseInt(input.getLength())];
        for (int i = 0; i < ch.length; i++) {
            ch[i] = numbers.charAt(r.nextInt(numbers.length()));
        }
        return new String(ch);
    }

}
