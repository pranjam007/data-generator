package com.discover.cpp.settlement.data.enricher;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import com.discover.cpp.settlement.data.interfaces.Context;
import com.discover.cpp.settlement.data.interfaces.Enricher;
import com.discover.cpp.settlement.data.metadata.FieldMetadata;

public class RandomAmountFieldDataEnricher implements Enricher<FieldMetadata, String> {

    private final String range = "500000.00";

    @Override
    public String enrich(FieldMetadata input, Context context) {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumIntegerDigits(Integer.parseInt(input.getLength()) - 1);
        format.setGroupingUsed(false);
        format.getMinimumFractionDigits();
        BigDecimal maxRange = new BigDecimal(range);
        BigDecimal randomNumber = new BigDecimal(Math.random());
        BigDecimal actualRandomResult = randomNumber.multiply(maxRange).setScale(2, RoundingMode.HALF_UP);
        return format.format(actualRandomResult);

    }

}
