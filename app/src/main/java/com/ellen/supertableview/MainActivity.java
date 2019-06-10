package com.ellen.supertableview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ellen.supertableview.fragment.TableFragment;
import com.ellen.tableview.supertableview.adapter.TableViewAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.SuperTableViewAdapter;
import com.ellen.tableview.supertableview.TableClick;
import com.ellen.tableview.supertableview.TableItemView;
import com.ellen.tableview.supertableview.TableView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TableFragment tableFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.framelayout, tableFragment = new TableFragment(), "f1")
                .commit();
        tableFragment.setChooseUpDataCallback(new TableFragment.ChooseUpDataCallback() {

            //选中后在点击item就会回调该方法
            @Override
            public void upData(int row, int column) {
                Log.e("修改(列，行)", "(" + column + "," + row + ")");
            }
        });

        //左边定位到0的位置
        findViewById(R.id.bt_left_dw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableFragment.getTableView().setLeftCloumnPosition(0);
            }
        });

        //右边定位到3的位置
        findViewById(R.id.bt_right_dw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableFragment.getTableView().setRightCloumnPosition(3);
            }
        });

        findViewById(R.id.bt_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableFragment.updateCloumnData(1, new SuperTableViewAdapter.SuperUpdateDataCallback<TableAdapter.MyItemViewHolder>() {
                    @Override
                    public void update(List<TableAdapter.MyItemViewHolder> myItemViewHolders) {
                        //修改列2，行为2的数据为：修改的数据
                        myItemViewHolders.get(1).getTextView().setText("修改的数据");
                    }
                });
            }
        });


    }
}
