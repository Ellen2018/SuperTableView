package com.ellen.tableview.supertableview.adapter.superadapter.xy;

import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.SuperTableAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.XYItemViewHolder;

public abstract class SuperXYTableViewAdapter<X extends XYItemViewHolder,Y extends XYItemViewHolder,T extends ItemViewHolder> extends SuperTableAdapter {

    @Override
    protected XYItemViewHolder createXItemViewHolder(int column, int type) {
        return createXItemViewHolder(column);
    }

    @Override
    protected void bindXItemViewHolder(XYItemViewHolder xItemViewHolder, int column) {
         bindXViewHolder((X) xItemViewHolder,column);
    }

    @Override
    protected XYItemViewHolder createYItemViewHolder(int row, int type) {
        return createYViewHolder(row);
    }

    @Override
    protected void bindYItemViewHolder(XYItemViewHolder yItemViewHolder, int row) {
         bindYViewHolder((Y) yItemViewHolder,row);
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
    }

    protected abstract T createViewHolder(int row,int column);
    protected abstract void bindViewHolder(T t,int row,int column);
    protected abstract X createXItemViewHolder(int column);
    protected abstract void bindXViewHolder(X x,int column);
    protected abstract Y createYViewHolder(int column);
    protected abstract void bindYViewHolder(Y y,int row);
}
