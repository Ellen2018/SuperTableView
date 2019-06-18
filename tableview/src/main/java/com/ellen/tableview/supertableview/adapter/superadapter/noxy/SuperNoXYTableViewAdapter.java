package com.ellen.tableview.supertableview.adapter.superadapter.noxy;

import android.view.View;

import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.SuperTableAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.XYItemViewHolder;

public abstract class SuperNoXYTableViewAdapter<T extends ItemViewHolder> extends SuperTableAdapter {

    @Override
    protected XYItemViewHolder createXItemViewHolder(int column, int type) {
        return null;
    }

    @Override
    protected void bindXItemViewHolder(XYItemViewHolder xItemViewHolder, int column) {

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
        T t = createViewHolder(row,column);
        return t;
    }

    @Override
    protected void bindItemViewHolder(ItemViewHolder itemViewHolder, int row, int column) {
        bindViewHolder((T) itemViewHolder,row,column);
    }

    @Override
    public void bindAdapter() {
        getTableView().hideYAxis();
        getTableView().hideXAxis();
    }

    @Override
    public View createXYView() {
        return null;
    }

    @Override
    public void bindXYItemView(View view) {

    }

    protected abstract T createViewHolder(int row,int column);
    protected abstract void bindViewHolder(T t,int row,int column);
}
