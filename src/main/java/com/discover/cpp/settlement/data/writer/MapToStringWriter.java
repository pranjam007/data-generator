package com.discover.cpp.settlement.data.writer;

import java.util.List;
import java.util.Map;

import com.discover.cpp.settlement.data.common.ContextKeys;
import com.discover.cpp.settlement.data.interfaces.Context;
import com.discover.cpp.settlement.data.interfaces.Writer;

public class MapToStringWriter implements Writer<Map<Integer, List<String>>> {

    private java.io.Writer writer;

    @Override
    public void write(Map<Integer, List<String>> value, Context context) {
        try {
            writer = context.get(ContextKeys.WRITER);
            value.entrySet().forEach(m -> {
                m.getValue().forEach(this::write);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private void write(String value) {
        try {
            writer.write(value);
            writer.write(System.getProperty("line.separator"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
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
