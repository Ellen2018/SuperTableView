package com.ellen.tableview.supertableview;

import android.view.View;

import java.util.List;

public class TableClick {

    private View view;
    private int position;
    private int cloumn;
    private int row;
    private List<TableItemView> cloumnViewList;
    private  List<TableItemView> rowViewList;
    /**
     * true:右边部分隐藏
     * false:左边部分隐藏
     * null:没有隐藏
     */
    private Boolean isXPartHide = null;
    /**
     * true:底部部分隐藏
     * false:顶部部分隐藏
     * null:没有隐藏
     */
    private Boolean isYPartHide = null;
    private YItemView yItemView;
    private XItemView xItemView;

    public YItemView getyItemView() {
        return yItemView;
    }

    public void setyItemView(YItemView yItemView) {
        this.yItemView = yItemView;
    }

    public Boolean getXPartHide() {
        return isXPartHide;
    }

    public void setXPartHide(Boolean XPartHide) {
        isXPartHide = XPartHide;
    }

    public Boolean getYPartHide() {
        return isYPartHide;
    }

    public void setYPartHide(Boolean YPartHide) {
        isYPartHide = YPartHide;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getCloumn() {
        return cloumn;
    }

    public void setCloumn(int cloumn) {
        this.cloumn = cloumn;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public List<TableItemView> getCloumnViewList() {
        return cloumnViewList;
    }

    public void setCloumnViewList(List<TableItemView> cloumnViewList) {
        this.cloumnViewList = cloumnViewList;
    }

    public List<TableItemView> getRowViewList() {
        return rowViewList;
    }

    public void setRowViewList(List<TableItemView> rowViewList) {
        this.rowViewList = rowViewList;
    }

    public XItemView getxItemView() {
        return xItemView;
    }

    public void setxItemView(XItemView xItemView) {
        this.xItemView = xItemView;
    }
}
