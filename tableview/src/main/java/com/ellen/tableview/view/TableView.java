package com.ellen.tableview.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ellen.tableview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableView extends RelativeLayout {

    private GridLayout gridLayoutTable;
    private GridLayout gridLayoutY;
    //用于列定位
    private HorizontalScrollView horizontalScrollView;
    //用于行定位
    private ScrollView scrollView;
    //item点击事件
    private OnItemClickListener onItemClickListener;
    //列集合
    private Map<Integer, List<TableItemView>> mapColumn;
    //行集合
    private Map<Integer, List<TableItemView>> mapRow;
    //Y轴Item集合
    private Map<Integer,YItemView> mapYItem;
    //适配器
    private TableViewAdapter tableViewAdapter;

    //列数
    private int columnNumber = 10;
    //行数
    private int rowNumber = 10;
    //item宽度
    private int itemWidth = 100;
    //item高度
    private int itemHeight = 100;
    //y轴宽度
    private int yWidth = 100;
    //item实时个数
    private int itemCount = 0;
    //行数 -> Y
    private int rowY = -1;

    public TableView(Context context) {
        super(context);
        initView();
    }

    public TableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TableView);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.TableView_ItemWidth) {
                float width = typedArray.getDimension(attr, this.itemWidth);
                this.itemWidth = (int) width;
            } else if (attr == R.styleable.TableView_ItemHeight) {
                float height = typedArray.getDimension(attr, this.itemHeight);
                this.itemHeight = (int) height;
            } else if (attr == R.styleable.TableView_ColumnCount) {
                this.columnNumber = typedArray.getInteger(attr, this.columnNumber);
                gridLayoutTable.setColumnCount(columnNumber);
                gridLayoutY.setColumnCount(1);
            } else if (attr == R.styleable.TableView_RowCount) {
                this.rowNumber = typedArray.getInteger(attr, this.rowNumber);
                gridLayoutTable.setRowCount(rowNumber);
                gridLayoutY.setRowCount(rowNumber);
            }else if(attr == R.styleable.TableView_YItemWidth){
                yWidth = (int) typedArray.getDimension(attr, 100);// 控件的宽强制设成30;
                LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams)gridLayoutY.getLayoutParams();
                linearParams.height = LayoutParams.MATCH_PARENT;// 控件的高强制设成20
                linearParams.width = yWidth;
                gridLayoutY.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
            }
        }
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
        gridLayoutTable.setColumnCount(columnNumber);
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
        gridLayoutTable.setRowCount(columnNumber);
    }

    public int getItemWidth() {
        return itemWidth;
    }

    public void setItemWidth(int itemWidth) {
        this.itemWidth = itemWidth;
    }

    public int getItemHeight() {
        return itemHeight;
    }

    public void setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setTableViewAdapter(TableViewAdapter tableViewAdapter){
        this.tableViewAdapter = tableViewAdapter;
        for(int i=0;i<tableViewAdapter.getItemCount();i++){
            itemCount++;
            final int row = getRow();
            final int column = getColumn();
            View view = tableViewAdapter.createItemView(i,row,column);
            addItem(view);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_table, this);
        gridLayoutTable = view.findViewById(R.id.grid_layout);
        gridLayoutY = view.findViewById(R.id.grid_layout_y);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        mapColumn = new HashMap<>();
        mapRow = new HashMap<>();
        mapYItem = new HashMap<>();
    }

    public void addTextViewItem(String itemString) {
        TextView textView = new TextView(getContext());
        textView.setText(itemString);
        textView.setGravity(Gravity.CENTER);
        addItem(textView);
    }

    public void setTableTitleY(List<String> stringList){
        for(String title:stringList){
            setTableTitleY(title);
        }
    }

    private void setTableTitleY(String str) {
        rowY++;
        TextView textView = new TextView(getContext());
        textView.setText(str);
        textView.setWidth(yWidth);
        textView.setGravity(Gravity.CENTER);
        final YItemView yItemView = new YItemView(textView,rowY);
        mapYItem.put(rowY,yItemView);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
              if(onItemClickListener != null){
                  TableClick tableClick = new TableClick();
                  tableClick.setyItemView(yItemView);
                  tableClick.setRowTextViewList(mapRow.get(yItemView.getRow()));
                  onItemClickListener.onClickYItem(v,tableClick);
              }
            }
        });
        gridLayoutY.addView(textView,itemWidth,itemHeight);
    }

    private int getRow(){
        return  (itemCount - 1) / columnNumber;
    }

    private int getColumn(){
        return (((itemCount - 1) - getRow() * columnNumber) % columnNumber);
    }

    public void addItem(final View view) {
        final int row = getRow();
        final int column = getColumn();
        Log.e("行数", row + "");
        Log.e("列数", column + "");

        TableItemView tableItemView = new TableItemView(row, column, view);
        if (mapColumn.get(column) == null) {
            List<TableItemView> cloumnTextViewList = new ArrayList<>();
            cloumnTextViewList.add(tableItemView);
            mapColumn.put(column, cloumnTextViewList);
        } else {
            List<TableItemView> cloumnTextViewList = mapColumn.get(column);
            cloumnTextViewList.add(tableItemView);
        }

        if (mapRow.get(row) == null) {
            List<TableItemView> cloumnTextViewList = new ArrayList<>();
            cloumnTextViewList.add(tableItemView);
            mapRow.put(row, cloumnTextViewList);
            View viewYTitle = tableViewAdapter.createYItemView(row);
            if(viewYTitle != null) {
                YItemView yItemView = new YItemView(viewYTitle, row);
                gridLayoutY.addView(yItemView.getView(), yWidth, itemHeight);
                mapYItem.put(row, yItemView);
                tableViewAdapter.bindYItemView(viewYTitle, row);
            }
        } else {
            List<TableItemView> cloumnTextViewList = mapRow.get(row);
            cloumnTextViewList.add(tableItemView);
        }

        tableItemView.getView().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    List<TableItemView> cloumnTextViewList = mapColumn.get(column);
                    List<TableItemView> rowTextViewList = mapRow.get(row);
                    TableClick tableClick = new TableClick();
                    tableClick.setView(view);
                    tableClick.setPosition(itemCount - 1);
                    tableClick.setRow(row);
                    tableClick.setCloumn(column);
                    tableClick.setCloumnTextViewList(cloumnTextViewList);
                    tableClick.setRowTextViewList(rowTextViewList);
                    tableClick.setyItemView(mapYItem.get(row));
                    onItemClickListener.onClickItem(v, tableClick);
                }
            }
        });
        tableViewAdapter.bindView(view,itemCount - 1,row,column);
        gridLayoutTable.addView(tableItemView.getView(), itemWidth, itemHeight);
    }

    public void hideYAxis(){
        gridLayoutY.setVisibility(GONE);
    }

    /**
     * 列：左边定位到position位置
     */
    public void setLeftCloumnPosition(int position) {
        horizontalScrollView.scrollTo(position * itemWidth, 0);
    }

    /**
     * 列：右边定位到position位置
     */
    public void setRightCloumnPosition(int position) {
        //获取当前控件的宽度
        int width = this.getWidth();
        //总长度
        int allWidth = position * itemWidth;
        int scrollWidth = allWidth - width;
        horizontalScrollView.scrollTo(scrollWidth, 0);
    }

    public interface OnItemClickListener {
        void onClickItem(View view, TableClick tableClick);
        void onClickYItem(View view,TableClick tableClick);
    }

}
