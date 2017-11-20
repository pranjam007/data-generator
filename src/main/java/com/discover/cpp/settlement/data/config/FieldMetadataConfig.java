package com.discover.cpp.settlement.data.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.discover.cpp.settlement.data.common.DataTypes;
import com.discover.cpp.settlement.data.interfaces.MetadataRepository;
import com.discover.cpp.settlement.data.metadata.FieldMetadata;

@Configuration
public class FieldMetadataConfig {

    @Autowired
    MetadataRepository metadata;

    @Bean
    public List<FieldMetadata> txnMetadata() {
        return populateMetadata(DataTypes.TXN);
    }

    @Bean
    public List<FieldMetadata> batchMetadata() {
        return populateMetadata(DataTypes.BATCH);
    }

    @Bean
    public List<FieldMetadata> fileMetadata() {
        return populateMetadata(DataTypes.FILE);
    }

    private List<FieldMetadata> populateMetadata(DataTypes dataType) {
        List<FieldMetadata> fields = metadata.list();
        return fields.stream().filter(field -> dataType.getName().equals(field.getDataType()))
                .collect(Collectors.toList());
    }
}
