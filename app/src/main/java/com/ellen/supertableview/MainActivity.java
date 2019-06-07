package com.ellen.supertableview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ellen.tableview.supertableview.adapter.superadapter.SuperTableViewAdapter;
import com.ellen.tableview.supertableview.TableClick;
import com.ellen.tableview.supertableview.TableItemView;
import com.ellen.tableview.supertableview.TableView;

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
        final TableAdapter tableAdapter = new TableAdapter(MainActivity.this);
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
               tableAdapter.addSingleDataRow(new SuperTableViewAdapter.AddYItemCallback() {
                   @Override
                   public void addItemSuccess(int poition, View view) {

                   }

                   @Override
                   public void addYItemSuccess(int row, View yItemView) {

                   }
               });
            }
        });
    }
}
