package com.example.lihong.wechatdemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private List<Fragment> fragmentList;
    private ViewPager viewPager;

    private LinearLayout llOne,llTwo,llThree,llFour;

    private LinearLayout linearLayouts[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLinearLayout();
        initViewPager();

    }

    private void initLinearLayout(){
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.ll_parent);
        linearLayouts=new LinearLayout[4];
        for(int i=0;i<linearLayouts.length;i++){
            linearLayouts[i]=(LinearLayout)linearLayout.getChildAt(i);//遍历父LineaLayout下的所有LinearLayout子节点
            linearLayouts[i].setOnClickListener(this);
            linearLayouts[i].setTag(i);
        }
    }

    private void initViewPager(){

        viewPager=(ViewPager)findViewById(R.id.viewpager);
        llOne=(LinearLayout)findViewById(R.id.ll_one);
        llTwo=(LinearLayout)findViewById(R.id.ll_two);
        llThree=(LinearLayout)findViewById(R.id.ll_three);
        llFour=(LinearLayout)findViewById(R.id.ll_four);

        llOne.setBackgroundColor(Color.GREEN);

        fragmentList=new ArrayList<>();

      ChatListFragment chatListFragment=new ChatListFragment();
        Fragment2 fragment2=new Fragment2();
        Fragment3 fragment3=new Fragment3();
        Fragment4 fragment4=new Fragment4();
        fragmentList.add(chatListFragment);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);

        FragmentPagerAdapter adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };

        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0:
                        llOne.setBackgroundColor(Color.GREEN);
                        llTwo.setBackgroundColor(0xff11ffff);
                        llThree.setBackgroundColor(0xff11ffff);
                        llFour.setBackgroundColor(0xff11ffff);
                        break;
                    case 1:
                        llTwo.setBackgroundColor(Color.GREEN);
                        llOne.setBackgroundColor(0xff11ffff);
                        llThree.setBackgroundColor(0xff11ffff);
                        llFour.setBackgroundColor(0xff11ffff);
                        break;
                    case 2:
                        llThree.setBackgroundColor(Color.GREEN);
                        llTwo.setBackgroundColor(0xff11ffff);
                        llOne.setBackgroundColor(0xff11ffff);
                        llFour.setBackgroundColor(0xff11ffff);
                        break;
                    case 3:
                        llFour.setBackgroundColor(Color.GREEN);
                        llTwo.setBackgroundColor(0xff11ffff);
                        llThree.setBackgroundColor(0xff11ffff);
                        llOne.setBackgroundColor(0xff11ffff);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_title,menu);

        return true;
    }

    @Override
    public void onClick(View view){
        //利用在initLinearLayout（）中设置的标识符跳转页面
      viewPager.setCurrentItem((int)view.getTag());
    }

}
