package com.ellen.supertableview.table2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ellen.supertableview.R;
import com.ellen.supertableview.TableAdapter;
import com.ellen.tableview.supertableview.TableClick;
import com.ellen.tableview.supertableview.TableItemView;
import com.ellen.tableview.supertableview.TableView;

import java.util.ArrayList;
import java.util.List;

public class Table2Activity extends AppCompatActivity {

    private TableView tableView;
    private TableAdapter tableAdapter;
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
            "病害",
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table2);
        tableView = findViewById(R.id.tableView);
        tableAdapter = new TableAdapter(this,yTitles);
        tableView.setTableViewAdapter(tableAdapter);
    }
}
