package com.omnia.common.util.pager;


/**
 * jquery datatable pager util.
 * Created by khaerothe on 2015/5/6.
 */
public class DataTablePager {

    private String draw;
    private String start;
    private String length;

    private PagerSearch search;

    private PagerOrder[] order = new PagerOrder[10];

    private PagerColumn[] column = new PagerColumn[10];

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public PagerSearch getSearch() {
        return search;
    }

    public void setSearch(PagerSearch search) {
        this.search = search;
    }

//    public List<PagerOrder> getOrder() {
//        return order;
//    }
//
//    public void setOrder(List<PagerOrder> order) {
//        this.order = order;
//    }
//
//    public List<PagerColumn> getColumn() {
//        return column;
//    }
//
//    public void setColumn(List<PagerColumn> column) {
//        this.column = column;
//    }

    public PagerOrder[] getOrder() {
        return order;
    }

    public void setOrder(PagerOrder[] order) {
        this.order = order;
    }

    public PagerColumn[] getColumn() {
        return column;
    }

    public void setColumn(PagerColumn[] column) {
        this.column = column;
    }
}


