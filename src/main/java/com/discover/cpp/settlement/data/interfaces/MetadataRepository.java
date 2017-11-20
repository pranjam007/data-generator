package com.discover.cpp.settlement.data.interfaces;

import java.util.List;
import java.util.Set;

import com.discover.cpp.settlement.data.metadata.FieldMetadata;

public interface MetadataRepository {

    List<FieldMetadata> list();
    
    Set<String> metadataIdList();
    
    FieldMetadata metadataById(String id);
}
