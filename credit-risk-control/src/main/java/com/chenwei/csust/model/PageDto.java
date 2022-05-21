package com.chenwei.csust.model;

import java.io.Serializable;

public class PageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer pageNo = 1;

    private Integer pageSize = 10;

    public PageDto() {
    }

    public PageDto(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
