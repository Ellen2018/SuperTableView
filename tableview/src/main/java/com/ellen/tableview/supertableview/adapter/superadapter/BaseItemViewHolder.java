package com.ellen.tableview.supertableview.adapter.superadapter;

import android.view.View;

public class BaseItemViewHolder {

    public View itemView;

    public BaseItemViewHolder(View itemView){
        this.itemView = itemView;
    }

    public <T extends View> T findViewById(int viewId){
        if(viewId == -1){return null;}
        return itemView.findViewById(viewId);
    }

    public View getItemView() {
        return itemView;
    }
}
