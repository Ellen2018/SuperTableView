package com.ellen.tableview.supertableview;

import android.view.View;

public class YItemView {

    private View View;
    private int row = 0;

    public YItemView(android.view.View view, int row) {
        View = view;
        this.row = row;
    }

    public android.view.View getView() {
        return View;
    }

    public int getRow() {
        return row;
    }

}
