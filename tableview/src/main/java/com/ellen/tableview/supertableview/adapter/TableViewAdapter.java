package com.ellen.tableview.supertableview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.ellen.tableview.supertableview.TableItemView;
import com.ellen.tableview.supertableview.TableView;
import com.ellen.tableview.supertableview.XItemView;
import com.ellen.tableview.supertableview.YItemView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class TableViewAdapter {

    private int cloumn;
    //是否取向为垂直
    private boolean isOrientationV = false;
    private GridLayout gridLayout;
    private TableView tableView;
    private boolean isRemoveXClear = false;
    private boolean isRemoveYClear = false;

    public boolean isRemoveXClear() {
        return isRemoveXClear;
    }

    public boolean isRemoveYClear() {
        return isRemoveYClear;
    }

    public int getCloumn() {
        return cloumn;
    }

    public void setCloumn(int cloumn) {
        this.cloumn = cloumn;
    }

    public boolean isOrientationV() {
        return isOrientationV;
    }

    public void setOrientationV(boolean orientationV) {
        isOrientationV = orientationV;
    }

    public GridLayout getGridLayout() {
        return gridLayout;
    }

    public void setGridLayout(GridLayout gridLayout) {
        this.gridLayout = gridLayout;
    }

    public TableView getTableView() {
        return tableView;
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }

    public int getItemCount() {
        return getTableRow() * getTableColumn();
    }

    public int getTableRow() {
        return tableView.getRowNumber();
    }

    public int getTableColumn() {
        return tableView.getColumnNumber();
    }

    public abstract void bindAdapter();

    public abstract void bindView(View view, int finalIndex, int row, int column);

    public abstract View createItemView(int position, int row, int column);

    public abstract View createYItemView(int row);

    public abstract void bindYItemView(View view, int row);

    public abstract View createXItemView(int column);

    public abstract void bindXItemView(View view, int column);

    public abstract View createXYView();

    public abstract void bindXYItemView(View view);

    public int getAllRow() {
        return getItemCount() / getCloumn();
    }

    public int getFinalIndex(int position, int row, int cloumn) {
        if (isOrientationV) {
            return row + cloumn * getAllRow();
        } else {
            return position;
        }
    }

    protected View getViewByLayoutId(Context context,int layoutId){
        return LayoutInflater.from(context).inflate(layoutId,null);
    }

    public void addDataColumn(List<View> viewList, View xItemView) {
        tableView.getGridLayoutTable().removeAllViews();
        tableView.getMapItemViews().clear();
        tableView.setItemCount(0);
        for (int i = 0; i < viewList.size(); i++) {
            //处理行
            TableItemView tableItemView = new TableItemView(i, tableView.getColumnNumber(), viewList.get(i));
            List<TableItemView> tableItemViewListRow = tableView.getMapRow().get(i);
            tableItemViewListRow.add(tableItemView);
            //处理列
            if (tableView.getMapColumn().get(tableView.getColumnNumber()) == null) {
                List<TableItemView> tableItemViewListColumn = new ArrayList<>();
                tableItemViewListColumn.add(tableItemView);
                tableView.getMapColumn().put(tableView.getColumnNumber(), tableItemViewListColumn);
            } else {
                List<TableItemView> tableItemViewListColumn = tableView.getMapColumn().get(tableView.getColumnNumber());
                tableItemViewListColumn.add(tableItemView);
            }
        }
        tableView.setColumnNumber(tableView.getColumnNumber() + 1);
        for (int i = 0; i < tableView.getMapRow().size(); i++) {
            for (int j = 0; j < tableView.getColumnNumber(); j++) {
                List<TableItemView> tableItemViewList = tableView.getMapColumn().get(j);
                for (TableItemView tableItemView : tableItemViewList) {
                    if (tableItemView.getRow() == i && tableItemView.getCloumn() == j) {
                        int index;
                        if (isOrientationV) {
                            index = j * tableView.getColumnNumber() + i;
                        } else {
                            index = i * tableView.getRowNumber() + j;
                        }
                        tableView.getMapItemViews().put(index, tableItemView);
                        tableView.setItemOnClick(tableItemView.getView(), tableItemView.getRow(), tableItemView.getCloumn());
                        View view = tableItemView.getView();
                        ViewGroup viewParent = (ViewGroup) view.getParent();
                        if (viewParent != null) {
                            viewParent.removeView(view);
                        }
                        tableView.getGridLayoutTable().addView(tableItemView.getView(),tableView.getItemWidth(),tableView.getItemHeight());
                        tableItemView.getView().setMinimumWidth(tableView.getItemWidth());
                        tableItemView.getView().setMinimumHeight(tableView.getItemHeight());
                        tableView.setItemCount(tableView.getItemCount() + 1);
                        break;
                    }
                }
            }
        }
        if (xItemView != null) {
            //添加X轴View
            tableView.getGridLayoutX().setColumnCount(tableView.getColumnNumber() + 1);
            tableView.getGridLayoutX().addView(xItemView,tableView.getItemWidth(),tableView.getxHeight());
            XItemView xItemView1 = new XItemView(xItemView, tableView.getColumnNumber() - 1);
            xItemView.setMinimumWidth(tableView.getItemWidth());
            xItemView.setMinimumHeight(tableView.getxHeight());
            tableView.getMapXItem().put(tableView.getColumnNumber() - 1, xItemView1);
            tableView.setItemOnClick(xItemView, -1, tableView.getColumnNumber() - 1);
        }
    }

    public void addDataRow(List<View> viewList, View yItemView) {
        tableView.getGridLayoutTable().removeAllViews();
        tableView.getMapItemViews().clear();
        tableView.setItemCount(0);
        for (int i = 0; i < viewList.size(); i++) {
            //处理行
            TableItemView tableItemView = new TableItemView(tableView.getRowNumber(), i, viewList.get(i));
            if (tableView.getMapRow().get(tableView.getRowNumber()) == null) {
                List<TableItemView> tableItemViewListRow = new ArrayList<>();
                tableItemViewListRow.add(tableItemView);
                tableView.getMapRow().put(tableView.getRowNumber(), tableItemViewListRow);
            } else {
                List<TableItemView> tableItemViewListRow = tableView.getMapRow().get(tableView.getRowNumber());
                tableItemViewListRow.add(tableItemView);
            }

            //处理列
            List<TableItemView> tableItemViewListColumn = tableView.getMapColumn().get(i);
            Log.e("列数",i+"");
            tableItemViewListColumn.add(tableItemView);
        }
        tableView.setRowNumber(tableView.getRowNumber() + 1);
        for (int i = 0; i < tableView.getRowNumber(); i++) {
            for (int j = 0; j < tableView.getMapColumn().size(); j++) {
                List<TableItemView> tableItemViewList = tableView.getMapColumn().get(j);
                for (TableItemView tableItemView : tableItemViewList) {
                    if (tableItemView.getRow() == i && tableItemView.getCloumn() == j) {
                        int index;
                        if (isOrientationV) {
                            index = j * tableView.getColumnNumber() + i;
                        } else {
                            index = i * tableView.getRowNumber() + j;
                        }
                        tableView.getMapItemViews().put(index, tableItemView);
                        tableView.setItemOnClick(tableItemView.getView(), tableItemView.getRow(), tableItemView.getCloumn());
                        View view = tableItemView.getView();
                        ViewGroup viewParent = (ViewGroup) view.getParent();
                        if (viewParent != null) {
                            viewParent.removeView(view);
                        }
                        tableView.getGridLayoutTable().addView(tableItemView.getView(),tableView.getItemWidth(),tableView.getItemHeight());
                        tableItemView.getView().setMinimumWidth(tableView.getItemWidth());
                        tableItemView.getView().setMinimumHeight(tableView.getItemHeight());
                        tableView.setItemCount(tableView.getItemCount() + 1);
                        break;
                    }
                }
            }
        }
        if (yItemView != null) {
            //添加Y轴View
            tableView.getGridLayoutY().addView(yItemView,tableView.getyWidth(),tableView.getItemHeight());
            YItemView yItemView1 = new YItemView(yItemView, tableView.getRowNumber() - 1);
            yItemView.setMinimumWidth(tableView.getyWidth());
            yItemView.setMinimumHeight(tableView.getItemHeight());
            tableView.getMapYItem().put(tableView.getRowNumber() - 1, yItemView1);
            tableView.setItemOnClick(yItemView, tableView.getRowNumber() - 1, -1);
        }
    }

    public void addDataColumn(List<View> viewList, View xItemView, AddDataCallback addDataCallback) {
        addDataColumn(viewList, xItemView);
        addDataCallback.addViewSuccess(tableView.getMapItemViews(), tableView.getMapYItem());
    }

    public void updateCloumnData(int column, UpdateDataCallback updateDataCallback) {
        List<TableItemView> tableItemViewList = tableView.getMapColumn().get(column);
        updateDataCallback.update(tableItemViewList);
    }

    public void updateRowData(int row, UpdateDataCallback updateDataCallback) {
        List<TableItemView> tableItemViewList = tableView.getMapRow().get(row);
        updateDataCallback.update(tableItemViewList);
    }

    protected void removeRow(int row) {
        if ((!(row >= 0 && row <= tableView.getMapRow().size() - 1)) || getTableView().getRowNumber()<=0) {
            return;
        }
        tableView.getGridLayoutTable().removeAllViews();
        tableView.getMapItemViews().clear();
        tableView.getGridLayoutY().removeAllViews();
        if(getTableView().getRowNumber() > 1)
        getTableView().getMapColumn().clear();
        tableView.setItemCount(0);
        //移除掉行集合中的此行
        getTableView().getMapRow().remove(row);
        for (int copyRow = 0; copyRow < getTableView().getRowNumber(); copyRow++) {
            if (copyRow != row) {
                if (copyRow < row) {
                    List<TableItemView> rowTableItemViewList = getTableView().getMapRow().get(copyRow);
                    for (TableItemView tableItemView : rowTableItemViewList) {
                        tableView.setItemOnClick(tableItemView.getView(), tableItemView.getRow(), tableItemView.getCloumn());
                        View view = tableItemView.getView();
                        ViewGroup viewParent = (ViewGroup) view.getParent();
                        if (viewParent != null) {
                            viewParent.removeView(view);
                        }
                        tableView.setItemOnClick(tableItemView.getView(), tableItemView.getRow(), tableItemView.getCloumn());
                        tableView.getGridLayoutTable().addView(tableItemView.getView(),tableView.getItemWidth(),tableView.getItemHeight());
                        tableItemView.getView().setMinimumWidth(tableView.getItemWidth());
                        tableItemView.getView().setMinimumHeight(tableView.getItemHeight());
                        tableView.setItemCount(tableView.getItemCount() + 1);
                        if (getTableView().getMapColumn().get(tableItemView.getCloumn()) == null) {
                            List<TableItemView> tableItemViewList = new ArrayList<>();
                            tableItemViewList.add(tableItemView);
                            getTableView().getMapColumn().put(tableItemView.getCloumn(), tableItemViewList);
                        } else {
                            List<TableItemView> tableItemViewList = getTableView().getMapColumn().get(tableItemView.getCloumn());
                            tableItemViewList.add(tableItemView);
                        }
                    }

                } else {
                    List<TableItemView> rowTableItemViewList = getTableView().getMapRow().get(copyRow);
                    getTableView().getMapRow().remove(copyRow);
                    getTableView().getMapRow().put(copyRow - 1, rowTableItemViewList);
                    for (TableItemView tableItemView : rowTableItemViewList) {
                        tableItemView.setRow(tableItemView.getRow() - 1);
                        tableView.setItemOnClick(tableItemView.getView(), tableItemView.getRow(), tableItemView.getCloumn());
                        View view = tableItemView.getView();
                        ViewGroup viewParent = (ViewGroup) view.getParent();
                        if (viewParent != null) {
                            viewParent.removeView(view);
                        }
                        tableView.getGridLayoutTable().addView(tableItemView.getView(),tableView.getItemWidth(),tableView.getItemHeight());
                        tableItemView.getView().setMinimumWidth(tableView.getItemWidth());
                        tableItemView.getView().setMinimumHeight(tableView.getItemHeight());
                        tableView.setItemCount(tableView.getItemCount() + 1);
                        if (getTableView().getMapColumn().get(tableItemView.getCloumn()) == null) {
                            List<TableItemView> tableItemViewList = new ArrayList<>();
                            tableItemViewList.add(tableItemView);
                            getTableView().getMapColumn().put(tableItemView.getCloumn(), tableItemViewList);
                        } else {
                            List<TableItemView> tableItemViewList = getTableView().getMapColumn().get(tableItemView.getCloumn());
                            tableItemViewList.add(tableItemView);
                        }
                    }
                }

            }
        }
        //移除Y轴
        if (getTableView().getGridLayoutY().getVisibility() == View.VISIBLE) {
            getTableView().getMapYItem().remove(row);
            for (int i = 0; i < getTableView().getRowNumber(); i++) {
                if (i != row) {
                    YItemView yItemView = getTableView().getMapYItem().get(i);
                    if (i > row) {
                        getTableView().getMapYItem().remove(i);
                        getTableView().getMapYItem().put(i - 1, yItemView);
                        yItemView.setRow(i - 1);
                    }
                    getTableView().getGridLayoutY().addView(yItemView.getView(),tableView.getyWidth(),tableView.getItemHeight());
                    yItemView.getView().setMinimumWidth(tableView.getyWidth());
                    yItemView.getView().setMinimumHeight(tableView.getItemHeight());
                    tableView.setItemOnClick(yItemView.getView(), yItemView.getRow(), -1);
                }
            }
        }
        getTableView().setRowNumber(getTableView().getRowNumber() - 1);
        Log.e("列的个数",getTableView().getMapColumn().size()+"");
    }

    protected void removeColumn(int column) {
        if ((!(column >= 0 && column <= tableView.getColumnNumber() - 1)) || getTableView().getColumnNumber() <= 0) {
            return;
        }
        tableView.getGridLayoutTable().removeAllViews();
        tableView.getMapItemViews().clear();
        tableView.getGridLayoutX().removeAllViews();
        getTableView().getMapColumn().clear();
        tableView.setItemCount(0);
        tableView.getGridLayoutTable().setColumnCount(getTableView().getColumnNumber()-1);
        for (int row = 0; row < getTableView().getRowNumber(); row++) {
            List<TableItemView> rowItemViewList = getTableView().getMapRow().get(row);
            for (TableItemView tableItemView : rowItemViewList) {
                if (tableItemView.getCloumn() != column) {
                    if (tableItemView.getCloumn() < column) {
                        tableView.setItemOnClick(tableItemView.getView(), tableItemView.getRow(), tableItemView.getCloumn());
                        View view = tableItemView.getView();
                        ViewGroup viewParent = (ViewGroup) view.getParent();
                        if (viewParent != null) {
                            viewParent.removeView(view);
                        }
                        tableView.getGridLayoutTable().addView(tableItemView.getView(),tableView.getItemWidth(),tableView.getItemHeight());
                        tableItemView.getView().setMinimumWidth(tableView.getItemWidth());
                        tableItemView.getView().setMinimumHeight(tableView.getItemHeight());
                        tableView.setItemCount(tableView.getItemCount() + 1);
                        if (getTableView().getMapColumn().get(tableItemView.getCloumn()) == null) {
                            List<TableItemView> tableItemViewList = new ArrayList<>();
                            tableItemViewList.add(tableItemView);
                            getTableView().getMapColumn().put(tableItemView.getCloumn(), tableItemViewList);
                        } else {
                            List<TableItemView> tableItemViewList = getTableView().getMapColumn().get(tableItemView.getCloumn());
                            tableItemViewList.add(tableItemView);
                        }
                    } else {
                        tableItemView.setCloumn(tableItemView.getCloumn() - 1);
                        tableView.setItemOnClick(tableItemView.getView(), tableItemView.getRow(), tableItemView.getCloumn());
                        View view = tableItemView.getView();
                        ViewGroup viewParent = (ViewGroup) view.getParent();
                        if (viewParent != null) {
                            viewParent.removeView(view);
                        }
                        tableView.getGridLayoutTable().addView(tableItemView.getView(),tableView.getItemWidth(),tableView.getItemHeight());
                        tableItemView.getView().setMinimumWidth(tableView.getItemWidth());
                        tableItemView.getView().setMinimumHeight(tableView.getItemHeight());
                        tableView.setItemCount(tableView.getItemCount() + 1);
                        if (getTableView().getMapColumn().get(tableItemView.getCloumn()) == null) {
                            List<TableItemView> tableItemViewList = new ArrayList<>();
                            tableItemViewList.add(tableItemView);
                            getTableView().getMapColumn().put(tableItemView.getCloumn(), tableItemViewList);
                        } else {
                            List<TableItemView> tableItemViewList = getTableView().getMapColumn().get(tableItemView.getCloumn());
                            tableItemViewList.add(tableItemView);
                        }
                    }
                }
            }
            rowItemViewList.remove(column);
        }
        //移除X轴
        if (getTableView().getGridLayoutX().getVisibility() == View.VISIBLE) {
            getTableView().getMapXItem().remove(column);
            for (int i = 0; i < getTableView().getColumnNumber(); i++) {
                if (i != column) {
                    XItemView xItemView = getTableView().getMapXItem().get(i);
                    if (i > column) {
                        getTableView().getMapXItem().remove(i);
                        getTableView().getMapXItem().put(i - 1, xItemView);
                        xItemView.setColumn(xItemView.getColumn() - 1);
                    }
                    View view = xItemView.getView();
                    ViewGroup viewParent = (ViewGroup) view.getParent();
                    if (viewParent != null) {
                        viewParent.removeView(view);
                    }
                    tableView.getGridLayoutX().addView(xItemView.getView(),tableView.getItemWidth(),tableView.getxHeight());
                    xItemView.getView().setMinimumWidth(tableView.getItemWidth());
                    xItemView.getView().setMinimumHeight(tableView.getxHeight());
                    tableView.setItemOnClick(xItemView.getView(), -1, xItemView.getColumn());
                }
            }
        }
        getTableView().setColumnNumber(getTableView().getColumnNumber() - 1);
    }

    public interface UpdateDataCallback {
        void update(List<TableItemView> tableItemViewList);
    }

    public interface AddDataCallback {
        void addViewSuccess(Map<Integer, TableItemView> tableViewMap, Map<Integer, YItemView> yItemViewMap);
    }

}
