package com.ellen.supertableview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellen.tableview.view.TableClick;
import com.ellen.tableview.view.TableItemView;
import com.ellen.tableview.view.TableView;
import com.ellen.tableview.view.TableViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TableView tableView;
    private TableClick agoTableClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableView = findViewById(R.id.tableView);
        tableView.setOnItemClickListener(new TableView.OnItemClickListener() {
            @Override
            public void onClickItem(View view, TableClick tableClick) {
                if(agoTableClick != null){
                    for(TableItemView tableItemView:agoTableClick.getRowViewList()){
                        tableItemView.getView().setBackgroundColor(Color.WHITE);
                    }
                    if(agoTableClick.getCloumnViewList() != null) {
                        for (TableItemView tableItemView : agoTableClick.getCloumnViewList()) {
                            tableItemView.getView().setBackgroundColor(Color.WHITE);
                        }
                    }
                    agoTableClick.getView().setBackgroundColor(Color.WHITE);
                    agoTableClick.getyItemView().getView().setBackgroundColor(Color.WHITE);
                }
                for(TableItemView tableItemView:tableClick.getRowViewList()){
                    tableItemView.getView().setBackgroundColor(Color.RED);
                }
                for(TableItemView tableItemView:tableClick.getCloumnViewList()){
                    tableItemView.getView().setBackgroundColor(Color.BLUE);
                }
                tableClick.getyItemView().getView().setBackgroundColor(Color.YELLOW);
                view.setBackgroundColor(Color.GREEN);
                agoTableClick = tableClick;
            }

            @Override
            public void onClickYItem(View view, TableClick tableClick) {
                if(agoTableClick != null){
                    for(TableItemView tableItemView:agoTableClick.getRowViewList()){
                        tableItemView.getView().setBackgroundColor(Color.WHITE);
                    }
                    if(agoTableClick.getCloumnViewList() != null) {
                        for (TableItemView tableItemView : agoTableClick.getCloumnViewList()) {
                            tableItemView.getView().setBackgroundColor(Color.WHITE);
                        }
                    }
                    agoTableClick.getView().setBackgroundColor(Color.WHITE);
                    agoTableClick.getyItemView().getView().setBackgroundColor(Color.WHITE);
                }
                tableClick.getyItemView().getView().setBackgroundColor(Color.YELLOW);
                for(TableItemView tableItemView:tableClick.getRowViewList()){
                    tableItemView.getView().setBackgroundColor(Color.RED);
                }
                agoTableClick = tableClick;
            }
        });
        final TableAdapter tableAdapter = new TableAdapter(MainActivity.this);
        //设置索引排序为：垂直方向
        tableAdapter.setOrientationV(true);
        tableView.setTableViewAdapter(tableAdapter);

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableAdapter.updateCloumn(30);
            }
        });

        //左边定位到第10个
        findViewById(R.id.bt_dinwei_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableView.setLeftCloumnPosition(33);
            }
        });

        //右边定位到第10个
        findViewById(R.id.bt_dinwei_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableView.setRightCloumnPosition(20);
            }
        });

        findViewById(R.id.bt_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              List<View> views = new ArrayList<>();
              for(int i=0;i<tableView.getColumnNumber();i++){
                 View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_table,null);
                 views.add(view);
              }
              View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_type_y,null);
              tableAdapter.addDataRow(views,view);
            }
        });
    }
}
