package com.example.lihong.btmnavview;

import android.graphics.Color;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBtmNavView;
    private ViewPager mViewPager;

    private List<Fragment> fragmentList;

    private int currentPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件
        mViewPager=(ViewPager) findViewById(R.id.viewpager);
        mBtmNavView=(BottomNavigationView)findViewById(R.id.btm_nav_view);

        initViewPager();

        initBtmNavView();
    }

    private void initViewPager(){
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

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                //在ViewPager中关联BottomNavigationView的子项
                switch (position){
                    case 0:
                        mBtmNavView.setSelectedItemId(R.id.tab_menu_weixin);
                        break;
                    case 1:
                        mBtmNavView.setSelectedItemId(R.id.tab_menu_address);
                        break;
                    case 2:
                        mBtmNavView.setSelectedItemId(R.id.tab_menu_find);
                        break;
                    case 3:
                        mBtmNavView.setSelectedItemId(R.id.tab_menu_me);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private  void initBtmNavView(){

        mBtmNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item){


                //在BottomNavigationView中关联ViewPager的页面
                switch (item.getItemId()){
                    case R.id.tab_menu_weixin:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.tab_menu_address:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.tab_menu_find:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.tab_menu_me:
                        mViewPager.setCurrentItem(3);
                        break;
                }

                return true;
            }
        });

    }

}
