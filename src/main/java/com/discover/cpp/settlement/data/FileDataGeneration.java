package com.discover.cpp.settlement.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.discover.cpp.settlement.data.common.ApplicationProperties;
import com.discover.cpp.settlement.data.common.ContextKeys;
import com.discover.cpp.settlement.data.common.MapBasedContext;
import com.discover.cpp.settlement.data.config.ApplicationPropertiesConfig;
import com.discover.cpp.settlement.data.config.EnricherConfig;
import com.discover.cpp.settlement.data.config.FieldMetadataConfig;
import com.discover.cpp.settlement.data.config.GeneratorConfig;
import com.discover.cpp.settlement.data.config.MetadataConfig;
import com.discover.cpp.settlement.data.config.WriterConfig;
import com.discover.cpp.settlement.data.generators.FileDataGenerator;
import com.discover.cpp.settlement.data.interfaces.Context;

public class FileDataGeneration {

    public static void main(String[] args) throws Exception {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(EnricherConfig.class,
                FieldMetadataConfig.class, GeneratorConfig.class, MetadataConfig.class, WriterConfig.class,
                ApplicationPropertiesConfig.class);

        FileDataGenerator fdg = appContext.getBean(FileDataGenerator.class);
        String fileName = "generatedData_";
        Context context = new MapBasedContext();
        loadProperties(appContext, context);
        int noOfFiles = context.get(ContextKeys.NO_OF_FILES);
        for (int i = 0; i < noOfFiles; i++) {
            fileName = context.get(ContextKeys.OUT_FILE_LOCATION) + fileName + i +".csv";
            Writer fileWriter = new BufferedWriter(new FileWriter(new File(fileName)));
            context.put(ContextKeys.WRITER, fileWriter);
            fdg.generate(null, context);
        }

    }

    private static void loadProperties(ApplicationContext appContext, Context context) {
        ApplicationProperties appProperties = appContext.getBean(ApplicationProperties.class);
        context.put(ContextKeys.NO_OF_FILES, appProperties.getNoOfFiles());
        context.put(ContextKeys.NO_OF_BATCHES, appProperties.getNoOfBatches());
        context.put(ContextKeys.NO_OF_TXN_PER_BATCH, appProperties.getNoOfTxnPerBatch());
        context.put(ContextKeys.DELIMITER, appProperties.getDelimiter());
        context.put(ContextKeys.OUT_FILE_LOCATION, appProperties.getOutFileLocation());
        context.put(ContextKeys.NO_OF_DUPLICATE_BATCH, appProperties.getNoOfDuplicateBatch());
        context.put(ContextKeys.NO_OF_DUPLICATE_TXN_IN_BATCH, appProperties.getNoOfDuplicateTxnsInBatch());
    }
}
