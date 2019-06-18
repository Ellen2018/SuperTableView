package com.ellen.tableview.supertableview.adapter.superadapter;

import android.view.View;

import com.ellen.tableview.supertableview.adapter.TableViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public abstract class SuperTableAdapter extends TableViewAdapter {

    private Map<Integer,XYItemViewHolder> xViewHolderMap;
    private Map<Integer,XYItemViewHolder> yViewHolderMap;
    private Map<ItemPosition,ItemViewHolder> itemViewHolderMap;

    public SuperTableAdapter(){
        xViewHolderMap = new TreeMap<>();
        yViewHolderMap = new TreeMap<>();
        itemViewHolderMap = new TreeMap<>();
    }

    @Override
    public void bindView(View view, int finalIndex, int row, int column) {

    }

    @Override
    public View createItemView(int position, int row, int column) {
        int type = getItemType(row,column);
        ItemViewHolder itemViewHolder = createItemViewHolder(row,column,type);
        bindItemViewHolder(itemViewHolder,row,column);
        ItemPosition itemPosition = new ItemPosition(position,row,column);
        itemViewHolderMap.put(itemPosition,itemViewHolder);
        return itemViewHolder.getItemView();
    }

    @Override
    public View createYItemView(int row) {
        int type = geYItemType(row);
        XYItemViewHolder xyItemViewHolder = createYItemViewHolder(row,type);
        if(xyItemViewHolder != null) {
            bindYItemViewHolder(xyItemViewHolder, row);
            yViewHolderMap.put(row,xyItemViewHolder);
            return xyItemViewHolder.getItemView();
        }else {
            return null;
        }
    }

    @Override
    public void bindYItemView(View view, int row) {

    }

    @Override
    public View createXItemView(int column) {
        int type = getXItemType(column);
        XYItemViewHolder xyItemViewHolder = createXItemViewHolder(column,type);
        if(xyItemViewHolder != null) {
            bindXItemViewHolder(xyItemViewHolder, column);
            xViewHolderMap.put(column,xyItemViewHolder);
            return xyItemViewHolder.getItemView();
        }else {
            return null;
        }
    }

    @Override
    public void bindXItemView(View view, int column) {

    }

    //X轴
    protected int getXItemType(int column){
        return 0;
    }
    protected abstract XYItemViewHolder createXItemViewHolder(int column,int type);
    protected abstract void bindXItemViewHolder(XYItemViewHolder xItemViewHolder,int column);
    //Y轴
    protected int geYItemType(int row){
        return 0;
    }
    protected abstract XYItemViewHolder createYItemViewHolder(int row,int type);
    protected abstract void bindYItemViewHolder(XYItemViewHolder yItemViewHolder,int row);
    //Item
    protected int getItemType(int row,int column){
        return 0;
    }
    protected abstract ItemViewHolder createItemViewHolder(int row,int column,int type);
    protected abstract void bindItemViewHolder(ItemViewHolder itemViewHolder,int row,int column);

    public void notifyChanged(){
        //更新x轴
        Set<Integer> xSet = xViewHolderMap.keySet();
        for(int i:xSet){
            bindXItemViewHolder(xViewHolderMap.get(i),i);
        }
        //更新y轴
        Set<Integer> ySet = yViewHolderMap.keySet();
        for(int i:ySet){
            bindYItemViewHolder(yViewHolderMap.get(i),i);
        }
        //更新item
        Set<ItemPosition> itemSet = itemViewHolderMap.keySet();
        for(ItemPosition itemPosition:itemSet){
            bindItemViewHolder(itemViewHolderMap.get(itemPosition),itemPosition.row,itemPosition.column);
        }
    }

    public void addColumn(){
        //获取x轴的type
        int xType = getXItemType(getTableView().getColumnNumber());
        //获取添加的x轴的ViewHolder
        XYItemViewHolder xyItemViewHolder = createXItemViewHolder(getTableView().getColumnNumber(),xType);
        xViewHolderMap.put(getTableView().getColumnNumber(),xyItemViewHolder);
        View xView = null;
        if(xyItemViewHolder != null) {
          xView = xyItemViewHolder.getItemView();
        }

        //添加一列数据
        List<View> viewList = new ArrayList<>();
        for(int row = 0;row<getTableView().getRowNumber();row++){
            int itemType = getItemType(row,getTableView().getColumnNumber());
            ItemViewHolder itemViewHolder = createItemViewHolder(row,getTableView().getColumnNumber(),itemType);
            ItemPosition itemPosition = new ItemPosition(itemViewHolderMap.size(),row,getTableView().getColumnNumber());
            itemViewHolderMap.put(itemPosition,itemViewHolder);
            viewList.add(itemViewHolder.getItemView());
        }
        addDataColumn(viewList,xView);
    }

    public void addRow(){
         //获取y轴的type
         int yType = geYItemType(getTableView().getRowNumber());
        XYItemViewHolder yItemViewHolder = createYItemViewHolder(getTableView().getRowNumber(),yType);
        yViewHolderMap.put(getTableView().getRowNumber(),yItemViewHolder);
        View yView = null;
        if(yItemViewHolder != null){
           yView = yItemViewHolder.getItemView();
        }


        //添加一列数据
        List<View> viewList = new ArrayList<>();
        for(int column = 0;column<getTableView().getColumnNumber();column++){
            int itemType = getItemType(getTableView().getRowNumber(),column);
            ItemViewHolder itemViewHolder = createItemViewHolder(getTableView().getColumnNumber(),column,itemType);
            ItemPosition itemPosition = new ItemPosition(itemViewHolderMap.size(),getTableView().getColumnNumber(),column);
            itemViewHolderMap.put(itemPosition,itemViewHolder);
            viewList.add(itemViewHolder.getItemView());
        }
        addDataRow(viewList,yView);
    }

    private static class ItemPosition implements Comparable<ItemPosition>{
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
}
