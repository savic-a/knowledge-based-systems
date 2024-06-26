package com.ftn.sbnz.util;

public class ObjectHolder<T> {
    
    private T object;

    public ObjectHolder() {
    }

    public ObjectHolder(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
    
}
