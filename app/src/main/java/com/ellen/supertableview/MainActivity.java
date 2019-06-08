package com.ellen.supertableview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ellen.tableview.supertableview.adapter.TableViewAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.SuperTableViewAdapter;
import com.ellen.tableview.supertableview.TableClick;
import com.ellen.tableview.supertableview.TableItemView;
import com.ellen.tableview.supertableview.TableView;

import java.util.List;

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
                if (agoTableClick != null) {
                    for (TableItemView tableItemView : agoTableClick.getCloumnViewList()) {
                        if (tableItemView.getRow() == 0) continue;
                        TextView textView = tableItemView.getView().findViewById(R.id.table_text);
                        textView.setBackgroundResource(R.drawable.item_bg_white);
                        textView.setTextColor(Color.BLACK);
                    }
                }

                int cloumn = tableClick.getCloumn();
                int resorce = R.drawable.item_bg_red;
                switch (cloumn){
                    case 0:
                        resorce = R.drawable.item_bg_red;
                        break;
                    case 1:
                        resorce = R.drawable.item_bg_blue;
                        break;
                    case 2:
                        resorce = R.drawable.item_bg_yellow;
                        break;
                    case 3:
                        resorce = R.drawable.item_bg_green;
                        break;

                }

                for (TableItemView tableItemView : tableClick.getCloumnViewList()) {
                    if (tableItemView.getRow() == 0) continue;
                    TextView textView = tableItemView.getView().findViewById(R.id.table_text);
                    textView.setTextColor(Color.WHITE);
                    textView.setBackgroundResource(resorce);
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

        findViewById(R.id.bt_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableAdapter.updateCloumnData(0, new TableViewAdapter.UpdateDataCallback() {
                    @Override
                    public void update(List<TableItemView> tableItemViewList) {
                        for (int i = 0; i < tableItemViewList.size(); i++) {
                            if (i == 0) continue;
                            TextView textView = tableItemViewList.get(i).getView().findViewById(R.id.table_text);
                            textView.setText(i + "");
                            switch (i){
                                case 1:
                                    textView.setText("前顺坡终点");
                                    break;
                                case 2:
                                    textView.setText("转撤");
                                    break;
                                case 3:
                                    textView.setText("3");
                                    break;
                                case 4:
                                    textView.setText("2");
                                    break;
                                case 5:
                                    textView.setText("x");
                                    break;
                                case 6:
                                    textView.setText("x");
                                    break;
                                case 7:
                                    textView.setText("x");
                                    break;
                                case 8:
                                    textView.setText("");
                                    break;
                                case 9:
                                    textView.setText("");
                                    break;
                            }
                        }
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

        findViewById(R.id.bt_left_dinwei).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableView.setLeftCloumnPosition(1);
            }
        });

        findViewById(R.id.bt_right_dinwei).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableView.setRightCloumnPosition(3);
            }
        });


    }
}
