## 0.如何导入?

[![](https://www.jitpack.io/v/Ellen2018/SuperTableView.svg)](https://www.jitpack.io/#Ellen2018/SuperTableView)

&emsp;&emsp;首先你需要在项目的build.gradle中配置以下代码：  

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }//加上这句即可
		}
	}

&emsp;&emsp;然后你在要使用该库的module中添加以下依赖:  

     implementation 'com.github.Ellen2018:SuperTableView:x.y.z'

&emsp;&emsp;x,y,z是笔者库的版本值，例如：1.2.0

## 1.介绍

&emsp;&emsp;此库用来做表格视图，笔者封装了几种适配器，让你像使用RecyclerView一样去使用它,它用来做怎样的表格呢？带有XY轴的都可以做。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190912094909887.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0NsQW5kRWxsZW4=,size_16,color_FFFFFF,t_70)

&emsp;&emsp;当然以上只是带Y轴的表格。详细用法请看下面。

## 2.用法

&emsp;&emsp;步骤一：在布局文件种声明控件

    <com.ellen.tableview.supertableview.TableView
        android:id="@+id/tableView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:ItemHeight="50dp" 
        app:ItemWidth="120dp" 
        app:RowCount="10"  
        app:XItemHeight="50dp"
        app:YItemWidth="88dp" />

- ItemHeight:Item的高度
- ItemWidth:Item的宽度
- ColumnCount:列的个数
- RowCount:行的宽度
- XItemHeight：X轴的高度
- YItemWidth：Y轴的宽度
- isHideX：是否隐藏X轴
- isHideY:是否隐藏Y轴
- isHideXY:是否隐藏XY轴

&emsp;&emsp;步骤二：绑定控件，并设置适配器给它，设配器一共有四种，当然你还可以自定义自配器，但是你必须熟悉笔者封装的源码原理，那笔者这里就简单介绍一下这四种适配器：
SuperNoXYTableViewAdapter(无XY轴),SuperXTableViewAdapter(仅仅有X轴),SuperYTableViewAdapter(仅仅有Y轴),SuperXYTableViewAdapter(XY轴都有)，接下来就演示一下适配器的代码：

    public class TableAdapter extends SuperXTableViewAdapter<TableAdapter.MyItemViewHolder, TableAdapter.XItemViewHolder> {

        public Context context;
        public List<String> xTitles;
        public int rowNumber = 20;
        public TableAdapter(Context context,List<String> xTitles,List<String> itemTitles){
            this.context = context;
            this.xTitles = xTitles;
        }

        public int getRowNumber() {
            return rowNumber;
        }

        public void setRowNumber(int rowNumber) {
            this.rowNumber = rowNumber;
        }

        @Override
        protected XItemViewHolder createXViewHolder(int column) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_line_table_x,null);
            XItemViewHolder xyItemViewHolder = new XItemViewHolder(view);
            return xyItemViewHolder;
        }

        @Override
        protected void bindXViewHolder(XItemViewHolder yItemViewHolder, int column) {
        }

        @Override
        protected MyItemViewHolder createTableItemViewHolder(int row, int column) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_line_table_x,null);
            MyItemViewHolder myItemViewHolder = new MyItemViewHolder(view);
            return myItemViewHolder;
        }

        @Override
        protected void bindTableItemViewHolder(MyItemViewHolder myItemViewHolder, int row, int column) {
            myItemViewHolder.tv.setText("("+row+","+column+")");
        }


        //重写可以定义表格的列数
        @Override
        public int getTableColumn() {
            return 22000;
        }

        //重写可以定义表格的行数
        @Override
        public int getTableRow() {
            return 5;
        }

        @Override
        public void bindAdapter() {
            super.bindAdapter();
        }

        public static class XItemViewHolder extends XYItemViewHolder{

            TextView tv;

            public XItemViewHolder(View yItemView) {
                super(yItemView);
                tv = yItemView.findViewById(R.id.tv);
            }
        }

        public static class MyItemViewHolder extends ItemViewHolder {

            TextView tv;
            public MyItemViewHolder(View yItemView) {
                super(yItemView);
                tv = yItemView.findViewById(R.id.tv);
            }
        }

    }

&emsp;&emsp;从以上的代码可以看出，笔者封装的适配器和RecyclerView的适配器是不是很像呢？没错，笔者的适配器就是按照RecyclerView的适配器进行封装的，为了方便大家很快上手。此外如果你一次性加载过多的表格，那么建议使用延迟加载模式，因为会导致卡顿，用了延迟加载后可解决这个问题。实例代码如下：  

    
    tableView.setPagingMode(new PagingMode(true, 20, 20, 10));

&emsp;&emsp;PagingMode构造器4个参数的含义分别代表：  

- 延迟加载的方向:true为竖直
- 第一次加载数据的个数
- 每次延迟加载的个数
- 滑动到倒数第几个开始延迟加载




