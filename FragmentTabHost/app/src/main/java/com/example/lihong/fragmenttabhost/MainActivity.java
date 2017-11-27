package com.example.lihong.fragmenttabhost;

import android.content.Context;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Fragment> fragmentList;

    private FragmentTabHost mFragmentTabHost;

    private String textViewArray[]={"微信","通讯录","发现","我"};
    private int imageViewArray[]={
                R.drawable.tab_weixin_normal,
                R.drawable.tab_address_normal,
                R.drawable.tab_find_frd_normal,
                R.drawable.tab_settings_normal
    };
    private int imageViewArrayPressed[]={
            R.drawable.tab_weixin_pressed,
            R.drawable.tab_address_pressed,
            R.drawable.tab_find_frd_pressed,
            R.drawable.tab_settings_pressed
    };
    private Class FragmentArray[]={Fragment1.class,Fragment2.class,Fragment3.class,Fragment4.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件
        mViewPager=(ViewPager) findViewById(R.id.viewpager);
        mFragmentTabHost=(FragmentTabHost)findViewById(android.R.id.tabhost);

        initViewPager();

        initFragmentTabHost();

    }

    private void initViewPager() {
        //初始化适配器数据源
        fragmentList = new ArrayList<>();
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);

        //设置适配器关联数据
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };

        //为ViewPager设置适配器
        mViewPager.setAdapter(adapter);

        //设置监听，联系FragmentTabHost的Tab切换
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mFragmentTabHost.setCurrentTab(position);//当左右滑动页面时，FragmentTabHost的Tab也跟着变换

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFragmentTabHost(){

        //关联FragmentTabHost
        mFragmentTabHost.setup(this,getSupportFragmentManager(),R.id.viewpager);//绑定ViewPager

        //注意，监听要设置在添加Tab之前
        mFragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                //更新点击与未点击的状态变换
                updateTabState();

                //将选中的Tab位置设置给ViewPager，以便控制页面的切换
                int position=mFragmentTabHost.getCurrentTab();
                mViewPager.setCurrentItem(position);
            }
        });

        //添加Tab,并绑定Fragment
        for(int i=0;i<4;i++){
             TabHost.TabSpec tabSpec=mFragmentTabHost.newTabSpec(textViewArray[i]).setIndicator(getTabView(this,i));
            mFragmentTabHost.addTab(tabSpec,FragmentArray[i],null);
        }

        //去掉Tab之间的分割线
        mFragmentTabHost.getTabWidget().setDividerDrawable(null);

    }

    /**
     * 更新Tab点击与未点击的状态
     */
    private void updateTabState(){
        TabWidget tabWidget=mFragmentTabHost.getTabWidget();
        for(int i=0;i<tabWidget.getTabCount();i++){
            View view= tabWidget.getChildTabViewAt(i);
            ImageView icon=(ImageView)view.findViewById(R.id.image_item);
            TextView text=(TextView)view.findViewById(R.id.tv_item);
            if(i==mFragmentTabHost.getCurrentTab()){
                icon.setImageResource(imageViewArrayPressed[i]);
                text.setTextColor(0xff45c01a);
            }else {
                icon.setImageResource(imageViewArray[i]);
                text.setTextColor(getResources().getColor(android.R.color.darker_gray));
            }
        }
    }

    private  View getTabView(Context context, int position){
        View view= LayoutInflater.from(context).inflate(R.layout.item_bottom,null);
        TextView tvTitle=(TextView)view.findViewById(R.id.tv_item);
        tvTitle.setText(textViewArray[position]);

        ImageView imgTab=(ImageView)view.findViewById(R.id.image_item);
        imgTab.setImageResource(imageViewArray[position]);//其余三个界面初始化为灰色
        return  view;
    }

}
