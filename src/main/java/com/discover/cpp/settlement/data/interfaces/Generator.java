package com.discover.cpp.settlement.data.interfaces;

public interface Generator<I, O> {

    O generate(I value, Context context);
}
