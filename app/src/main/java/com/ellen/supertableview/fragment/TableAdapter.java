package com.ellen.supertableview.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ellen.supertableview.R;
import com.ellen.tableview.supertableview.adapter.TableViewAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.XYItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.y.SuperYTableViewAdapter;

import java.util.List;

public class TableAdapter extends SuperYTableViewAdapter<TableAdapter.MyItemViewHolder, TableAdapter.YItemViewHolder> {

    public Context context;
    public List<String> yTitles;
    public List<String> itemTitles;
    public TableAdapter(Context context,List<String> yTitles,List<String> itemTitles){
        this.context = context;
        this.itemTitles = itemTitles;
        this.yTitles = yTitles;
    }

    @Override
    protected YItemViewHolder createYViewHolder(int row) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_line_table_x,null);
        YItemViewHolder yItemViewHolder = new YItemViewHolder(view);
        return yItemViewHolder;
    }

    @Override
    protected void bindYViewHolder(YItemViewHolder yItemViewHolder, int row) {
        yItemViewHolder.tv.setText(yTitles.get(row));
    }

    @Override
    protected MyItemViewHolder createTableItemViewHolder(int row, int column) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_line_table_x,null);
        MyItemViewHolder myItemViewHolder = new MyItemViewHolder(view);
        return myItemViewHolder;
    }

    @Override
    protected void bindTableItemViewHolder(MyItemViewHolder myItemViewHolder, int row, int column) {
         myItemViewHolder.tv.setText(itemTitles.get(column));
    }

    @Override
    public int getTableColumn() {
        return itemTitles.size();
    }

    @Override
    public int getTableRow() {
        return yTitles.size();
    }

    public static class YItemViewHolder extends XYItemViewHolder{

        TextView tv;

        public YItemViewHolder(View yItemView) {
            super(yItemView);
            tv = yItemView.findViewById(R.id.tv);
        }
    }

    public static class MyItemViewHolder extends ItemViewHolder {

        TextView tv;
        public MyItemViewHolder(View yItemView) {
            super(yItemView);
            tv = yItemView.findViewById(R.id.tv);
        }
    }

}
