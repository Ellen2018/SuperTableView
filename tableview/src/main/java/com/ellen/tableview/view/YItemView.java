package com.ellen.tableview.view;

import android.view.View;

public class YItemView {

    private View View;
    private int row = 0;

    public YItemView(android.view.View view, int row) {
        View = view;
        this.row = row;
    }

    public View getView() {
        return View;
    }

    public void setView(View view) {
        View = view;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
