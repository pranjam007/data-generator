package com.discover.cpp.settlement.data.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.discover.cpp.settlement.data.common.ContextKeys;
import com.discover.cpp.settlement.data.interfaces.Context;
import com.discover.cpp.settlement.data.interfaces.Enricher;
import com.discover.cpp.settlement.data.interfaces.Generator;

public class TxnDataGenerator implements Generator<String, List<String>> {

    private final Enricher<Void, String> txnEnricher;
    
    public TxnDataGenerator(Enricher<Void, String> txnEnricher) {
        this.txnEnricher = txnEnricher;
    }

    @Override
    public List<String> generate(String value, Context context) {
        int noOfTxns = context.get(ContextKeys.NO_OF_TXN_PER_BATCH);
        int noOfDuplicate = context.get(ContextKeys.NO_OF_DUPLICATE_TXN_IN_BATCH);
        int noOfTxnToProcess = noOfTxns - noOfDuplicate;
        List<String> txns = new ArrayList<>();
        for (int i = 0; i < noOfTxnToProcess; i++) {
            StringBuilder txnLine = new StringBuilder();
            String txn = txnEnricher.enrich(null, context);
            txnLine.append(txn);
            txnLine.append(value.substring(0, value.length() - 1));
            txns.add(txnLine.toString());
        }
        if(noOfDuplicate > 0) {
            generateDuplicate(noOfDuplicate, noOfTxnToProcess, txns);
        }
        return txns;
    }

    private void generateDuplicate(int noOfDuplicate, int noOfTxnToProcess, List<String> txns) {
        Random random = new Random();
        for (int i = 0; i < noOfDuplicate; i++) {
            String duplicate = txns.get(random.nextInt(noOfTxnToProcess));
            txns.add(duplicate);
        }
    }

}
