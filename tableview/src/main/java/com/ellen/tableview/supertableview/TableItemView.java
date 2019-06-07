package com.ellen.tableview.supertableview;

import android.view.View;

public class TableItemView {
    private View view;

    private int cloumn = 0;
    private int row = 0;

    public TableItemView(int row, int cloumn, View view) {
        this.cloumn = cloumn;
        this.row = row;
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
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
}
