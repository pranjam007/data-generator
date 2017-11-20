package com.discover.cpp.settlement.data.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.discover.cpp.settlement.data.enricher.BatchEnricher;
import com.discover.cpp.settlement.data.enricher.CounterBasedFieldDataEnricher;
import com.discover.cpp.settlement.data.enricher.FileEnricher;
import com.discover.cpp.settlement.data.enricher.RandomAlphaNumericFieldDataEnricher;
import com.discover.cpp.settlement.data.enricher.RandomAmountFieldDataEnricher;
import com.discover.cpp.settlement.data.enricher.RandomDateFieldDataGenerator;
import com.discover.cpp.settlement.data.enricher.RandomNumericFieldDataEnricher;
import com.discover.cpp.settlement.data.enricher.TxnEnricher;
import com.discover.cpp.settlement.data.interfaces.Enricher;
import com.discover.cpp.settlement.data.metadata.FieldMetadata;

@Configuration
public class EnricherConfig {

    @Autowired
    @Qualifier("batchMetadata")
    private List<FieldMetadata> batchMetadata;

    @Autowired
    @Qualifier("txnMetadata")
    private List<FieldMetadata> txnMetadata;

    @Autowired
    @Qualifier("fileMetadata")
    private List<FieldMetadata> fileMetadata;

    @Bean
    Enricher<Void, String> fileEnricher() {
        return new FileEnricher(fileMetadata, availableEnrichers());
    }

    @Bean
    Enricher<Void, String> batchEnricher() {
        return new BatchEnricher(batchMetadata, availableEnrichers());
    }

    @Bean
    Enricher<Void, String> txnEnricher() {
        return new TxnEnricher(txnMetadata, availableEnrichers());
    }

    private Map<String, Enricher<FieldMetadata, String>> availableEnrichers() {
        Map<String, Enricher<FieldMetadata, String>> result = new HashMap<>();
        result.put("CounterBasedFieldDataEnricher", new CounterBasedFieldDataEnricher());
        result.put("RandomAlphaNumericFieldDataEnricher", new RandomAlphaNumericFieldDataEnricher());
        result.put("RandomNumericFieldDataEnricher", new RandomNumericFieldDataEnricher());
        result.put("RandomDateFieldDataGenerator", new RandomDateFieldDataGenerator());
        result.put("RandomAmountFieldDataEnricher", new RandomAmountFieldDataEnricher());
        return result;
    }
}
