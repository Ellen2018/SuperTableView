package com.ellen.tableview.view;

import android.view.View;
import android.widget.GridLayout;

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

    public abstract View createItemView(int position, int row, int cloumn);

    public abstract int getItemCount();

    public abstract void bindView(View view, int finalIndex, int row, int cloumn);

    public abstract View createYItemView(int row);

    public abstract void bindYItemView(View view, int row);

    public int getAllRow() {
        return getItemCount() / getCloumn() ;
    }

    public int getFinalIndex(int position, int row, int cloumn) {
        if (isOrientationV) {
            return row + cloumn * getAllRow();
        } else {
            return position;
        }
    }

    public void updateCloumn(int newColumn) {
        this.cloumn = newColumn;
        tableView.setColumnNumber(newColumn);
        gridLayout.setColumnCount(newColumn);
        if (isOrientationV) {
            //调整
            gridLayout.removeAllViews();
            tableView.getMapRow().clear();
            tableView.getMapColumn().clear();
            tableView.getMapYItem().clear();
            for (int i = 0; i < getItemCount(); i++) {
                //重新计算行和列
//                int indexRow = tableView.getRow(i, cloumn);
//                Log.e("行号", indexRow + "");
//                int indexCloumn = tableView.getColumn(i, indexRow, cloumn);
//                Log.e("列号", indexCloumn + "");
//                Log.e("位置", i + "");
//                int finalIndex = getFinalIndex(i+1, indexRow, indexCloumn);
//                Log.e("填塞数据", finalIndex + "");
                gridLayout.addView(tableView.getMapItemViews().get(i));
            }
        }
    }

    public void notifyChange() {
       tableView.setTableViewAdapter(this);
    }

}
