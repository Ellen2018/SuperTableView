package com.ellen.tableview.supertableview.adapter.superadapter.noxy;

import android.view.View;

import com.ellen.tableview.supertableview.adapter.TableViewAdapter;

public abstract class SuperNoXYTableViewAdapter extends TableViewAdapter {

    @Override
    public void bindAdapter() {
        getTableView().hideXAxis();
        getTableView().hideYAxis();
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
