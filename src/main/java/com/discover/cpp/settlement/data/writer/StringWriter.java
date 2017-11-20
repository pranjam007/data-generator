package com.discover.cpp.settlement.data.writer;

import com.discover.cpp.settlement.data.common.ContextKeys;
import com.discover.cpp.settlement.data.interfaces.Context;
import com.discover.cpp.settlement.data.interfaces.Writer;

public class StringWriter implements Writer<String> {

    private java.io.Writer writer;
    
    @Override
    public void write(String value, Context context) {
        try {
            writer = context.get(ContextKeys.WRITER);
            writer.write(value);
            writer.write(System.getProperty("line.separator"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
     
    }
    
    public void close(Context context) {
        try {
            writer = context.get(ContextKeys.WRITER);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
