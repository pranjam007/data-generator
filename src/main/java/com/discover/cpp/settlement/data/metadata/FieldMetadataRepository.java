package com.discover.cpp.settlement.data.metadata;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.discover.cpp.settlement.data.interfaces.MetadataRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FieldMetadataRepository implements MetadataRepository {

    private Map<String, FieldMetadata> metadataMap;
    private List<FieldMetadata> metadatas;
    
    public FieldMetadataRepository(File file) {
        populateFieldMetadata(file);
    }
    
    private void populateFieldMetadata(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            metadatas = mapper.readValue(file, new TypeReference<List<FieldMetadata>>(){});
            metadataMap = metadatas.stream().collect(Collectors.toMap(FieldMetadata::getId, m -> m));
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<FieldMetadata> list() {
        return metadatas;
    }

    @Override
    public Set<String> metadataIdList() {
        return metadataMap.keySet();
    }

    @Override
    public FieldMetadata metadataById(String id) {
        return metadataMap.get(id);
    }

}
