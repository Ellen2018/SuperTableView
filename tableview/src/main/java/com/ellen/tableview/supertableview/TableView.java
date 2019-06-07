package com.ellen.tableview.supertableview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.ellen.tableview.R;
import com.ellen.tableview.supertableview.adapter.TableViewAdapter;

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
    private Map<Integer, YItemView> mapYItem;
    //适配器
    private TableViewAdapter tableViewAdapter;
    //
    private Map<Integer, TableItemView> mapItemViews;

    //列数
    private int columnNumber = 10;
    //行数
    private int rowNumber = -1;
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
            } else if (attr == R.styleable.TableView_YItemWidth) {
                yWidth = (int) typedArray.getDimension(attr, 100);// 控件的宽强制设成30;
                LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) gridLayoutY.getLayoutParams();
                linearParams.height = LayoutParams.MATCH_PARENT;// 控件的高强制设成20
                linearParams.width = yWidth;
                gridLayoutY.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
            }
        }
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public GridLayout getGridLayoutY() {
        return gridLayoutY;
    }

    public void setGridLayoutY(GridLayout gridLayoutY) {
        this.gridLayoutY = gridLayoutY;
    }

    public int getyWidth() {
        return yWidth;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setyWidth(int yWidth) {
        this.yWidth = yWidth;
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
        gridLayoutTable.setRowCount(rowNumber);
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

    public Map<Integer, List<TableItemView>> getMapRow() {
        return mapRow;
    }

    public Map<Integer, List<TableItemView>> getMapColumn() {
        return mapColumn;
    }

    public void setMapColumn(Map<Integer, List<TableItemView>> mapColumn) {
        this.mapColumn = mapColumn;
    }

    public Map<Integer, YItemView> getMapYItem() {
        return mapYItem;
    }

    public TableViewAdapter getTableViewAdapter() {
        return tableViewAdapter;
    }

    public Map<Integer, TableItemView> getMapItemViews() {
        return mapItemViews;
    }

    private void clear() {
        mapYItem.clear();
        mapColumn.clear();
        mapItemViews.clear();
        mapRow.clear();
        itemCount = 0;
        gridLayoutTable.removeAllViews();
        gridLayoutY.removeAllViews();
    }

    public void setTableViewAdapter(TableViewAdapter tableViewAdapter) {
        if(tableViewAdapter.equals(this.tableViewAdapter)){
            return;
        }
        clear();
        this.tableViewAdapter = tableViewAdapter;
        this.tableViewAdapter.setCloumn(columnNumber);
        this.tableViewAdapter.setGridLayout(gridLayoutTable);
        this.tableViewAdapter.setTableView(this);
        for (int i = 0; i < tableViewAdapter.getItemCount(); i++) {
            itemCount++;
            final int row = getRow(itemCount - 1, columnNumber);
            final int column = getColumn(itemCount - 1, getRow(itemCount - 1, columnNumber), columnNumber);
            View view = tableViewAdapter.createItemView(i, row, column);
            addItem(view);
            view.setMinimumWidth(getItemWidth());
            view.setMinimumHeight(getItemHeight());
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
        mapItemViews = new HashMap<>();
    }

    public int getRow(int position, int cloumn) {
        return (position) / cloumn;
    }

    public int getColumn(int position, int row, int cloumn) {
        return (((position) - row * cloumn) % cloumn);
    }

    public void addItem(final View view) {
        final int row = getRow(itemCount - 1, columnNumber);
        final int column = getColumn(itemCount - 1, getRow(itemCount - 1, columnNumber), columnNumber);
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
            if (viewYTitle != null) {
                YItemView yItemView = new YItemView(viewYTitle, row);
                setItemOnClick(yItemView.getView(), row, -1);
                gridLayoutY.addView(yItemView.getView(), yWidth, itemHeight);
                GridLayout.LayoutParams layoutParams = (GridLayout.LayoutParams) yItemView.getView().getLayoutParams();
                layoutParams.height = itemHeight;// 控件的高强制设成20
                yItemView.getView().setLayoutParams(layoutParams);
                mapYItem.put(row, yItemView);
                tableViewAdapter.bindYItemView(viewYTitle, row);
            }
        } else {
            List<TableItemView> cloumnTextViewList = mapRow.get(row);
            cloumnTextViewList.add(tableItemView);
        }

        setItemOnClick(tableItemView.getView(), row, column);
        int finalIndex = tableViewAdapter.getFinalIndex(itemCount - 1, row, column);
        tableViewAdapter.bindView(view, finalIndex, row, column);
        gridLayoutTable.addView(view);
        mapItemViews.put(finalIndex, tableItemView);
    }

    public void hideYAxis() {
        gridLayoutY.setVisibility(GONE);
    }

    /**
     * 列：左边定位到position位置
     */
    public void setLeftCloumnPosition(int position) {
        int scrollWidth = position  * itemWidth;
        horizontalScrollView.scrollTo(scrollWidth, 0);
    }

    /**
     * 列：右边定位到position位置
     */
    public void setRightCloumnPosition(int position) {
        int yItemWidth = gridLayoutY.getVisibility() == VISIBLE ? yWidth : 0;
        //获取当前控件的宽度
        int width = this.getWidth();
        //总长度
        int allWidth = (position + 1) * itemWidth;
        int scrollWidth = allWidth - width + yItemWidth;
        horizontalScrollView.scrollTo(scrollWidth, 0);
    }

    public interface OnItemClickListener {
        void onClickItem(View view, TableClick tableClick);

        void onClickYItem(View view, TableClick tableClick);
    }

    public void setItemOnClick(final View view, final int row, final int column) {
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    List<TableItemView> cloumnViewList = mapColumn.get(column);
                    List<TableItemView> rowViewList = mapRow.get(row);
                    TableClick tableClick = new TableClick();
                    tableClick.setView(view);
                    tableClick.setPosition(itemCount - 1);
                    tableClick.setRow(row);
                    tableClick.setCloumn(column);
                    tableClick.setCloumnViewList(cloumnViewList);
                    tableClick.setRowViewList(rowViewList);
                    tableClick.setyItemView(mapYItem.get(row));
                    if (column != -1) {
                        onItemClickListener.onClickItem(v, tableClick);
                    } else {
                        onItemClickListener.onClickYItem(v, tableClick);
                    }
                }
            }
        });

    }

}
