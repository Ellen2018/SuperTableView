package com.ellen.tableview.supertableview;

public class PagingMode {

    /**
     * 分页的方向
     */
    private boolean isVer;
    /**
     * 第一次加载的数据个数
     */
    private int firstSize;
    /**
     * 每次分页加载的个数
     */
    private int addSize;

    private int loadBeforeSize = 1;
    /**
     * 记录数据总个数,防止notifiChange增加bug
     */
    private int currentSize = 0;

    public int getLoadBeforeSize() {
        return loadBeforeSize;
    }

    public void setLoadBeforeSize(int loadBeforeSize) {
        this.loadBeforeSize = loadBeforeSize;
    }

    public PagingMode(boolean isVer, int firstSize, int addSize, int loadBeforeSize) {
        this.isVer = isVer;
        this.firstSize = firstSize;
        this.addSize = addSize;
        this.loadBeforeSize = loadBeforeSize;
    }

    public boolean isVer() {
        return isVer;
    }

    public void setVer(boolean ver) {
        isVer = ver;
    }

    public int getFirstSize() {
        return firstSize;
    }

    public void setFirstSize(int firstSize) {
        this.firstSize = firstSize;
    }

    public int getAddSize() {
        return addSize;
    }

    public void setAddSize(int addSize) {
        this.addSize = addSize;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }
}
