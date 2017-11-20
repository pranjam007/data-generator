package com.discover.cpp.settlement.data.interfaces;

public interface Enricher<I, O> {

    O enrich(I input, Context context);
}
