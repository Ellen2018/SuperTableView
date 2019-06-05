package com.ellen.tableview.view;

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
}
