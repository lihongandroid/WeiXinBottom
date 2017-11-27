package com.example.lihong.radiogroup;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Fragment> fragmentList;

    private RadioGroup mRadioGroup;

    private RadioButton weixinRadioButton;//用于初始化第一个字按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        mRadioGroup=(RadioGroup)findViewById(R.id.radio_group);
        weixinRadioButton=(RadioButton)findViewById(R.id.radio_button_weixin);

        initViewPager();

        initRadioGroup();

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

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //改变RadioGroup的选中状态
                mRadioGroup.check(mRadioGroup.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initRadioGroup(){

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.radio_button_weixin:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.radio_button_address:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.radio_button_find:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.radio_button_me:
                        mViewPager.setCurrentItem(3);
                        break;

                }
            }
        });

        weixinRadioButton.setChecked(true);//为了保证第一次回调onChechedChangeLister,即初始化第一个子Tab按钮
    }

    }
