package com.ellen.tableview.supertableview.adapter.superadapter.y;

import android.view.View;

import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.SuperTableAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.XYItemViewHolder;


public abstract class SuperYTableViewAdapter<T extends ItemViewHolder,E extends XYItemViewHolder> extends SuperTableAdapter {

    @Override
    protected XYItemViewHolder createXItemViewHolder(int column, int type) {
        return null;
    }

    @Override
    protected void bindXItemViewHolder(XYItemViewHolder xItemViewHolder, int column) {

    }

    @Override
    protected XYItemViewHolder createYItemViewHolder(int row, int type) {
        E e = createYViewHolder(row);
        return e;
    }

    @Override
    protected void bindYItemViewHolder(XYItemViewHolder yItemViewHolder, int row) {
        bindYViewHolder((E) yItemViewHolder,row);
    }

    @Override
    protected ItemViewHolder createItemViewHolder(int row, int column, int type) {
        T t = createTableItemViewHolder(row,column);
        return t;
    }

    @Override
    protected void bindItemViewHolder(ItemViewHolder itemViewHolder, int row, int column) {
        bindTableItemViewHolder((T) itemViewHolder,row,column);
    }

    @Override
    public void bindAdapter() {
         getTableView().hideXAxis();
         setOrientationV(true);
    }

    @Override
    public View createXYView() {
        return null;
    }

    @Override
    public void bindXYItemView(View view) {

    }

    protected abstract E createYViewHolder(int row);
    protected abstract void bindYViewHolder(E e,int row);
    protected abstract T createTableItemViewHolder(int row,int column);
    protected abstract void bindTableItemViewHolder(T t,int row,int column);
}
