package com.ellen.tableview.supertableview;

import android.view.View;

public class XItemView {

    private View View;
    private int column = 0;

    public XItemView(android.view.View view, int column) {
        View = view;
        this.column = column;
    }

    public View getView() {
        return View;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column){
        this.column = column;
    }
}
