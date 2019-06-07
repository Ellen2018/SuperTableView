package com.ellen.tableview.supertableview.adapter;

import android.view.View;
import android.widget.GridLayout;

import com.ellen.tableview.supertableview.TableItemView;
import com.ellen.tableview.supertableview.TableView;
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

    public abstract View createItemView(int position, int row, int column);

    public int getItemCount() {
        return tableView.getColumnNumber() * tableView.getRowNumber();
    }

    public abstract void bindView(View view, int finalIndex, int row, int column);

    public abstract View createYItemView(int row);

    public abstract void bindYItemView(View view, int row);

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

    public void updateCloumn(int newColumn) {
    }

    public void notifyChange() {
        tableView.setTableViewAdapter(this);
    }

    public void addDataColumn(List<View> viewList) {
        gridLayout.removeAllViews();
        tableView.getMapItemViews().clear();
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
                        gridLayout.addView(tableItemView.getView());
                        tableItemView.getView().setMinimumWidth(tableView.getItemWidth());
                        tableItemView.getView().setMinimumHeight(tableView.getItemHeight());
                        break;
                    }
                }
            }
        }
    }

    public void addDataRow(List<View> viewList,View yItemView) {
        gridLayout.removeAllViews();
        tableView.getMapItemViews().clear();
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
                        gridLayout.addView(tableItemView.getView());
                        tableItemView.getView().setMinimumWidth(tableView.getItemWidth());
                        tableItemView.getView().setMinimumHeight(tableView.getItemHeight());
                        break;
                    }
                }
            }
        }
        //添加Y轴View
        tableView.getGridLayoutY().addView(yItemView);
        YItemView yItemView1 = new YItemView(yItemView,tableView.getRowNumber()-1);
        yItemView.setMinimumWidth(tableView.getyWidth());
        yItemView.setMinimumHeight(tableView.getItemHeight());
        tableView.getMapYItem().put(tableView.getRowNumber()-1,yItemView1);
        tableView.setItemOnClick(yItemView,tableView.getRowNumber()-1,-1);
    }

    public void addDataColumn(List<View> viewList,AddDataCallback addDataCallback){
        addDataColumn(viewList);
        addDataCallback.addViewSuccess(tableView.getMapItemViews(),tableView.getMapYItem());
    }

    public void updateCloumnData(int column, UpdateDataCallback updateDataCallback) {
        List<TableItemView> tableItemViewList = tableView.getMapColumn().get(column);
        updateDataCallback.update(tableItemViewList);
    }

    public void updateRowData(int row, UpdateDataCallback updateDataCallback) {
        List<TableItemView> tableItemViewList = tableView.getMapRow().get(row);
        updateDataCallback.update(tableItemViewList);
    }

    public interface UpdateDataCallback {
        void update(List<TableItemView> tableItemViewList);
    }

    public interface AddDataCallback{
        void addViewSuccess(Map<Integer,TableItemView> tableViewMap,Map<Integer,YItemView> yItemViewMap);
    }

}
