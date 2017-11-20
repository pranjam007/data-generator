package com.discover.cpp.settlement.data.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.discover.cpp.settlement.data.generators.BatchDataGenerator;
import com.discover.cpp.settlement.data.generators.FileDataGenerator;
import com.discover.cpp.settlement.data.generators.TxnDataGenerator;
import com.discover.cpp.settlement.data.interfaces.Enricher;
import com.discover.cpp.settlement.data.interfaces.Writer;

@Configuration
public class GeneratorConfig {

    @Autowired
    Writer<String> stringWriter;
    
    @Autowired
    @Qualifier("mapWriter")
    Writer<Map<Integer, List<String>>> mapWriter;
    
    @Autowired
    @Qualifier("fileEnricher")
    Enricher<Void, String> fileEnricher;
    
    @Autowired
    @Qualifier("batchEnricher")
    Enricher<Void, String> batchEnricher;
    
    @Autowired
    @Qualifier("txnEnricher")
    Enricher<Void, String> txnEnricher;
    
    @Bean
    public FileDataGenerator fileDataGenerator() {
        return new FileDataGenerator(batchDataGenerator(), fileEnricher, stringWriter);
    }
    
    @Bean
    public BatchDataGenerator batchDataGenerator() {
        return new BatchDataGenerator(txnDataGenerator(), batchEnricher, mapWriter);
    }
    
    @Bean
    public TxnDataGenerator txnDataGenerator() {
        return new TxnDataGenerator(txnEnricher);
    }
}
