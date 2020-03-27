/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx.util;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 2019/1/8.
 */
public class PageData<T> implements Serializable {
    private int draw;
    private int recordTotal;
    private int recordsFiltered;
    private int pageNo;
    private int pageSize;
    private List<T> data;

    public int getPageSize() {
        return pageSize;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public PageData(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return pageNo > 0 ? (pageNo-1) * pageSize : 0;
    }
}
