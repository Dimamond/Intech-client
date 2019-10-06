package com.test.client.common;

import java.util.List;

@FunctionalInterface
public interface ListSupplier<T> {

    List<T> getAsList();
}
