package com.ellen.supertableview.table2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ellen.supertableview.R;
import com.ellen.tableview.supertableview.TableClick;
import com.ellen.tableview.supertableview.TableView;

public class Table2Activity extends AppCompatActivity {

    private TableView tableView;
    private RailLineTableAdapter railLineTableAdapter;
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
        tableView.setRowNumber(2);
        railLineTableAdapter = new RailLineTableAdapter(this,yTitles);
        tableView.setTableViewAdapter(railLineTableAdapter);
        tableView.setOnItemClickListener(new TableView.OnItemClickListener() {
            @Override
            public void onClickItem(View view, TableClick tableClick) {
            }

            @Override
            public void onClickYItem(View view, TableClick tableClick) {
                toast("Y:"+tableClick.getRow());
                tableView.setTopRowPosition(tableClick.getRow());
            }

            @Override
            public void onClickXItem(View view, TableClick tableClick) {
                yTitles[3] = "5";
                railLineTableAdapter.notifyChanged();
            }

            @Override
            public void onClickXYView(View view) {
                toast("XY交界处");
            }
        });
    }

    private void toast(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }
}
