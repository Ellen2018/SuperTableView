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
import com.ellen.tableview.supertableview.PagingMode;
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
    private TableAdapterY superTableViewAdapter;

    private List<String> xTitles = new ArrayList<>();
    private List<String> itemTitles = new ArrayList<>();
    private int aa =20;

    private boolean a = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getActivity()).inflate(R.layout.fragment_table, container, false);
        tableView = view.findViewById(R.id.tableView);
        superTableViewAdapter = new TableAdapterY(getActivity());
        PagingMode pagingMode = new PagingMode(false,50,50,2);
        tableView.setPagingMode(pagingMode);
        tableView.setTableViewAdapter(superTableViewAdapter);
        return view;
    }
}
