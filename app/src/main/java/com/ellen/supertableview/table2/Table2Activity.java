package com.ellen.supertableview.table2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ellen.supertableview.R;
import com.ellen.tableview.supertableview.TableClick;
import com.ellen.tableview.supertableview.TableView;

import java.util.ArrayList;
import java.util.List;

public class Table2Activity extends AppCompatActivity{

    private TableView tableView;
    private RailLineTableAdapter railLineTableAdapter;
    private List<String> xList;
    private List<String> yList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table2);
        tableView = findViewById(R.id.tableView);
        xList = new ArrayList<>();
        yList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            xList.add("x" + i);
        }
        for (int i = 0; i < 10; i++) {
            yList.add("y" + i);
        }
        railLineTableAdapter = new RailLineTableAdapter(this, xList, yList);
        tableView.setTableViewAdapter(railLineTableAdapter);
        tableView.setOnItemClickListener(new TableView.OnItemClickListener() {
            @Override
            public void onClickItem(View view, TableClick tableClick) {
                Toast.makeText(Table2Activity.this,"点击了("+tableClick.getRow()+","+tableClick.getCloumn()+")",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickYItem(View view, TableClick tableClick) {
                toast("Y:"+tableClick.getRow());
                yList.set(tableClick.getRow(),"点击Y:"+tableClick.getRow());
                railLineTableAdapter.notifyChanged();
            }

            @Override
            public void onClickXItem(View view, TableClick tableClick) {
                toast("X:"+tableClick.getCloumn());
                xList.set(tableClick.getCloumn(),"点击X:"+tableClick.getCloumn());
                railLineTableAdapter.notifyChanged();
            }

            @Override
            public void onClickXYView(View view) {
                toast("XY交界处");
            }
        });
    }

    private void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}
