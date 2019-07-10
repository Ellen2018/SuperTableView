package com.ellen.supertableview.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ellen.supertableview.R;
import com.ellen.tableview.supertableview.TableClick;
import com.ellen.tableview.supertableview.TableItemView;
import com.ellen.tableview.supertableview.TableView;
import com.ellen.tableview.supertableview.adapter.TableViewAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.ItemViewHolder;
import com.ellen.tableview.supertableview.adapter.superadapter.SuperTableAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.x.SuperXTableViewAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.y.SuperYTableViewAdapter;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class TableFragment extends Fragment {

    private TableView tableView;
    private TableClick agoTableClick;
    private TableAdapter superTableViewAdapter;
    private ChooseUpDataCallback chooseUpDataCallback;

    private List<String> xTitles = new ArrayList<>();
    private List<String> itemTitles = new ArrayList<>();
    private int aa =20;

    private boolean a = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getActivity()).inflate(R.layout.fragment_table, container, false);
        tableView = view.findViewById(R.id.tableView);
        superTableViewAdapter = new TableAdapter(getActivity(), xTitles, itemTitles);
        superTableViewAdapter.setRowNumber(aa);
        tableView.setTableViewAdapter(superTableViewAdapter);
        view.findViewById(R.id.bt_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               superTableViewAdapter.notifyChanged();
            }
        });
        initView();
        return view;
    }

    private void initView() {

        tableView.setOnItemClickListener(new TableView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClickItem(View view, TableClick tableClick) {
                Toast.makeText(getActivity(), "(" + tableClick.getRow() + "," + tableClick.getCloumn() + ")", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickYItem(View view, TableClick tableClick) {
                Toast.makeText(getActivity(), "点击了Y：" + tableClick.getRow(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickXItem(View view, TableClick tableClick) {
                Toast.makeText(getActivity(), "点击了X：" + tableClick.getCloumn(), Toast.LENGTH_SHORT).show();
                for (TableItemView tableItemView : tableClick.getCloumnViewList()) {
                    tableItemView.getView().setBackgroundColor(Color.RED);
                }
                view.setBackgroundColor(Color.RED);
            }

            @Override
            public void onClickXYView(View view) {

            }
        });

    }

    public void setChooseUpDataCallback(ChooseUpDataCallback chooseUpDataCallback) {
        this.chooseUpDataCallback = chooseUpDataCallback;
    }

    public TableView getTableView() {
        return tableView;
    }

    public interface ChooseUpDataCallback {
        void upData(int row, int column);
    }
}
