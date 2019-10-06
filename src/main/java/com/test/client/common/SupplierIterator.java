package com.test.client.common;

import java.util.*;
import java.util.function.Consumer;

public class SupplierIterator<T> implements Iterator<T> {

    private Queue<T> cash = new LinkedList<>();

    private ListSupplier<T> supplier;

    public SupplierIterator(ListSupplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = false;
        if(cash.size() > 0){
            hasNext = true;
        }else {
            fillCash();
            if(cash.size() > 0)
                hasNext = true;
        }

        return hasNext;
    }

    @Override
    public T next() {
        return cash.remove();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();

    }

    @Override
    public void forEachRemaining(Consumer action) {
        throw new UnsupportedOperationException();
    }

    private void fillCash(){
        cash.addAll(supplier.getAsList());
    }
}
