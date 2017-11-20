package com.discover.cpp.settlement.data.enricher;

import java.util.List;
import java.util.Map;

import com.discover.cpp.settlement.data.common.ContextKeys;
import com.discover.cpp.settlement.data.interfaces.Context;
import com.discover.cpp.settlement.data.interfaces.Enricher;
import com.discover.cpp.settlement.data.metadata.FieldMetadata;

public class TxnEnricher implements Enricher<Void, String> {

    private final List<FieldMetadata> txnMetadata;

    private final Map<String, Enricher<FieldMetadata, String>> availableEnrichers;

    public TxnEnricher(List<FieldMetadata> txnMetadata,
            Map<String, Enricher<FieldMetadata, String>> availableEnrichers) {
        this.txnMetadata = txnMetadata;
        this.availableEnrichers = availableEnrichers;
    }

    @Override
    public String enrich(Void input, Context context) {
        StringBuilder sb = new StringBuilder();
        String delimiter = context.get(ContextKeys.DELIMITER);
        txnMetadata.forEach(tm -> {
            if(availableEnrichers.get(tm.getEnricher()) != null)
                sb.append(availableEnrichers.get(tm.getEnricher()).enrich(tm, context));
            sb.append(delimiter);
        });
        return sb.toString();
    }

}
