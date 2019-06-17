package com.ellen.supertableview.table2;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ellen.supertableview.R;
import com.ellen.tableview.supertableview.TableItemView;
import com.ellen.tableview.supertableview.adapter.TableViewAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RailLineTableAdapter extends TableViewAdapter {

    private Context context;

    private View getView(int layoutId){
        View view = LayoutInflater.from(context).inflate(layoutId,null);
        return view;
    }

    public RailLineTableAdapter(Context context){
        this.context = context;
    }

    @Override
    public View createItemView(int position, int row, int column) {
        View view = null;
        if(row == 0) {
           view = getView(R.layout.item_line_table_x);
        }else {
            if(column == 0){
                view = getView(R.layout.item_line_table_column_1);
            }else if(column == 1){
                view = getView(R.layout.item_line_table_column_2);
            }else {
                view = getView(R.layout.item_line_table_column_3);
                if(column == 6 || column == 7){
                    TextView textView = view.findViewById(R.id.textview);
                    textView.setBackgroundResource(R.drawable.line_table_item_bg_column_red);
                    textView.setTextColor(Color.WHITE);
                }
            }
        }
        return view;
    }

    @Override
    public void bindAdapter() {

    }

    @Override
    public void bindView(View view, int finalIndex, int row, int column) {

    }

    @Override
    public View createYItemView(int row) {
        return getView(R.layout.item_type_y);
    }

    @Override
    public void bindYItemView(View view, int row) {
      TextView textView = view.findViewById(R.id.table_y_title);
      textView.setText(row+"");
    }

    @Override
    public View createXItemView(int column) {
        return getView(R.layout.item_line_table_x);
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

    public void updateRow(int row,RefreshRowCallback refreshRowCallback){
        List<TableItemView> tableItemViewList = getTableView().getMapRow().get(row);
        for(TableItemView tableItemView:tableItemViewList){
            if(tableItemView.getCloumn() == 0){
                refreshRowCallback.columnOneAdd(new ColumnOneViewHolder(tableItemView.getView()),tableItemView.getRow(),tableItemView.getCloumn());
            }else if(tableItemView.getCloumn() == 1){
                refreshRowCallback.columnTwoAdd(new ColumnTwoViewHolder(tableItemView.getView()),tableItemView.getRow(),tableItemView.getCloumn());
            }else {
                refreshRowCallback.columnThreeAdd(new ColumnThreeViewHolder(tableItemView.getView()),tableItemView.getRow(),tableItemView.getCloumn());
            }
        }
    }

    public void addRow(RefreshRowCallback refreshRowCallback){
        List<View> viewList = new ArrayList<>();
        for(int i=0;i<getTableView().getColumnNumber();i++){
            View view = null;
            if(i == 0){
                view = getView(R.layout.item_line_table_column_1);
                refreshRowCallback.columnOneAdd(new ColumnOneViewHolder(view),getTableView().getRowNumber(),i);
            }else if(i == 1){
                view = getView(R.layout.item_line_table_column_2);
                refreshRowCallback.columnTwoAdd(new ColumnTwoViewHolder(view),getTableView().getRowNumber(),i);
            }else {
                view = getView(R.layout.item_line_table_column_3);
                refreshRowCallback.columnThreeAdd(new ColumnThreeViewHolder(view),getTableView().getRowNumber(),i);
            }
            viewList.add(view);
        }
        addDataRow(viewList,getView(R.layout.item_line_table_column_1));
    }

    public interface  RefreshRowCallback{
         void columnOneAdd(ColumnOneViewHolder columnOneViewHolder,int row,int column);
         void columnTwoAdd(ColumnTwoViewHolder columnTwoViewHolder,int row,int column);
         void columnThreeAdd(ColumnThreeViewHolder columnThreeViewHolder,int row,int column);
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
