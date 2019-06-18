package com.ellen.tableview.supertableview.adapter.superadapter;

import android.view.View;

public class XYItemViewHolder {

    private View xyItemView;

    public XYItemViewHolder(View xyItemView){
        this.xyItemView = xyItemView;
    }

    public View getItemView() {
        return xyItemView;
    }
}
