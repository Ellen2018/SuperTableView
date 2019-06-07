package com.ellen.supertableview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.SuperTableViewAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.YItemViewHolder;

public class TableAdapter extends SuperTableViewAdapter<TableAdapter.MyItemViewHolder, TableAdapter.MyYItemViewHolder> {

    private String[] yTitles = {
            "状态",
            "检查位置",
            "部位",
            "轨距",
            "水平",
            "查照",
            "护背",
            "轨向",
            "高低",
            "病害"
    };

    public TableAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getyItemLayoutId() {
        return R.layout.item_type_y;
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_table;
    }

    @Override
    public MyItemViewHolder onCreateItemViewHolder(View itemView, int position, int row, int column) {
        return new MyItemViewHolder(itemView);
    }

    @Override
    public void onBindItemViewHolder(MyItemViewHolder myItemViewHolder, int position, int row, int column) {
        if(row == 0){
            switch (column){
                case 0:
                    myItemViewHolder.textView.setBackgroundColor(Color.RED);
                    myItemViewHolder.textView.setText("已测量");
                    break;
                case 1:
                    myItemViewHolder.textView.setBackgroundColor(Color.BLUE);
                    myItemViewHolder.textView.setText("测量中");
                    break;
                case 2:
                    myItemViewHolder.textView.setBackgroundColor(Color.YELLOW);
                    myItemViewHolder.textView.setText("已跳过");
                    break;
                case 3:
                    myItemViewHolder.textView.setBackgroundColor(Color.GREEN);
                    myItemViewHolder.textView.setText("已上传");
                    break;
            }
        }
    }

    @Override
    public MyYItemViewHolder onCreateYItemViewHolder(View yItemView, int row) {
        return new MyYItemViewHolder(yItemView);
    }

    @Override
    public void onBindYItemViewHolder(MyYItemViewHolder myYItemViewHolder, int row) {
       myYItemViewHolder.textView.setText(yTitles[row]);
    }

    static class MyYItemViewHolder extends YItemViewHolder{

        TextView textView;

        public MyYItemViewHolder(View yItemView) {
            super(yItemView);
            textView = yItemView.findViewById(R.id.table_y_title);
        }
    }

    static class MyItemViewHolder extends ItemViewHolder{

        private TextView textView;

        public MyItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.table_text);
        }
    }
}
