package com.ellen.supertableview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellen.tableview.view.TableView;
import com.ellen.tableview.view.TableViewAdapter;

public class MainActivity extends AppCompatActivity {

    private TableView tableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableView = findViewById(R.id.tableView);
        tableView.hideYAxis();
        tableView.setTableViewAdapter(new TableViewAdapter() {
            @Override
            public View createItemView(int position, int row, int cloumn) {
                if(row == 1){
                    ImageView imageView = new ImageView(MainActivity.this);
                    imageView.setImageResource(R.mipmap.ic_launcher);
                    return imageView;
                }else {
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(""+position);
                    return textView;
                }
            }

            @Override
            public int getItemCount() {
                return 200;
            }

            @Override
            public void bindView(View view, int position, int row, int cloumn) {

            }

            @Override
            public View createYItemView(int row) {
                return null;
            }

            @Override
            public void bindYItemView(View view, int row) {

            }
        });
    }
}
