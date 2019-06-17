package com.ellen.tableview.supertableview.adapter.superadapter.noxy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.ellen.tableview.supertableview.adapter.TableViewAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;

public abstract class SuperNoXYTableViewAdapter<T extends ItemViewHolder> extends TableViewAdapter {

    private Context context;
    public SuperNoXYTableViewAdapter(Context context){
        this.context = context;
    }

    public Context getContext(){
        return context;
    }

    @Override
    public void bindAdapter() {
        getTableView().hideXAxis();
        getTableView().hideYAxis();
    }

    protected abstract int getItemLayoutId();
    protected abstract T createItemViewHolder(View view,int row,int column);
    protected abstract void bindItemViewHolder(T t,int row,int column);

    @Override
    public View createItemView(int position, int row, int column) {
        View view = LayoutInflater.from(getContext()).inflate(getItemLayoutId(),null);
        T t = createItemViewHolder(view,row,column);
        bindItemViewHolder(t,row,column);
        return view;
    }

    @Override
    public void bindView(View view, int finalIndex, int row, int column) {

    }

    @Override
    public View createYItemView(int row) {
        return null;
    }

    @Override
    public void bindYItemView(View view, int row) {

    }

    @Override
    public View createXItemView(int column) {
        return null;
    }

    @Override
    public void bindXItemView(View view, int column) {

    }

    @Override
    public View createXYView() {
        return null;
    }

    @Override
    public void bindXYItemView(View view) {

    }
}
