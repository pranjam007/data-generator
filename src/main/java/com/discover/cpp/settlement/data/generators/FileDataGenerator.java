package com.discover.cpp.settlement.data.generators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.discover.cpp.settlement.data.common.ContextKeys;
import com.discover.cpp.settlement.data.interfaces.Context;
import com.discover.cpp.settlement.data.interfaces.Enricher;
import com.discover.cpp.settlement.data.interfaces.Generator;
import com.discover.cpp.settlement.data.interfaces.Writer;

public class FileDataGenerator implements Generator<Void, Map<Integer, Map<Integer, List<String>>>> {

    private final BatchDataGenerator batchDataGenerator;

    private final Enricher<Void, String> fileEnricher;
    
    private final Writer<String> writer;

    public FileDataGenerator(BatchDataGenerator batchDataGenerator, Enricher<Void, String> fileEnricher, Writer<String> writer) {
        this.batchDataGenerator = batchDataGenerator;
        this.fileEnricher = fileEnricher;
        this.writer = writer;
    }

    @Override
    public Map<Integer, Map<Integer, List<String>>> generate(Void value, Context context) {
        int noOfFiles = context.get(ContextKeys.NO_OF_FILES);
        String fileHeader = fileEnricher.enrich(null, context);
        Map<Integer, Map<Integer, List<String>>> file = new HashMap<>();
        for (int i = 0; i < noOfFiles; i++) {
            Map<Integer, List<String>> result = batchDataGenerator.generate(fileHeader, context);
            file.put(i, result);
        }
        writer.close(context);
        return file;
    }

}
