package com.discover.cpp.settlement.data.enricher;

import java.util.Random;

import com.discover.cpp.settlement.data.interfaces.Context;
import com.discover.cpp.settlement.data.interfaces.Enricher;
import com.discover.cpp.settlement.data.metadata.FieldMetadata;

public class RandomAlphaNumericFieldDataEnricher implements Enricher<FieldMetadata, String> {

    private final String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    @Override
    public String enrich(FieldMetadata input, Context context) {
        Random r = new Random();
        char[] ch = new char[Integer.parseInt(input.getLength())];
        for (int i = 0; i < ch.length; i++) {
            ch[i] = alphaNumeric.charAt(r.nextInt(alphaNumeric.length()));
        }
        return new String(ch);
    }
  
}
