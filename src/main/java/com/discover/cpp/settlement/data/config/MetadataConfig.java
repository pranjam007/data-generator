package com.discover.cpp.settlement.data.config;

import java.io.File;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.discover.cpp.settlement.data.interfaces.MetadataRepository;
import com.discover.cpp.settlement.data.metadata.FieldMetadataRepository;

@Configuration
public class MetadataConfig {
    
    @Bean
    public MetadataRepository metadata() throws IOException {
        return new FieldMetadataRepository(getInputStream());
    }
    
    private File getInputStream() throws IOException {
        Resource resource = new ClassPathResource("/metadata.json");
        File file = resource.getFile();
        return file;
    }
}
