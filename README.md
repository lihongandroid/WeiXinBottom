# WeiXinBottom
仿写微信布局以及实现底部导航的几种方式

WeChatDemo是自己第一次实现微信布局，没有参考课程，WeChat是对WeChatDemo的优化，在WeChat里有比较详细的注解
 
BottomTab、BtmNavView、FragmentTabHost、RadioGroup是查看网上教程后，几种实现底部布局的方式
 
WeChat的功能涉及到：ViewPager包含Fragment，Fragment1中包含RecyclerView
 
一、TabLayout+ViewPager+Fragment
    1 TabLayout默认带Indicator，因此将其驱动，则需设置app：tabIndacatorHeight="0dp"
    2 设置OnTabChangeLister需在添加Tab之前，不然第一次不会回调onTabSelecte()方法，即进入界面，第一个子项Weixin不会是绿色
    
二、BoottomNavigationView+ViewPager+Fragment
    1 BottomNavigationView的Tab是通过menu的方式添加的，即在布局文件中加入菜单文件的布局： app:menu="@menu/menu_tab"
    2 BottomNavigationView是Material风格，需添加依赖库：com.android.support:design:26.+
    3 该控件的Tab只能是3-5个，多了或少了汇报错
    
三 FragmentTabHost+ViewPager+Fragment
    1 FragmentTabHost是比较老牌的实现底部导航的方式，相比前两个，稍微复杂一些
    2 FragmentTabHost的id是系统提供的，不能随便起，即android:id="@android:id/tabhost"
    3 首先通过setup（）方法建立FragmentTabHost与ViewPagr的关联，然后设置Tab的切换监听，再添加Tab
    4 还需去掉每个Tab之间的分割线： mFragmentTabHost.getTabWidget().setDividerDrawable(null);
    5 在onTabChanged()回调中需要手动设置每个Tab的切换状态：从TabWidget中取出每个子View来设置选中和未选中的状态（跟TabLayout自定义布局添加Tab一样）
    6 和TabLayout一样，在添加Tab之前需设置OnTabChangeListener监听，否则第一次收不到onTabChanged（）回调
    
四 RadioGroup、RadioButton+ViewPager+Fragment
    1 RadioGroup+RadioButton是做单选的，也可实现底部导航
    2 自定style，去掉其默认样式并定义RadioButton的属性
    3 因为RadioButton有check和uncheck状态，直接用selector就能切换状态的图标和check文字的颜色
    4 即在布局文件中设置每个RadioButton显示的图标： android:drawableTop="@drawable/tab_weixin_seletctor"等
    5 初始化第一个TabWedgit：weixinRadioButton.setChecked(true);
五 总结
  1 Tablayout和BottomNavigationView是android5.0以后添加的新控件，符合Material设计风格
  2 TabLayout使用更多的场景其实是顶部到Indicator的滑动页卡
  3 FragmentTabHost和RadoGroup是比较老牌是实现方式，在4.x时代就大量使用
