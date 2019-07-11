package com.ellen.tableview.supertableview.adapter.superadapter.x;

import android.view.View;
import android.widget.GridLayout;

import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.SuperTableAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.XYItemViewHolder;

public abstract class SuperXTableViewAdapter<T extends ItemViewHolder,E extends XYItemViewHolder> extends SuperTableAdapter {


    @Override
    protected XYItemViewHolder createXItemViewHolder(int column, int type) {
        E e = createXViewHolder(column);
        return e;
    }

    @Override
    protected void bindXItemViewHolder(XYItemViewHolder xItemViewHolder, int column) {
        bindXViewHolder((E) xItemViewHolder,column);
    }

    @Override
    protected XYItemViewHolder createYItemViewHolder(int row, int type) {
        return null;
    }

    @Override
    protected void bindYItemViewHolder(XYItemViewHolder yItemViewHolder, int row) {

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
         getTableView().hideYAxis();
         setOrientationV(false);
    }

    @Override
    public View createXYView() {
        return null;
    }

    @Override
    public void bindXYItemView(View view) {

    }

    protected abstract E createXViewHolder(int column);
    protected abstract void bindXViewHolder(E e,int column);
    protected abstract T createTableItemViewHolder(int row,int column);
    protected abstract void bindTableItemViewHolder(T t,int row,int column);

}
