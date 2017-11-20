package com.discover.cpp.settlement.data.config;



import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.discover.cpp.settlement.data.interfaces.Writer;
import com.discover.cpp.settlement.data.writer.MapToStringWriter;
import com.discover.cpp.settlement.data.writer.StringWriter;

@Configuration
public class WriterConfig {

    @Bean
    public Writer<String> stringWriter() {
        return new StringWriter();
    }
    
    @Bean
    public Writer<Map<Integer, List<String>>> mapWriter() {
        return new MapToStringWriter();
    }
}
