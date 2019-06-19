package com.ellen.supertableview.table2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ellen.supertableview.R;
import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.SuperTableAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.XYItemViewHolder;

public class RailLineTableAdapter extends SuperTableAdapter {

    private Context context;
    private String[] xTitles;

    public RailLineTableAdapter(Context context,String[] xTitles){
        this.context = context;
        this.xTitles = xTitles;
    }

    @Override
    protected XYItemViewHolder createXItemViewHolder(int column, int type) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_line_table_x,null);
        return new XViewHolder(view);
    }

    @Override
    protected void bindXItemViewHolder(XYItemViewHolder xItemViewHolder, int column) {
        XViewHolder xViewHolder = (XViewHolder) xItemViewHolder;
        xViewHolder.textView.setText(xTitles[column]);
    }

    @Override
    protected XYItemViewHolder createYItemViewHolder(int row, int type) {
        return null;
    }

    @Override
    protected void bindYItemViewHolder(XYItemViewHolder yItemViewHolder, int row) {

    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getTableRow() {
        return 8;
    }

    @Override
    protected int getItemType(int row, int column) {
        if(column == 0){
            return 0;
        }else if(column == 1){
            return 1;
        }else {
            return 2;
        }
    }

    @Override
    protected ItemViewHolder createItemViewHolder(int row, int column, int type) {
        if(type == 0){
            return new ColumnOneViewHolder(LayoutInflater.from(context).inflate(R.layout.item_line_table_column_1,null));
        }else if(type == 1){
            return new ColumnTwoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_line_table_column_2,null));
        }else if(type == 2){
            return new ColumnThreeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_line_table_column_3,null));
        }else {
            return null;
        }
    }

    @Override
    protected void bindItemViewHolder(ItemViewHolder itemViewHolder, int row, int column) {

    }

    @Override
    public void bindAdapter() {
        getTableView().hideYAxis();
    }

    @Override
    public View createXYView() {
        return null;
    }

    @Override
    public void bindXYItemView(View view) {

    }

    public static class XViewHolder extends XYItemViewHolder{
        TextView textView;
        public XViewHolder(View yItemView) {
            super(yItemView);
            textView = yItemView.findViewById(R.id.tv);
        }
    }

    public static class ColumnOneViewHolder extends ItemViewHolder {

        private TextView textView;

        public ColumnOneViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public static class ColumnTwoViewHolder extends ItemViewHolder {

        private TextView textViewLeft;
        private TextView textViewRight;

        public ColumnTwoViewHolder(View itemView) {
            super(itemView);
            textViewLeft = itemView.findViewById(R.id.textview_left);
            textViewRight = itemView.findViewById(R.id.textview_right);
        }

        public TextView getTextViewLeft() {
            return textViewLeft;
        }

        public TextView getTextViewRight() {
            return textViewRight;
        }
    }

    public static class ColumnThreeViewHolder extends ItemViewHolder {

        private TextView textView;

        public ColumnThreeViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
