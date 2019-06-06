package com.ellen.supertableview;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ellen.tableview.view.TableViewAdapter;

public class TableAdapter extends TableViewAdapter {

    private Context context;

    public TableAdapter(Context context){
        this.context = context;
    }

    @Override
    public View createItemView(int position, int row, int cloumn) {
       View view = LayoutInflater.from(context).inflate(R.layout.item_table,null);
        return view;
    }

    @Override
    public void bindView(View view, int finalIndex, int row, int cloumn) {
       TextView textView = view.findViewById(R.id.table_text);
       textView.setGravity(Gravity.CENTER);
       textView.setText(finalIndex+"");
    }

    @Override
    public View createYItemView(int row) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type_y,null);
        return view;
    }

    @Override
    public void bindYItemView(View view, int row) {

    }
}
