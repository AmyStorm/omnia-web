package com.omnia.common.util.pager;

/**
 * Created by khaerothe on 2015/5/6.
 */
class PagerColumn {
    private String data;
    private String name;
    private String searchable;
    private String orderable;
    private PagerSearch search;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchable() {
        return searchable;
    }

    public void setSearchable(String searchable) {
        this.searchable = searchable;
    }

    public String getOrderable() {
        return orderable;
    }

    public void setOrderable(String orderable) {
        this.orderable = orderable;
    }

    public PagerSearch getSearch() {
        return search;
    }

    public void setSearch(PagerSearch search) {
        this.search = search;
    }
}