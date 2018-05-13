package com.mybatis.util;

/**
 * Created by renwujie on 2018/05/13 at 16:35
 */
public class Pager {
    private int pageIndex;
    private int pageOffSet;
    private int pageSize;
    private String order;
    private String orderWay;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageOffSet() {
        pageOffSet = (pageIndex - 1) * pageSize;
        return pageOffSet;
    }

    public void setPageOffSet(int pageOffSet) {
        this.pageOffSet = pageOffSet;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderWay() {
        return orderWay;
    }

    public void setOrderWay(String orderWay) {
        this.orderWay = orderWay;
    }
}
