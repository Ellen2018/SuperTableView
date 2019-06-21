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

public class Table2Activity extends AppCompatActivity implements View.OnClickListener {

    private TableView tableView;
    private RailLineTableAdapter railLineTableAdapter;
    private List<String> xList;
    private List<String> yList;
    private Button btAdd, btDelete, btUpdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table2);
        tableView = findViewById(R.id.tableView);
        btAdd = findViewById(R.id.bt1);
        btDelete = findViewById(R.id.bt2);
        btUpdate = findViewById(R.id.bt3);
        btAdd.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        xList = new ArrayList<>();
        yList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            xList.add("x" + i);
        }
        for (int i = 0; i < 1; i++) {
            yList.add("y" + i);
        }
        railLineTableAdapter = new RailLineTableAdapter(this, xList, yList);
        tableView.setTableViewAdapter(railLineTableAdapter);
        tableView.setOnItemClickListener(new TableView.OnItemClickListener() {
            @Override
            public void onClickItem(View view, TableClick tableClick) {
                Toast.makeText(Table2Activity.this,"点击了"+tableClick.getCloumn(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickYItem(View view, TableClick tableClick) {
            }

            @Override
            public void onClickXItem(View view, TableClick tableClick) {
                
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                xList.add("3");
                xList.add("5");
                if(xList.size()>10){
                    yList.clear();
                }
                break;
            case R.id.bt2:
                xList.remove(0);
                break;
            case R.id.bt3:
                xList.set(xList.size()-1,"5");
                break;
        }
        railLineTableAdapter.notifyChanged();
    }
}
