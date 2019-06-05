package com.ellen.tableview.view;

import android.view.View;

public abstract class TableViewAdapter {

    public abstract View createItemView(int position,int row,int cloumn);
    public abstract int getItemCount();
    public abstract void bindView(View view,int position,int row,int cloumn);
    public abstract View createYItemView(int row);
    public abstract void bindYItemView(View view,int row);

}
