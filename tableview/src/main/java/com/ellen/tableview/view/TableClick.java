package com.ellen.tableview.view;

import android.view.View;

import java.util.List;

public class TableClick {

    private View view;
    private int position;
    private int cloumn;
    private int row;
    private List<TableItemView> cloumnTextViewList;
    private  List<TableItemView> rowTextViewList;
    private boolean isPartHide = false;
    private YItemView yItemView;

    public YItemView getyItemView() {
        return yItemView;
    }

    public void setyItemView(YItemView yItemView) {
        this.yItemView = yItemView;
    }

    public boolean isPartHide() {
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

    public List<TableItemView> getCloumnTextViewList() {
        return cloumnTextViewList;
    }

    public void setCloumnTextViewList(List<TableItemView> cloumnTextViewList) {
        this.cloumnTextViewList = cloumnTextViewList;
    }

    public List<TableItemView> getRowTextViewList() {
        return rowTextViewList;
    }

    public void setRowTextViewList(List<TableItemView> rowTextViewList) {
        this.rowTextViewList = rowTextViewList;
    }

}
