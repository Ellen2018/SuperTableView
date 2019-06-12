package com.ellen.supertableview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ellen.supertableview.fragment.TableFragment;
import com.ellen.tableview.supertableview.adapter.superadapter.x.SuperXTableViewAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.y.SuperYTableViewAdapter;

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
        findViewById(R.id.bt_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               tableFragment.getSuperTableViewAdapter().addSingleDataRow(new SuperXTableViewAdapter.AddYItemCallback() {
                   @Override
                   public void addItemSuccess(int poition, View view) {

                   }
               });
            }
        });

    }
}
