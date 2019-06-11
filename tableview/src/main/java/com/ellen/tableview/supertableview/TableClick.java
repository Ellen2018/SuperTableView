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
    private Boolean isPartHide = null;
    private YItemView yItemView;

    public YItemView getyItemView() {
        return yItemView;
    }

    public void setyItemView(YItemView yItemView) {
        this.yItemView = yItemView;
    }

    public Boolean isPartHide() {
        return isPartHide;
    }

    public void setPartHide(boolean partHide) {
        isPartHide = partHide;
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
}
