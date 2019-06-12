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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table2);
        tableView = findViewById(R.id.tableView_line);
        railLineTableAdapter = new RailLineTableAdapter(this);
        tableView.hideYAxis();
        tableView.setTableViewAdapter(railLineTableAdapter);
        railLineTableAdapter.updateRow(1, new RailLineTableAdapter.RefreshRowCallback() {

            //第一列会回调此方法
            @Override
            public void columnOneAdd(RailLineTableAdapter.ColumnOneViewHolder columnOneViewHolder, int row, int column) {
                columnOneViewHolder.getTextView().setText("修改");
            }

            //第二列会回调此方法
            @Override
            public void columnTwoAdd(RailLineTableAdapter.ColumnTwoViewHolder columnTwoViewHolder, int row, int column) {
                columnTwoViewHolder.getTextViewLeft().setText("200m");
                columnTwoViewHolder.getTextViewRight().setVisibility(View.GONE);
            }

            //第二列之后的列会回调此方法
            @Override
            public void columnThreeAdd(RailLineTableAdapter.ColumnThreeViewHolder columnThreeViewHolder, int row, int column) {

            }
        });
    }
}
