package com.discover.cpp.settlement.data.common;

import com.discover.cpp.settlement.data.interfaces.ContextKey;

public enum ContextKeys implements ContextKey {
    
    NO_OF_FILES,
    NO_OF_BATCHES,
    NO_OF_TXN_PER_BATCH,
    NO_OF_DUPLICATE_TXN_IN_BATCH,
    NO_OF_DUPLICATE_BATCH,
    CURRENT_DATA_TYPE,
    DELIMITER,
    WRITER,
    OUT_FILE_LOCATION;
}
