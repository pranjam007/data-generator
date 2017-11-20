package com.discover.cpp.settlement.data.enricher;

import java.util.List;
import java.util.Map;

import com.discover.cpp.settlement.data.common.ContextKeys;
import com.discover.cpp.settlement.data.interfaces.Context;
import com.discover.cpp.settlement.data.interfaces.Enricher;
import com.discover.cpp.settlement.data.metadata.FieldMetadata;

public class BatchEnricher implements Enricher<Void, String> {

    private final List<FieldMetadata> batchMetadata;

    private final Map<String, Enricher<FieldMetadata, String>> availableEnrichers;

    public BatchEnricher(List<FieldMetadata> batchMetadata,
            Map<String, Enricher<FieldMetadata, String>> availableEnrichers) {
        this.batchMetadata = batchMetadata;
        this.availableEnrichers = availableEnrichers;
    }

    @Override
    public String enrich(Void input, Context context) {
        StringBuilder sb = new StringBuilder();
        String delimiter = context.get(ContextKeys.DELIMITER);
        batchMetadata.forEach(bm -> {
            if(availableEnrichers.get(bm.getEnricher()) != null)
                sb.append(availableEnrichers.get(bm.getEnricher()).enrich(bm, context));
            sb.append(delimiter);
        });
        return sb.toString();
    }

}
