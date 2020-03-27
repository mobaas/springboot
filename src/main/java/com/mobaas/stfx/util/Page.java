/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.util;

/**
 * Created by root on 2019/1/8.
 */
public class Page<T> {
    public T t;
    private int offest;
    private int limit;

    public Page(T t, int offest, int limit) {
        this.t = t;
        this.offest = offest;
        this.limit = limit;
    }

    public T getT() {
        return t;
    }

    public int getOffest() {
        return offest;
    }

    public int getLimit() {
        return limit;
    }
}
