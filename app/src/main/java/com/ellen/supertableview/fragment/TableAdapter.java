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
    public List<String> xTitles;
    public int rowNumber = 20;
    public TableAdapter(Context context,List<String> xTitles,List<String> itemTitles){
        this.context = context;
        this.xTitles = xTitles;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    @Override
    protected XItemViewHolder createXViewHolder(int column) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_line_table_x,null);
        XItemViewHolder xyItemViewHolder = new XItemViewHolder(view);
        return xyItemViewHolder;
    }

    @Override
    protected void bindXViewHolder(XItemViewHolder yItemViewHolder, int column) {
    }

    @Override
    protected MyItemViewHolder createTableItemViewHolder(int row, int column) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_line_table_x,null);
        MyItemViewHolder myItemViewHolder = new MyItemViewHolder(view);
        return myItemViewHolder;
    }

    @Override
    protected void bindTableItemViewHolder(MyItemViewHolder myItemViewHolder, int row, int column) {
        myItemViewHolder.tv.setText("("+row+","+column+")");
    }

    @Override
    public int getTableColumn() {
        return 22000;
    }

    @Override
    public int getTableRow() {
        return 5;
    }

    @Override
    public void bindAdapter() {
        super.bindAdapter();
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
