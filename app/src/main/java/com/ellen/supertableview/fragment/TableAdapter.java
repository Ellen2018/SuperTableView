package com.ellen.supertableview.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ellen.supertableview.R;
import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.XYItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.x.SuperXTableViewAdapter;

import java.util.List;

public class TableAdapter extends SuperXTableViewAdapter<TableAdapter.MyItemViewHolder, TableAdapter.XItemViewHolder> {

    public Context context;
    public List<String> yTitles;
    public List<String> itemTitles;
    public TableAdapter(Context context,List<String> yTitles,List<String> itemTitles){
        this.context = context;
        this.itemTitles = itemTitles;
        this.yTitles = yTitles;
    }

    @Override
    protected XItemViewHolder createXViewHolder(int column) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_line_table_x,null);
        XItemViewHolder xyItemViewHolder = new XItemViewHolder(view);
        return xyItemViewHolder;
    }

    @Override
    protected void bindXViewHolder(XItemViewHolder yItemViewHolder, int column) {
         yItemViewHolder.tv.setText(yTitles.get(column));
    }

    @Override
    protected MyItemViewHolder createTableItemViewHolder(int row, int column) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_line_table_x,null);
        MyItemViewHolder myItemViewHolder = new MyItemViewHolder(view);
        return myItemViewHolder;
    }

    @Override
    protected void bindTableItemViewHolder(MyItemViewHolder myItemViewHolder, int row, int column) {
         myItemViewHolder.tv.setText(column+"");
    }

    @Override
    public int getTableColumn() {
        return yTitles.size();
    }

    @Override
    public int getTableRow() {
        return itemTitles.size();
    }

    public static class XItemViewHolder extends XYItemViewHolder{

        TextView tv;

        public XItemViewHolder(View yItemView) {
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
