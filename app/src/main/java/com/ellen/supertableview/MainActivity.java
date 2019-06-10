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

    private TableView tableView;
    private TableFragment tableFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableView = findViewById(R.id.tableView);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.framelayout, tableFragment = new TableFragment(), "f1")
                .commit();
        tableFragment.setChooseUpDataCallback(new TableFragment.ChooseUpDataCallback() {
            @Override
            public void upData(int row, int column) {
                Log.e("修改(列，行)","("+column+","+row+")");
            }
        });
    }
}
