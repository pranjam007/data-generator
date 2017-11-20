package com.discover.cpp.settlement.data.generators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.discover.cpp.settlement.data.common.ContextKeys;
import com.discover.cpp.settlement.data.interfaces.Context;
import com.discover.cpp.settlement.data.interfaces.Enricher;
import com.discover.cpp.settlement.data.interfaces.Generator;
import com.discover.cpp.settlement.data.interfaces.Writer;

public class BatchDataGenerator implements Generator<String, Map<Integer, List<String>>> {

    private final TxnDataGenerator txnDataGenerator;
    private final Enricher<Void, String> batchEnricher;
    private final Writer<Map<Integer, List<String>>> writer;
    
    public BatchDataGenerator(TxnDataGenerator txnDataGenerator, Enricher<Void, String> batchEnricher, Writer<Map<Integer, List<String>>> writer) {
        this.txnDataGenerator = txnDataGenerator;
        this.batchEnricher = batchEnricher;
        this.writer = writer;
    }

    @Override
    public Map<Integer, List<String>> generate(String value, Context context) {
        int noOfBatches = context.get(ContextKeys.NO_OF_BATCHES);
        int noOfDuplicateBatches = context.get(ContextKeys.NO_OF_DUPLICATE_BATCH);
        int noOfBatchesToProcess = noOfBatches - noOfDuplicateBatches;
        Map<Integer, List<String>> result = new HashMap<>();
        for (int i = 0; i < noOfBatchesToProcess; i++) {
            StringBuilder batchValue = new StringBuilder();
            batchValue.append(batchEnricher.enrich(null, context));
            batchValue.append(value);
            List<String> txns = txnDataGenerator.generate(batchValue.toString(), context);
            result.put(i, txns);
        }
        if(noOfDuplicateBatches > 0) {
            generateDuplicate(noOfDuplicateBatches, noOfBatchesToProcess, result);
        }
        writer.write(result, context);
        return result;
    }

    private void generateDuplicate(int noOfDuplicateBatches, int noOfBatchesToProcess, Map<Integer, List<String>> result) {
        Random random = new Random();
        for (int i = 0; i < noOfDuplicateBatches; i++) {
            List<String> duplicate = result.get(random.nextInt(noOfBatchesToProcess));
            result.put(noOfBatchesToProcess + i, duplicate);
        }
    }

}
