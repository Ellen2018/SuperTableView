package com.ellen.supertableview.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ellen.supertableview.R;
import com.ellen.supertableview.TableAdapter;
import com.ellen.tableview.supertableview.TableClick;
import com.ellen.tableview.supertableview.TableItemView;
import com.ellen.tableview.supertableview.TableView;
import com.ellen.tableview.supertableview.adapter.superadapter.SuperTableViewAdapter;

@SuppressLint("ValidFragment")
public class TableFragment extends Fragment {

    private TableView tableView;
    private TableClick agoTableClick;
    private SuperTableViewAdapter superTableViewAdapter;
    private ChooseUpDataCallback chooseUpDataCallback;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getActivity()).inflate(R.layout.fragment_table, container, false);
        tableView = view.findViewById(R.id.tableView);
        superTableViewAdapter = new TableAdapter(getActivity(),yTitles);
        superTableViewAdapter.setOrientationV(true);
        tableView.setTableViewAdapter(superTableViewAdapter);
        initView();
        return view;
    }

    private void initView() {

        tableView.setOnItemClickListener(new TableView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClickItem(View view, TableClick tableClick) {
                //检测是否需要滑动
                if(tableClick.isPartHide()){
                    tableView.setRightCloumnPosition(tableClick.getCloumn());
                }

                if(tableClick.getRow() == 0) {
                    if (agoTableClick != null) {
                        for (TableItemView tableItemView : agoTableClick.getCloumnViewList()) {
                            if (tableItemView.getRow() == 0) continue;
                            TextView textView = tableItemView.getView().findViewById(R.id.table_text);
                            if (tableItemView.getRow() >= 2 && tableItemView.getRow() <= 6) {
                                textView.setBackgroundResource(R.drawable.table_item_bg_gray);
                            } else {
                                textView.setBackgroundResource(R.drawable.table_item_bg_white);
                            }
                            if(tableItemView.getRow()>=2 && tableItemView.getRow()<=6){
                                textView.setTextColor(Color.parseColor("#474747"));
                            }else {
                                textView.setTextColor(Color.BLACK);
                            }
                        }
                    }
                    int cloumn = tableClick.getCloumn();
                    int resorce = 0;
                    switch (cloumn) {
                        case 0:
                            resorce = R.drawable.table_item_bg_column_1;
                            break;
                        case 1:
                            resorce = R.drawable.table_item_bg_column_2;
                            break;
                        case 2:
                            resorce = R.drawable.table_item_bg_column_3;
                            break;
                        case 3:
                            resorce = R.drawable.table_item_bg_column_4;
                            break;

                    }

                    for (TableItemView tableItemView : tableClick.getCloumnViewList()) {
                        if (tableItemView.getRow() == 0) continue;
                        TextView textView = tableItemView.getView().findViewById(R.id.table_text);
                        textView.setTextColor(Color.WHITE);
                        textView.setBackgroundResource(resorce);
                    }
                    agoTableClick = tableClick;
                }else {
                    if(!(tableClick.getRow() >=2 && tableClick.getRow()<=6)){
                        chooseUpDataCallback.upData(tableClick.getRow(),tableClick.getCloumn());
                    }
                }
            }

            @Override
            public void onClickYItem(View view, TableClick tableClick) {

            }
        });

    }

    public void setChooseUpDataCallback(ChooseUpDataCallback chooseUpDataCallback) {
        this.chooseUpDataCallback = chooseUpDataCallback;
    }

    public void updateCloumnData(int column, SuperTableViewAdapter.SuperUpdateDataCallback<TableAdapter.MyItemViewHolder> superUpdateDataCallback) {
        superTableViewAdapter.superUpdateCloumnData(column,superUpdateDataCallback);
    }

    public TableView getTableView() {
        return tableView;
    }

    public interface ChooseUpDataCallback{
        void upData(int row,int column);
    }
}
