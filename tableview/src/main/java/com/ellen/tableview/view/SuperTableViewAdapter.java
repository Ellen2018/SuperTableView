package com.ellen.tableview.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public abstract class SuperTableViewAdapter<T extends ItemViewHolder,E extends YItemViewHolder> extends TableViewAdapter {

    private int yItemLayoutId;
    private int itemLayoutId;
    private Context context;

    public SuperTableViewAdapter(Context context,int yItemLayoutId, int itemLayoutId){
        this.context = context;
        this.itemLayoutId = itemLayoutId;
        this.yItemLayoutId = yItemLayoutId;
    }

    private View getView(int layoutId){
        View view = LayoutInflater.from(context).inflate(layoutId,null);
        return view;
    }

    @Override
    public View createItemView(int position, int row, int cloumn) {
        View itemView = getView(itemLayoutId);
        T t = onCreateItemViewHolder(itemView,position,row,cloumn);
        onBindItemViewHolder(t,position,row,position);
        return itemView;
    }

    @Override
    public void bindView(View view, int finalIndex, int row, int cloumn) {

    }

    @Override
    public View createYItemView(int row) {
        View yItemView = getView(yItemLayoutId);
        E e= onCreateYItemViewHolder(yItemView,row);
        onBindYItemViewHolder(e,row);
        return yItemView;
    }

    @Override
    public void bindYItemView(View view, int row) {

    }

    public abstract T onCreateItemViewHolder(View itemView,int position,int row,int column);
    public abstract void onBindItemViewHolder(T t,int position,int row,int column);

    public abstract E onCreateYItemViewHolder(View yItemView,int row);
    public abstract void onBindYItemViewHolder(E t,int row);

    public void addSingleDataColumn(AddItemCallback addItemCallback){
        List<View> views = new ArrayList<>();
        for(int i=0;i<getTableView().getRowNumber();i++){
            View addItemView = getView(itemLayoutId);
            views.add(addItemView);
            addItemCallback.addItemSuccess(i,addItemView);
        }
        addDataColumn(views);
    }

    public void addSingleDataRow(AddYItemCallback addYItemCallback){
        List<View> views = new ArrayList<>();
        for(int i=0;i<getTableView().getColumnNumber();i++){
            View addItemView = getView(itemLayoutId);
            views.add(addItemView);
            addYItemCallback.addItemSuccess(i,addItemView);
        }
        View yItemView = getView(yItemLayoutId);
        addDataRow(views,yItemView);
        addYItemCallback.addYItemSuccess(getTableView().getRowNumber()-1,yItemView);
    }

    public interface AddItemCallback{
        void addItemSuccess(int poition,View view);
    }

    public interface AddYItemCallback{
        void addItemSuccess(int poition,View view);
        void addYItemSuccess(int row,View yItemView);
    }
}
