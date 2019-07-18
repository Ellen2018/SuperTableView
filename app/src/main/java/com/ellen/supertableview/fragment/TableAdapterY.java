package com.ellen.supertableview.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ellen.supertableview.R;
import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.XYItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.y.SuperYTableViewAdapter;

public class TableAdapterY extends SuperYTableViewAdapter<TableAdapterY.Item, TableAdapterY.Y> {

    private Context context;

    public TableAdapterY(Context context){
        this.context = context;
    }

    @Override
    protected Y createYViewHolder(int row) {
        Log.e("执行没,view",""+row);
        View view = LayoutInflater.from(context).inflate(R.layout.item_line_table_x,null);
        Y y = new Y(view);
        return y;
    }

    @Override
    protected void bindYViewHolder(Y y, int row) {
        Log.e("执行没,bind",""+row);
        y.tv.setText("row");
    }

    @Override
    public int getTableRow() {
        return 20;
    }


    @Override
    public int getTableColumn() {
        return 20000;
    }

    @Override
    protected Item createTableItemViewHolder(int row, int column) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_line_table_x,null);
        Item item = new Item(view);
        return item;
    }

    @Override
    protected void bindTableItemViewHolder(Item item, int row, int column) {
        item.tv.setText("("+row+","+column+")");
    }

    static class Y extends XYItemViewHolder{

        TextView tv;

        public Y(View itemView) {
            super(itemView);
            tv = findViewById(R.id.tv);
        }
    }

    static class Item extends ItemViewHolder{

        TextView tv;

        public Item(View itemView) {
            super(itemView);
            tv = findViewById(R.id.tv);
        }
    }

}
