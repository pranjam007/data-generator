package com.discover.cpp.settlement.data.interfaces;

public interface Writer<T> {
    void write(T value, Context context);
    void close(Context context);
}
