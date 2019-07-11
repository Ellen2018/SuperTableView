package com.ellen.tableview.supertableview.adapter.superadapter;

import android.util.Log;
import android.view.View;

import com.ellen.tableview.supertableview.adapter.TableViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public abstract class SuperTableAdapter extends TableViewAdapter {

    private Map<Integer, XYItemViewHolder> xViewHolderMap;
    private Map<Integer, XYItemViewHolder> yViewHolderMap;
    private Map<ItemPosition, ItemViewHolder> itemViewHolderMap;
    private Map<Integer, List<ItemPosition>> xItemPositionMap;
    private Map<Integer, List<ItemPosition>> yItemPositionMap;

    public SuperTableAdapter() {
        xViewHolderMap = new TreeMap<>();
        yViewHolderMap = new TreeMap<>();
        itemViewHolderMap = new TreeMap<>();
        xItemPositionMap = new TreeMap<>();
        yItemPositionMap = new TreeMap<>();
    }

    @Override
    public void bindView(View view, int finalIndex, int row, int column) {

    }

    @Override
    public View createItemView(int position, int row, int column) {
        int type = getItemType(row, column);
        ItemViewHolder itemViewHolder = createItemViewHolder(row, column, type);
        bindItemViewHolder(itemViewHolder, row, column);
        ItemPosition itemPosition = new ItemPosition(position, row, column);
        //x
        if (xItemPositionMap.get(column) == null) {
            List<ItemPosition> itemPositionList = new ArrayList<>();
            itemPositionList.add(itemPosition);
            xItemPositionMap.put(column, itemPositionList);
        } else {
            List<ItemPosition> itemPositionList = xItemPositionMap.get(column);
            itemPositionList.add(itemPosition);
        }
        //y
        if (yItemPositionMap.get(row) == null) {
            List<ItemPosition> itemPositionList = new ArrayList<>();
            itemPositionList.add(itemPosition);
            yItemPositionMap.put(row, itemPositionList);
        } else {
            List<ItemPosition> itemPositionList = yItemPositionMap.get(row);
            itemPositionList.add(itemPosition);
        }
        itemViewHolderMap.put(itemPosition, itemViewHolder);
        return itemViewHolder.getItemView();
    }

    @Override
    public View createYItemView(int row) {
        int type = geYItemType(row);
        XYItemViewHolder xyItemViewHolder = createYItemViewHolder(row, type);
        if (xyItemViewHolder != null) {
            bindYItemViewHolder(xyItemViewHolder, row);
            yViewHolderMap.put(row, xyItemViewHolder);
            return xyItemViewHolder.getItemView();
        } else {
            return null;
        }
    }

    @Override
    public void bindYItemView(View view, int row) {

    }

    @Override
    public View createXItemView(int column) {
        int type = getXItemType(column);
        XYItemViewHolder xyItemViewHolder = createXItemViewHolder(column, type);
        if (xyItemViewHolder != null) {
            bindXItemViewHolder(xyItemViewHolder, column);
            xViewHolderMap.put(column, xyItemViewHolder);
            return xyItemViewHolder.getItemView();
        } else {
            return null;
        }
    }

    @Override
    public void bindXItemView(View view, int column) {

    }

    //X轴
    protected int getXItemType(int column) {
        return 0;
    }

    protected abstract XYItemViewHolder createXItemViewHolder(int column, int type);

    protected abstract void bindXItemViewHolder(XYItemViewHolder xItemViewHolder, int column);

    //Y轴
    protected int geYItemType(int row) {
        return 0;
    }

    protected abstract XYItemViewHolder createYItemViewHolder(int row, int type);

    protected abstract void bindYItemViewHolder(XYItemViewHolder yItemViewHolder, int row);

    //Item
    protected int getItemType(int row, int column) {
        return 0;
    }

    protected abstract ItemViewHolder createItemViewHolder(int row, int column, int type);

    protected abstract void bindItemViewHolder(ItemViewHolder itemViewHolder, int row, int column);

    public void notifyChanged() {
        if (getTableView().getColumnNumber() != getTableColumn()) {
            //存在增加或者是删除
            if (getTableColumn() < getTableView().getColumnNumber()) {
                int columnNumber = getTableView().getColumnNumber();
                for (int i = 0; i < columnNumber - getTableColumn(); i++) {
                    removeColumn();
                }
            } else {
                //增加
                int columnNumber = getTableView().getColumnNumber();
                for (int i = 0; i < getTableColumn() - columnNumber; i++) {
                    addColumn();
                }
            }
        }

        if (getTableView().getRowNumber() != getTableRow()) {
            //存在增加或者是删除
            if (getTableRow() > getTableView().getRowNumber()) {
                //增加
                int rowNumber = getTableView().getRowNumber();
                for (int i = 0; i < getTableRow() - rowNumber; i++) {
                    addRow();
                }
            } else {
                int rowNumber = getTableView().getRowNumber();
                for (int i = 0; i < rowNumber - getTableRow(); i++) {
                    removeRow();
                }
            }
        }
        //更新x轴
        Set<Integer> xSet = xViewHolderMap.keySet();
        for (int i : xSet) {
            bindXItemViewHolder(xViewHolderMap.get(i), i);
        }
        //更新y轴
        Set<Integer> ySet = yViewHolderMap.keySet();
        for (int i : ySet) {
            bindYItemViewHolder(yViewHolderMap.get(i), i);
        }
        //更新item
        Set<ItemPosition> itemSet = itemViewHolderMap.keySet();
        for (ItemPosition itemPosition : itemSet) {
            bindItemViewHolder(itemViewHolderMap.get(itemPosition), itemPosition.row, itemPosition.column);
        }
    }

    private void removeColumn() {
        //删除
        int rowNumber = getTableView().getRowNumber();
        int removeColumn = getTableView().getColumnNumber() - 1;
        //删除itemViewHolderMap
        xViewHolderMap.remove(removeColumn);

        //遍历yItemPositionMap
        for (int row = 0; row < rowNumber; row++) {
            List<ItemPosition> itemPositionList = yItemPositionMap.get(row);
            if(itemPositionList != null) {
                for (int j = 0; j < itemPositionList.size(); j++) {
                    if (j == removeColumn) {
                        itemViewHolderMap.remove(itemPositionList.get(j));
                        itemPositionList.remove(j);
                        break;
                    }
                }
            }
        }
        xItemPositionMap.remove(removeColumn);
        removeColumn(removeColumn);
    }

    private void removeRow() {
        //删除
        int columnNumber = getTableView().getColumnNumber();
        int removeRow = getTableView().getRowNumber() - 1;
        //删除itemViewHolderMap
        yViewHolderMap.remove(removeRow);

        //遍历yItemPositionMap
        for (int column = 0; column < columnNumber; column++) {
            List<ItemPosition> itemPositionList = xItemPositionMap.get(column);
            if(itemPositionList != null) {
                for (int j = 0; j < itemPositionList.size(); j++) {
                    if (j == removeRow) {
                        itemViewHolderMap.remove(itemPositionList.get(j));
                        itemPositionList.remove(j);
                        break;
                    }
                }
            }
        }
        yItemPositionMap.remove(removeRow);
        removeRow(removeRow);
    }

    public void addColumn() {
        //获取x轴的type
        int xType = getXItemType(getTableView().getColumnNumber());
        //获取添加的x轴的ViewHolder
        XYItemViewHolder xyItemViewHolder = createXItemViewHolder(getTableView().getColumnNumber(), xType);
        xViewHolderMap.put(getTableView().getColumnNumber(), xyItemViewHolder);
        View xView = null;
        if (xyItemViewHolder != null) {
            xView = xyItemViewHolder.getItemView();
        }

        //添加一列数据
        List<View> viewList = new ArrayList<>();
        for (int row = 0; row < getTableView().getRowNumber(); row++) {
            int itemType = getItemType(row, getTableView().getColumnNumber());
            ItemViewHolder itemViewHolder = createItemViewHolder(row, getTableView().getColumnNumber(), itemType);
            ItemPosition itemPosition = new ItemPosition(itemViewHolderMap.size(), row, getTableView().getColumnNumber());
            itemViewHolderMap.put(itemPosition, itemViewHolder);
            if (yItemPositionMap.get(row) == null) {
                List<ItemPosition> itemPositionList = new ArrayList<>();
                itemPositionList.add(itemPosition);
                yItemPositionMap.put(row, itemPositionList);
            } else {
                List<ItemPosition> itemPositionList = yItemPositionMap.get(row);
                itemPositionList.add(itemPosition);
            }
            viewList.add(itemViewHolder.getItemView());
            this.bindItemViewHolder(itemViewHolder,row,getTableView().getColumnNumber());
        }
        addColumnAuto(viewList, xView);
    }

    public final void addRow() {
        //获取y轴的type
        int yType = geYItemType(getTableView().getRowNumber());
        XYItemViewHolder yItemViewHolder = createYItemViewHolder(getTableView().getRowNumber(), yType);
        yViewHolderMap.put(getTableView().getRowNumber(), yItemViewHolder);
        View yView = null;
        if (yItemViewHolder != null) {
            yView = yItemViewHolder.getItemView();
        }


        //添加一列数据
        List<View> viewList = new ArrayList<>();
        for (int column = 0; column < getTableView().getColumnNumber(); column++) {
            int itemType = getItemType(getTableView().getRowNumber(), column);
            ItemViewHolder itemViewHolder = createItemViewHolder(getTableView().getRowNumber(), column, itemType);
            ItemPosition itemPosition = new ItemPosition(itemViewHolderMap.size(), getTableView().getRowNumber(), column);
            itemViewHolderMap.put(itemPosition, itemViewHolder);
            if (xItemPositionMap.get(column) == null) {
                List<ItemPosition> itemPositionList = new ArrayList<>();
                itemPositionList.add(itemPosition);
                xItemPositionMap.put(column, itemPositionList);
            } else {
                List<ItemPosition> itemPositionList = xItemPositionMap.get(column);
                itemPositionList.add(itemPosition);
            }
            viewList.add(itemViewHolder.getItemView());
            this.bindItemViewHolder(itemViewHolder,getTableView().getRowNumber(),column);
        }
        addRowAuto(viewList, yView);
    }

    protected final void updateRow(int row, SuperUpdateDataCallback superUpdateDataCallback) {
        List<ItemPosition> itemPositionList = xItemPositionMap.get(row);
        List<ItemViewHolder> itemViewHolderList = new ArrayList<>();
        for (ItemPosition itemPosition : itemPositionList) {
            itemViewHolderList.add(itemViewHolderMap.get(itemPosition));
        }
        superUpdateDataCallback.update(itemViewHolderList);
    }

    public void updateColumn(int column, SuperUpdateDataCallback superUpdateDataCallback) {
        List<ItemPosition> itemPositionList = yItemPositionMap.get(column);
        List<ItemViewHolder> itemViewHolderList = new ArrayList<>();
        for (ItemPosition itemPosition : itemPositionList) {
            itemViewHolderList.add(itemViewHolderMap.get(itemPosition));
        }
        superUpdateDataCallback.update(itemViewHolderList);
    }

    private static class ItemPosition implements Comparable<ItemPosition> {
        private int position;
        private int row;
        private int column;

        public ItemPosition(int position, int row, int column) {
            this.position = position;
            this.row = row;
            this.column = column;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        @Override
        public int compareTo(ItemPosition o) {
            return this.position - o.position;
        }
    }

    public interface SuperUpdateDataCallback {
        void update(List<ItemViewHolder> itemViewHolderList);
    }
}
