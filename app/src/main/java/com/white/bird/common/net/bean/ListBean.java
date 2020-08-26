package com.white.bird.common.net.bean;

import java.util.List;

/**
 * author : ZYK
 * createTime   : 2020/8/26 15:12
 * function   :
 */
public class ListBean<T> {
    private String perPageSize;
    private String next;
    private String totalItem;
    private String toPage;
    private String totalPage;
    List<T> list;

    public String getPerPageSize() {
        return perPageSize;
    }

    public String getNext() {
        return next;
    }

    public String getTotalItem() {
        return totalItem;
    }

    public String getToPage() {
        return toPage;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public List<T> getList() {
        return list;
    }
}
