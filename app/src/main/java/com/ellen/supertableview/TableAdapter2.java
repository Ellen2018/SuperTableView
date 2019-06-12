package com.ellen.supertableview;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.XYItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.x.SuperXTableViewAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.y.SuperYTableViewAdapter;

public class TableAdapter2 extends SuperXTableViewAdapter<TableAdapter2.MyItemViewHolder, TableAdapter2.MyXItemViewHolder> {


    public TableAdapter2(Context context) {
        super(context);
    }

    @Override
    protected int getxItemLayoutId() {
        return R.layout.item_line_table_x;
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_table;
    }

    @Override
    public MyItemViewHolder onCreateItemViewHolder(View itemView, int position, int row, int column) {
        return null;
    }

    @Override
    public void onBindItemViewHolder(MyItemViewHolder myItemViewHolder, int position, int row, int column) {

    }

    @Override
    public MyXItemViewHolder onCreateXItemViewHolder(View xItemView, int column) {
        return null;
    }

    @Override
    public void onBindXItemViewHolder(MyXItemViewHolder t, int column) {

    }

    public static class MyXItemViewHolder extends XYItemViewHolder {

        TextView textView;

        public MyXItemViewHolder(View yItemView) {
            super(yItemView);
            textView = yItemView.findViewById(R.id.table_y_title);
        }
    }

    public static class MyItemViewHolder extends ItemViewHolder{

        private TextView textView;

        public TextView getTextView() {
            return textView;
        }

        public MyItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.table_text);
        }
    }

}
