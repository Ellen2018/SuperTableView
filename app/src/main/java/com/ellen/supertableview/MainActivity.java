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

import com.ellen.tableview.view.SuperTableViewAdapter;
import com.ellen.tableview.view.TableClick;
import com.ellen.tableview.view.TableItemView;
import com.ellen.tableview.view.TableView;
import com.ellen.tableview.view.TableViewAdapter;
import com.ellen.tableview.view.YItemView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TableView tableView;
    private TableClick agoTableClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableView = findViewById(R.id.tableView);
        //tableView.hideYAxis();
        tableView.setOnItemClickListener(new TableView.OnItemClickListener() {
            @Override
            public void onClickItem(View view, TableClick tableClick) {
                if(agoTableClick != null){
                    for(TableItemView tableItemView:agoTableClick.getCloumnViewList()){

                            tableItemView.getView().setBackgroundColor(Color.WHITE);

                    }
                }
               int column = tableClick.getCloumn();
               int color = 0;
               switch (column){
                   case 0:
                       color = Color.RED;
                       break;
                   case 1:
                       color = Color.BLUE;
                       break;
                   case 2:
                       color = Color.YELLOW;
                       break;
                   case 3:
                       color = Color.GREEN;
                       break;
               }

               for(TableItemView tableItemView:tableClick.getCloumnViewList()){
                   tableItemView.getView().setBackgroundColor(color);
               }
               agoTableClick = tableClick;
            }

            @Override
            public void onClickYItem(View view, TableClick tableClick) {

            }
        });
        final TableAdapter tableAdapter = new TableAdapter(MainActivity.this,R.layout.item_type_y,R.layout.item_table);
        //设置索引排序为：垂直方向
        tableAdapter.setOrientationV(true);
        tableView.setTableViewAdapter(tableAdapter);

        findViewById(R.id.bt_add_h).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               tableAdapter.addSingleDataColumn(new SuperTableViewAdapter.AddItemCallback() {
                   @Override
                   public void addItemSuccess(int poition, View view) {

                   }
               });
            }
        });

        findViewById(R.id.bt_add_v).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableView.setRightCloumnPosition(3);
            }
        });
    }
}
