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
import android.widget.Toast;

import com.ellen.supertableview.R;
import com.ellen.tableview.supertableview.TableClick;
import com.ellen.tableview.supertableview.TableItemView;
import com.ellen.tableview.supertableview.TableView;
import com.ellen.tableview.supertableview.adapter.superadapter.y.SuperYTableViewAdapter;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class TableFragment extends Fragment {

    private TableView tableView;
    private TableClick agoTableClick;
    private SuperYTableViewAdapter superTableViewAdapter;
    private ChooseUpDataCallback chooseUpDataCallback;

    private List<String> yTitles = new ArrayList<>();
    private List<String> itemTitles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getActivity()).inflate(R.layout.fragment_table, container, false);
        tableView = view.findViewById(R.id.tableView);
        yTitles.add("1");
        yTitles.add("2");
        yTitles.add("3");
        yTitles.add("4");
        yTitles.add("5");
        yTitles.add("6");
        yTitles.add("7");
        itemTitles.add("a");
        tableView.setRowNumber(yTitles.size());
        tableView.setColumnNumber(itemTitles.size());
        superTableViewAdapter = new TableAdapter(getActivity(), yTitles,itemTitles);
        tableView.setTableViewAdapter(superTableViewAdapter);
        initView();
        return view;
    }

    private void initView() {

        tableView.setOnItemClickListener(new TableView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClickItem(View view, TableClick tableClick) {

            }

            @Override
            public void onClickYItem(View view, TableClick tableClick) {
                superTableViewAdapter.addRow();
            }

            @Override
            public void onClickXItem(View view, TableClick tableClick) {
               for(TableItemView tableItemView:tableClick.getCloumnViewList()){
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
