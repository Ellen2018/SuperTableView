package com.ellen.supertableview.table2;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ellen.supertableview.R;
import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.XYItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.xy.SuperXYTableViewAdapter;

import java.util.List;

public class RailLineTableAdapter extends SuperXYTableViewAdapter<RailLineTableAdapter.XViewHolder,
        RailLineTableAdapter.YViewHolder, RailLineTableAdapter.MyItemViewHolder> {

    private Context context;
    private List<String> xList;
    private List<String> yList;

    public RailLineTableAdapter(Context context, List<String> xList,List<String> yList){
        this.context = context;
        this.xList = xList;
        this.yList = yList;
    }

    @Override
    protected MyItemViewHolder createViewHolder(int row, int column) {
        return new MyItemViewHolder(getViewByLayoutId(context,R.layout.item_table));
    }

    @Override
    protected void bindViewHolder(MyItemViewHolder myItemViewHolder, int row, int column) {
       myItemViewHolder.textView.setText("("+row+","+column+")");
    }

    @Override
    protected XViewHolder createXItemViewHolder(int column) {
        return new XViewHolder(getViewByLayoutId(context,R.layout.item_line_table_x));
    }

    @Override
    protected void bindXViewHolder(XViewHolder xViewHolder, int column) {
        xViewHolder.textView.setText(xList.get(column));
    }

    @Override
    protected YViewHolder createYViewHolder(int column) {
        return new YViewHolder(getViewByLayoutId(context,R.layout.item_type_y));
    }

    @Override
    protected void bindYViewHolder(YViewHolder yViewHolder, int row) {
        yViewHolder.textView.setText(yList.get(row));
    }

    @Override
    public int getTableRow() {
        return yList.size();
    }

    @Override
    public int getTableColumn() {
        return xList.size();
    }

    @Override
    public View createXYView() {
        return getViewByLayoutId(context,R.layout.item_xy);
    }

    @Override
    public void bindXYItemView(View view) {

    }

    static class XViewHolder extends XYItemViewHolder{
        TextView textView;
        public XViewHolder(View itemView) {
            super(itemView);
            textView = findViewById(R.id.tv);
        }
    }

    static class YViewHolder extends XYItemViewHolder{
        TextView textView;
        public YViewHolder(View itemView) {
            super(itemView);
            textView= findViewById(R.id.table_y_title);
        }
    }

    static class MyItemViewHolder extends ItemViewHolder {
        TextView textView;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            textView = findViewById(R.id.table_text);
        }
    }

}
