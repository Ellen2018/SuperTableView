package com.ellen.tableview.supertableview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ellen.tableview.R;
import com.ellen.tableview.supertableview.adapter.TableViewAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.SuperTableAdapter;
import com.ellen.tableview.supertableview.adapter.superadapter.TableHorizontalScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableView extends RelativeLayout {

    private GridLayout gridLayoutTable;
    private GridLayout gridLayoutY;
    private GridLayout gridLayoutX;
    private GridLayout gridLayoutXY;
    private LinearLayout ll_table;
    //用于列定位
    private TableHorizontalScrollView horizontalScrollView;
    private TableHorizontalScrollView horizontalScrollView_x;
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
    //X轴Item集合
    private Map<Integer, XItemView> mapXItem;
    //适配器
    private TableViewAdapter tableViewAdapter;
    //
    private Map<Integer, TableItemView> mapItemViews;
    //
    private OnScrollChangeListener onHScrollChangeListener;
    private OnScrollChangeListener onVScrollChangeListener;
    private PagingMode pagingMode = null;

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
    //x轴宽度
    private int xHeight = 100;
    //item实时个数
    private int itemCount = 0;
    //复用的个数
    private int fuYongCount = 100;

    private boolean isHidenX = false;
    private boolean isHideY = false;
    private boolean isHideXY = false;

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
                gridLayoutY.setColumnCount(1);
            } else if (attr == R.styleable.TableView_RowCount) {
                this.rowNumber = typedArray.getInteger(attr, this.rowNumber);
                gridLayoutX.setRowCount(1);
            } else if (attr == R.styleable.TableView_YItemWidth) {
                yWidth = (int) typedArray.getDimension(attr, 100);// 控件的宽强制设成30;
            } else if (attr == R.styleable.TableView_XItemHeight) {
                xHeight = (int) typedArray.getDimension(attr, 100);// 控件的宽强制设成30;
            } else if (attr == R.styleable.TableView_isHideX) {
                isHidenX = typedArray.getBoolean(attr, false);
            } else if (attr == R.styleable.TableView_isHideY) {
                isHideY = typedArray.getBoolean(attr, false);
            } else if (attr == R.styleable.TableView_isHideXY) {
                isHideXY = typedArray.getBoolean(attr, false);
            }

        }

        typedArray.recycle();

        LinearLayout.LayoutParams yParams = (LinearLayout.LayoutParams) gridLayoutY.getLayoutParams();
        yParams.height = LayoutParams.MATCH_PARENT;// 控件的高强制设成20
        yParams.width = yWidth;
        gridLayoutY.setLayoutParams(yParams); //使设置好的布局参数应用到控件

        FrameLayout.LayoutParams xParams = (FrameLayout.LayoutParams) gridLayoutX.getLayoutParams();
        xParams.height = xHeight;// 控件的高强制设成20
        xParams.width = LayoutParams.MATCH_PARENT;
        gridLayoutX.setPadding(yWidth, 0, 0, 0);
        gridLayoutX.setLayoutParams(xParams); //使设置好的布局参数应用到控件

        RelativeLayout.LayoutParams xyParams = (LayoutParams) gridLayoutXY.getLayoutParams();
        xParams.height = xHeight;
        xParams.width = yWidth;
        gridLayoutXY.setLayoutParams(xyParams);
        if (isHideXY) {
            hideXY();
        }
        if (isHidenX) {
            hideXAxis();
        }
        if (isHideY) {
            hideYAxis();
        }

    }

    public TableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
                gridLayoutX.setColumnCount(columnNumber);
            } else if (attr == R.styleable.TableView_RowCount) {
                this.rowNumber = typedArray.getInteger(attr, this.rowNumber);
                gridLayoutTable.setRowCount(rowNumber);
                gridLayoutY.setRowCount(rowNumber);
                gridLayoutX.setRowCount(1);
            } else if (attr == R.styleable.TableView_YItemWidth) {
                yWidth = (int) typedArray.getDimension(attr, 100);// 控件的宽强制设成30;
            } else if (attr == R.styleable.TableView_XItemHeight) {
                xHeight = (int) typedArray.getDimension(attr, 100);// 控件的宽强制设成30;
            } else if (attr == R.styleable.TableView_isHideX) {
                isHidenX = typedArray.getBoolean(attr, false);
            } else if (attr == R.styleable.TableView_isHideY) {
                isHideY = typedArray.getBoolean(attr, false);
            } else if (attr == R.styleable.TableView_isHideXY) {
                isHideXY = typedArray.getBoolean(attr, false);
            }
        }

        typedArray.recycle();

        LinearLayout.LayoutParams yParams = (LinearLayout.LayoutParams) gridLayoutY.getLayoutParams();
        yParams.height = LayoutParams.MATCH_PARENT;// 控件的高强制设成20
        yParams.width = yWidth;
        gridLayoutY.setLayoutParams(yParams); //使设置好的布局参数应用到控件

        FrameLayout.LayoutParams xParams = (FrameLayout.LayoutParams) gridLayoutX.getLayoutParams();
        xParams.height = xHeight;// 控件的高强制设成20
        xParams.width = LayoutParams.MATCH_PARENT;
        gridLayoutX.setPadding(yWidth, 0, 0, 0);
        gridLayoutX.setLayoutParams(xParams); //使设置好的布局参数应用到控件

        RelativeLayout.LayoutParams xyParams = (LayoutParams) gridLayoutXY.getLayoutParams();
        xParams.height = xHeight;
        xParams.width = yWidth;
        gridLayoutXY.setLayoutParams(xyParams);
        if (isHideXY) {
            hideXY();
        }
        if (isHidenX) {
            hideXAxis();
        }
        if (isHideY) {
            hideYAxis();
        }
    }

    public OnTouchListener getOnTouchListener() {
        return horizontalScrollView.getOnTouchListener();
    }

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener) {
        horizontalScrollView.setOnTouchListener(onTouchListener);
        horizontalScrollView_x.setOnTouchListener(onTouchListener);
    }

    public PagingMode getPagingMode() {
        return pagingMode;
    }

    public void setPagingMode(PagingMode pagingMode) {
        this.pagingMode = pagingMode;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
        if (columnNumber > gridLayoutTable.getColumnCount())
            gridLayoutTable.setColumnCount(columnNumber);
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

    public void setyWidth(int yWidth) {
        this.yWidth = yWidth;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public boolean isHidenX() {
        return isHidenX;
    }

    public boolean isHideY() {
        return isHideY;
    }

    public boolean isHideXY() {
        return isHideXY;
    }

    public OnScrollChangeListener getOnHScrollChangeListener() {
        return onHScrollChangeListener;
    }

    public void setOnHScrollChangeListener(OnScrollChangeListener onHScrollChangeListener) {
        this.onHScrollChangeListener = onHScrollChangeListener;
    }

    public OnScrollChangeListener getOnVScrollChangeListener() {
        return onVScrollChangeListener;
    }

    public void setOnVScrollChangeListener(OnScrollChangeListener onVScrollChangeListener) {
        this.onVScrollChangeListener = onVScrollChangeListener;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
        if (rowNumber > gridLayoutTable.getRowCount())
            gridLayoutTable.setRowCount(rowNumber);
    }

    public int getxHeight() {
        return xHeight;
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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

    public GridLayout getGridLayoutXY() {
        return gridLayoutXY;
    }

    public void setGridLayoutXY(GridLayout gridLayoutXY) {
        this.gridLayoutXY = gridLayoutXY;
    }

    public GridLayout getGridLayoutX() {
        return gridLayoutX;
    }

    public void setGridLayoutX(GridLayout gridLayoutX) {
        this.gridLayoutX = gridLayoutX;
    }

    public Map<Integer, XItemView> getMapXItem() {
        return mapXItem;
    }

    public Map<Integer, YItemView> getMapYItem() {
        return mapYItem;
    }

    public TableViewAdapter getTableViewAdapter() {
        return tableViewAdapter;
    }

    public void setTableViewAdapter(TableViewAdapter tableViewAdapter) {
        if (tableViewAdapter.equals(this.tableViewAdapter)) {
            return;
        }
        clear();
        this.tableViewAdapter = tableViewAdapter;
        this.tableViewAdapter.setCloumn(columnNumber);
        this.tableViewAdapter.setGridLayout(gridLayoutTable);
        this.tableViewAdapter.setTableView(this);
        this.tableViewAdapter.bindAdapter();
        View xyView = tableViewAdapter.createXYView();
        if (xyView == null) {
            TextView textView = new TextView(getContext());
            textView.setBackgroundColor(Color.BLACK);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            textView.setText("Y/X");
            xyView = textView;
        }
        if (xyView != null) {
            xyView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onClickXYView(v);
                    }
                }
            });
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            relativeLayout.setMinimumWidth(yWidth);
            relativeLayout.setMinimumHeight(xHeight);
            relativeLayout.addView(xyView);
            RelativeLayout.LayoutParams layoutParams = (LayoutParams) xyView.getLayoutParams();
            layoutParams.height = xHeight;
            layoutParams.width = yWidth;
            xyView.setLayoutParams(layoutParams);
            gridLayoutXY.addView(relativeLayout);
            tableViewAdapter.bindXYItemView(xyView);
            xyView.setMinimumWidth(yWidth);
            xyView.setMinimumHeight(xHeight);
        }
        for (int i = 0; i < columnNumber; i++) {
            View view = tableViewAdapter.createXItemView(i);
            if (view != null) {
                gridLayoutX.addView(view);
                view.setMinimumHeight(xHeight);
                view.setMinimumWidth(itemWidth);
                tableViewAdapter.bindXItemView(view, i);
                final int finalI = i;
                XItemView xItemView = new XItemView(view, finalI);
                mapXItem.put(finalI, xItemView);
                setItemOnClick(view, -1, i);
            }
        }
        int itemCountCopy = 0;
        if (pagingMode != null) {
            if (pagingMode.isVer()) {
                itemCountCopy = pagingMode.getFirstSize() * tableViewAdapter.getTableColumn();
                setColumnNumber(tableViewAdapter.getTableColumn());
                setRowNumber(pagingMode.getFirstSize());
                getGridLayoutY().setRowCount(pagingMode.getFirstSize());
                getGridLayoutX().setColumnCount(tableViewAdapter.getTableColumn());
            } else {
                itemCountCopy = pagingMode.getFirstSize() * tableViewAdapter.getTableRow();
                Log.e("首次加载",pagingMode.getFirstSize()+"");
                Log.e("预加载",tableViewAdapter.getTableRow()+"");
                setColumnNumber(pagingMode.getFirstSize());
                setRowNumber(tableViewAdapter.getTableRow());
                getGridLayoutY().setRowCount(tableViewAdapter.getTableRow());
                getGridLayoutX().setColumnCount(pagingMode.getFirstSize());
            }
        } else {
            itemCountCopy = tableViewAdapter.getItemCount();
            setColumnNumber(tableViewAdapter.getTableColumn());
            setRowNumber(tableViewAdapter.getTableRow());
            getGridLayoutY().setRowCount(tableViewAdapter.getTableRow());
            getGridLayoutX().setColumnCount(tableViewAdapter.getTableColumn());
        }

        for (int i = 0; i < itemCountCopy; i++) {
            itemCount++;
            View view = null;
            if (gridLayoutTable.getOrientation() == GridLayout.VERTICAL) {
                //竖直方向
                int row = (itemCount-1) % rowNumber;
                int column = (itemCount-1) / rowNumber;
                view = tableViewAdapter.createItemView(i, row, column);
            } else {
                //水平方向
                final int row = getRow(itemCount - 1, columnNumber);
                final int column = getColumn(itemCount - 1, getRow(itemCount - 1, columnNumber), columnNumber);
                view = tableViewAdapter.createItemView(i, row, column);
            }
            addItem(view);
            view.setMinimumWidth(getItemWidth());
            view.setMinimumHeight(getItemHeight());
        }
    }

    public Map<Integer, TableItemView> getMapItemViews() {
        return mapItemViews;
    }

    public GridLayout getGridLayoutTable() {
        return gridLayoutTable;
    }

    private void clear() {
        mapYItem.clear();
        mapXItem.clear();
        mapColumn.clear();
        mapItemViews.clear();
        mapRow.clear();
        itemCount = 0;
        gridLayoutTable.removeAllViews();
        gridLayoutY.removeAllViews();
        gridLayoutX.removeAllViews();
        gridLayoutXY.removeAllViews();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_view_table, this);
        gridLayoutTable = view.findViewById(R.id.grid_layout);
        gridLayoutY = view.findViewById(R.id.grid_layout_y);
        gridLayoutX = view.findViewById(R.id.grid_layout_x);
        gridLayoutXY = view.findViewById(R.id.grid_layout_xy);
        scrollView = view.findViewById(R.id.scrollView);
        ll_table = view.findViewById(R.id.ll_table);
        scrollView.setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (onVScrollChangeListener != null) {
                    onVScrollChangeListener.onScrollChange(v, scrollX, scrollY, oldScrollX, oldScrollY);
                }
                if (pagingMode != null && pagingMode.isVer()) {
                    TableView tableView = TableView.this;
                    if(scrollY+tableView.getHeight() > (tableView.getRowNumber()-pagingMode.getLoadBeforeSize())*tableView.getItemHeight()) {
                        if (tableView.getRowNumber() < tableViewAdapter.getTableRow()) {
                            for (int i = 0; i < pagingMode.getAddSize(); i++) {
                                if(tableView.getRowNumber() == tableViewAdapter.getTableRow())break;
                                SuperTableAdapter superTableAdapter = (SuperTableAdapter) tableViewAdapter;
                                superTableAdapter.addRow();
                            }
                        }
                    }
                }
            }
        });
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        horizontalScrollView_x = view.findViewById(R.id.horizontalScrollView_x);
        horizontalScrollView.setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                horizontalScrollView_x.scrollTo(scrollX, scrollY);
                if (onHScrollChangeListener != null) {
                    onHScrollChangeListener.onScrollChange(v, scrollX, scrollY, oldScrollX, oldScrollY);
                }
                //分页加载模式
                if (pagingMode != null && !pagingMode.isVer()) {
                    TableView tableView = TableView.this;
                    if(scrollX+tableView.getWidth()> (tableView.getColumnNumber()-pagingMode.getLoadBeforeSize())*tableView.getItemWidth()) {
                        if (tableView.getColumnNumber() < tableViewAdapter.getTableColumn()) {
                            for (int i = 0; i < pagingMode.getAddSize(); i++) {
                                if(tableView.getColumnNumber() == tableViewAdapter.getTableColumn())break;
                                SuperTableAdapter superTableAdapter = (SuperTableAdapter) tableViewAdapter;
                                superTableAdapter.addColumn();
                            }
                        }
                    }
                }
            }
        });
        horizontalScrollView_x.setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                horizontalScrollView.scrollTo(scrollX, scrollY);
                if (onHScrollChangeListener != null) {
                    onHScrollChangeListener.onScrollChange(v, scrollX, scrollY, oldScrollX, oldScrollY);
                }
                //分页加载模式
            }
        });
        mapColumn = new HashMap<>();
        mapRow = new HashMap<>();
        mapYItem = new HashMap<>();
        mapItemViews = new HashMap<>();
        mapXItem = new HashMap<>();
    }

    public int getRow(int position, int cloumn) {
        return (position) / cloumn;
    }

    public int getColumn(int position, int row, int cloumn) {
        return (((position) - row * cloumn) % cloumn);
    }

    public void addItem(final View view) {
        int row = 0;
        int column = 0;
        if (gridLayoutTable.getOrientation() == GridLayout.VERTICAL) {
             row = (itemCount-1) % rowNumber;
             column = (itemCount-1) / rowNumber;
        } else {
            row = getRow(itemCount - 1, columnNumber);
            column = getColumn(itemCount - 1, getRow(itemCount - 1, columnNumber), columnNumber);
        }
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
        //适配item的宽和高
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setMinimumWidth(itemWidth);
        relativeLayout.setMinimumHeight(itemHeight);
        relativeLayout.addView(view);
        RelativeLayout.LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutParams.height = itemHeight;
        layoutParams.width = itemWidth;
        view.setLayoutParams(layoutParams);
        gridLayoutTable.addView(relativeLayout);
        mapItemViews.put(finalIndex, tableItemView);
    }

    public void hideYAxis() {
        isHideY = true;
        gridLayoutY.setVisibility(GONE);
        FrameLayout.LayoutParams linearParams = (FrameLayout.LayoutParams) gridLayoutX.getLayoutParams();
        linearParams.height = xHeight;// 控件的高强制设成20
        linearParams.width = LayoutParams.MATCH_PARENT;
        gridLayoutX.setPadding(0, 0, 0, 0);
        gridLayoutX.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        gridLayoutXY.setVisibility(GONE);
    }

    public void hideXAxis() {
        isHidenX = true;
        gridLayoutX.setVisibility(GONE);
        gridLayoutXY.setVisibility(GONE);
        horizontalScrollView_x.setVisibility(GONE);
    }

    public void hideXY() {
        isHideXY = true;
        gridLayoutXY.setVisibility(GONE);
    }

    public int getScrollLeft() {
        return horizontalScrollView.getScrollX();
    }

    public int getScrollRight() {
        return horizontalScrollView.getScrollX() + gridLayoutTable.getWidth();
    }

    /**
     * 是否已经滑到了最右边
     *
     * @return
     */
    public boolean isScrollToRight() {
        return horizontalScrollView.isScrollToRight();
    }

    public int getActualTableWidth() {
        return horizontalScrollView.getWidth();
    }

    public int getTableWidth() {
        return gridLayoutTable.getWidth();
    }

    /**
     * 是否已经滑到了最左边
     *
     * @return
     */
    public boolean isScrollToLeft() {
        return horizontalScrollView.isScrollToLeft();
    }

    /**
     * 列：左边定位到position位置
     */
    public void setLeftCloumnPosition(int position) {
        int scrollWidth = position * itemWidth;
        horizontalScrollView.scrollTo(scrollWidth, 0);
    }

    /**
     * 列：右边定位到position位置
     */
    public void setRightCloumnPosition(int position) {
        int yItemWidth = (int) horizontalScrollView.getX() + this.getPaddingLeft();
        //获取当前控件的宽度
        int width = this.getWidth();
        //总长度
        int allWidth = (position + 1) * itemWidth;
        int scrollWidth = allWidth - width + yItemWidth;
        horizontalScrollView.scrollTo(scrollWidth, 0);
    }

    public void setTopRowPosition(int position) {
        scrollView.scrollTo(0, position * itemHeight);
    }

    public void setBottomRowPosition(int position) {
        int yItemWidth = (int) scrollView.getY() + this.getPaddingTop();
        int height = this.getHeight();
        int allHeight = (position + 1) * itemHeight;
        int scrollHeight = allHeight - height + yItemWidth;
        scrollView.scrollTo(0, scrollHeight);
    }

    public void setItemOnClick(final View view, final int rowO, final int columnO) {
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int row = rowO;
                int column = columnO;
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
                    tableClick.setxItemView(mapXItem.get(column));
                    //获取点击的位置的长度
                    int length_x1 = (column + 1) * itemWidth;
                    int length_x2 = horizontalScrollView.getScrollX() + horizontalScrollView.getWidth();
                    int length_x3 = column * itemWidth;
                    int length_x4 = (column + 1) * itemWidth;
                    if (length_x1 > length_x2) {
                        tableClick.setXPartHide(true);
                    }
                    if (horizontalScrollView.getScrollX() > length_x3 && horizontalScrollView.getScrollX() < length_x4) {
                        tableClick.setXPartHide(false);
                    }

                    int length_y1 = (row + 1) * itemHeight;
                    int length_y2 = scrollView.getScrollY() + scrollView.getHeight();
                    int length_y3 = row * itemHeight;
                    int length_y4 = (row + 1) * itemHeight;
                    if (length_y1 > length_y2) {
                        tableClick.setYPartHide(true);
                    }
                    if (scrollView.getScrollY() > length_y3 && scrollView.getScrollY() < length_y4) {
                        tableClick.setYPartHide(false);
                    }
                    if (column < 0 && row >= 0) {
                        onItemClickListener.onClickYItem(v, tableClick);
                    }
                    if (column >= 0 && row >= 0) {
                        onItemClickListener.onClickItem(v, tableClick);
                    }
                    if (column >= 0 && row < 0) {
                        onItemClickListener.onClickXItem(view, tableClick);
                    }
                }
            }
        });

    }

    public interface OnItemClickListener {
        void onClickItem(View view, TableClick tableClick);

        void onClickYItem(View view, TableClick tableClick);

        void onClickXItem(View view, TableClick tableClick);

        void onClickXYView(View view);
    }

}
