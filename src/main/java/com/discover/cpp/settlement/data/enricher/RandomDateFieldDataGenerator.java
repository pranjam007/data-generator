package com.discover.cpp.settlement.data.enricher;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import com.discover.cpp.settlement.data.interfaces.Context;
import com.discover.cpp.settlement.data.interfaces.Enricher;
import com.discover.cpp.settlement.data.metadata.FieldMetadata;

public class RandomDateFieldDataGenerator implements Enricher<FieldMetadata, String> {

    private long currentTime;
    
    private long weeksTime;
    
    public RandomDateFieldDataGenerator() {
        this.currentTime = Timestamp.valueOf(LocalDateTime.now()).getTime();
        this.weeksTime = Timestamp.valueOf(LocalDateTime.now().minusDays(7)).getTime();
    }
    
    @Override
    public String enrich(FieldMetadata input, Context context) {
        SimpleDateFormat format = new SimpleDateFormat(input.getFormat());
        Date randomDate = new Date(getRandomTime());
        return format.format(randomDate);
    }

    private long getRandomTime() {
        long difference = currentTime - weeksTime+ 1;
        return weeksTime + (long) (Math.random() * difference);
    }
}
