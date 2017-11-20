package com.discover.cpp.settlement.data.enricher;

import java.util.List;
import java.util.Map;

import com.discover.cpp.settlement.data.common.ContextKeys;
import com.discover.cpp.settlement.data.interfaces.Context;
import com.discover.cpp.settlement.data.interfaces.Enricher;
import com.discover.cpp.settlement.data.metadata.FieldMetadata;

public class FileEnricher implements Enricher<Void, String> {

    private final List<FieldMetadata> fileMetadata;

    private final Map<String, Enricher<FieldMetadata, String>> availableEnrichers;

    public FileEnricher(List<FieldMetadata> fileMetadata,
            Map<String, Enricher<FieldMetadata, String>> availableEnrichers) {
        this.fileMetadata = fileMetadata;
        this.availableEnrichers = availableEnrichers;
    }

    @Override
    public String enrich(Void input, Context context) {
        StringBuilder sb = new StringBuilder();
        String delimiter = context.get(ContextKeys.DELIMITER);
        fileMetadata.forEach(fm -> {
            if(availableEnrichers.get(fm.getEnricher()) != null)
                sb.append(availableEnrichers.get(fm.getEnricher()).enrich(fm, context));
            sb.append(delimiter);
        });
        return sb.toString();
    }

}
