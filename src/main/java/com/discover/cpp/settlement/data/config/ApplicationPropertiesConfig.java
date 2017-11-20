package com.discover.cpp.settlement.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.discover.cpp.settlement.data.common.ApplicationProperties;

@Configuration
@PropertySource("classpath:/application.properties")
public class ApplicationPropertiesConfig {

    @Autowired
    Environment env;

    @Bean
    public ApplicationProperties applicationProperties() {
        ApplicationProperties appProperties = new ApplicationProperties();
        appProperties.setDelimiter(env.getProperty("DELIMITER"));
        appProperties.setNoOfBatches(env.getProperty("NO_OF_BATCHES", Integer.class));
        appProperties.setNoOfDuplicateBatch(env.getProperty("NO_OF_DUPLICATE_BATCH", Integer.class));
        appProperties.setNoOfDuplicateTxnsInBatch(env.getProperty("NO_OF_DUPLICATE_TXN_IN_BATCH", Integer.class));
        appProperties.setNoOfFiles(env.getProperty("NO_OF_FILES", Integer.class));
        appProperties.setNoOfTxnPerBatch(env.getProperty("NO_OF_TXN_PER_BATCH", Integer.class));
        appProperties.setOutFileLocation(env.getProperty("OUT_FILE_LOCATION"));
        return appProperties;
    }
    
}
