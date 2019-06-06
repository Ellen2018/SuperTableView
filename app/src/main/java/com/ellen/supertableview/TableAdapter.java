package com.ellen.supertableview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ellen.tableview.view.TableViewAdapter;

import java.util.HashMap;
import java.util.Map;

public class TableAdapter extends TableViewAdapter {

    public int count = 10000;
    private Context context;
    Map<Integer,View> map;

    public TableAdapter(Context context){
        this.context = context;
        map = new HashMap<>();
    }

    @Override
    public View createItemView(int position, int row, int cloumn) {
        if(map.get(position) != null){
            return map.get(position);
        }
        TextView textView = new TextView(context);
        map.put(position,textView);
        return textView;
    }

    @Override
    public int getItemCount() {
        return count;
    }

    @Override
    public void bindView(View view, int finalIndex, int row, int cloumn) {
       TextView textView = (TextView) view;
       textView.setText(finalIndex+"");
    }

    @Override
    public View createYItemView(int row) {
        return null;
    }

    @Override
    public void bindYItemView(View view, int row) {

    }
}
