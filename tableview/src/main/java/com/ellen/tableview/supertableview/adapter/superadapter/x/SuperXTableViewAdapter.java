package com.ellen.tableview.supertableview.adapter.superadapter.x;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.ellen.tableview.supertableview.TableItemView;
import com.ellen.tableview.supertableview.adapter.TableViewAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.XYItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class SuperXTableViewAdapter<T extends ItemViewHolder,E extends XYItemViewHolder> extends TableViewAdapter {

    private Context context;

    public SuperXTableViewAdapter(Context context){
        this.context = context;
    }

    @Override
    public void bindAdapter() {
        getTableView().hideYAxis();
    }

    private View getView(int layoutId){
        View view = LayoutInflater.from(context).inflate(layoutId,null);
        return view;
    }

    @Override
    public View createItemView(int position, int row, int cloumn) {
        View itemView = getView(getItemLayoutId());
        T t = onCreateItemViewHolder(itemView,position,row,cloumn);
        onBindItemViewHolder(t,position,row,position);
        return itemView;
    }

    @Override
    public void bindView(View view, int finalIndex, int row, int cloumn) {

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
        View xItemView = getView(getxItemLayoutId());
        E e= onCreateXItemViewHolder(xItemView,column);
        onBindXItemViewHolder(e,column);
        return xItemView;
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

    protected abstract int getxItemLayoutId();
    protected abstract int getItemLayoutId();

    public abstract T onCreateItemViewHolder(View itemView,int position,int row,int column);
    public abstract void onBindItemViewHolder(T t,int position,int row,int column);

    public abstract E onCreateXItemViewHolder(View xItemView,int column);
    public abstract void onBindXItemViewHolder(E t,int column);

    public void addSingleDataColumn(AddItemCallback addItemCallback){
        List<View> views = new ArrayList<>();
        for(int i=0;i<getTableView().getRowNumber();i++){
            View addItemView = getView(getItemLayoutId());
            views.add(addItemView);
            addItemCallback.addItemSuccess(i,addItemView);
        }
        View xItemView = getView(getxItemLayoutId());
        addItemCallback.addXItemSuccess(getTableView().getColumnNumber()-1,xItemView);
        addDataColumn(views,xItemView);
    }

    public void addSingleDataRow(AddYItemCallback addYItemCallback){
        List<View> views = new ArrayList<>();
        for(int i=0;i<getTableView().getColumnNumber();i++){
            View addItemView = getView(getItemLayoutId());
            views.add(addItemView);
            addYItemCallback.addItemSuccess(i,addItemView);
        }
        addDataRow(views,null);
    }

    public interface AddItemCallback{
        void addItemSuccess(int poition, View view);
        void addXItemSuccess(int column, View yItemView);
    }

    public interface AddYItemCallback{
        void addItemSuccess(int poition, View view);
    }

    public Context getContext(){
        return context;
    }

    public void superUpdateCloumnData(final int column, final SuperUpdateDataCallback superUpdateDataCallback) {
        final List<T> tList = new ArrayList<>();
        super.updateCloumnData(column, new UpdateDataCallback() {
            @Override
            public void update(List<TableItemView> tableItemViewList) {
                for(TableItemView tableItemView:tableItemViewList){
                    T t = onCreateItemViewHolder(tableItemView.getView(),0,tableItemView.getRow(),column);
                    tList.add(t);
                }
                superUpdateDataCallback.update(tList);
            }
        });
    }

    public void superUpdateRowData(final int row, final SuperUpdateDataCallback superUpdateDataCallback){
        final List<T> tList = new ArrayList<>();
        super.updateRowData(row, new UpdateDataCallback() {
            @Override
            public void update(List<TableItemView> tableItemViewList) {
                for(TableItemView tableItemView:tableItemViewList){
                    T t = onCreateItemViewHolder(tableItemView.getView(),0,tableItemView.getRow(),row);
                    tList.add(t);
                }
                superUpdateDataCallback.update(tList);
            }
        });
    }

    public interface SuperUpdateDataCallback<T extends ItemViewHolder>{
        void update(List<T> tList);
    }
}
