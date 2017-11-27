package com.example.lihong.bottomtab1;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.RelativeDateTimeFormatter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Fragment> fragmentList;

    private TabLayout mTabLayout;

       private static final int[] TAB_TITLE=new int[]{R.string.weixin,R.string.address,R.string.find,R.string.me};

       private static final int[] TAB_IMAGES_NORMAL=new int[]{
               R.drawable.tab_weixin_normal,
               R.drawable.tab_address_normal,
               R.drawable.tab_find_frd_normal,
               R.drawable.tab_settings_normal};
       private static final int[] TAB_IMAGES_PRESSED=new int[]{
               R.drawable.tab_weixin_pressed,
               R.drawable.tab_address_pressed,
               R.drawable.tab_find_frd_pressed,
               R.drawable.tab_settings_pressed};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取控件
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout=(TabLayout)findViewById(R.id.bottom_tab_layout);

        initViewPager();

        initTabLayout();
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

        //ViewPager监听,专门为TabLayout设计的
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    private void initTabLayout(){

        //实现TabLayout的监听，监听要设置在Tab之前
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public  void onTabSelected(TabLayout.Tab tab){

               mViewPager.setCurrentItem(tab.getPosition());//根据点击选中的底部导航的位置改变ViewPager显示的页面

                //改变Tab状态
                for(int i=0;i<mTabLayout.getTabCount();i++){
                    View view=mTabLayout.getTabAt(i).getCustomView();
                    ImageView icon=(ImageView)view.findViewById(R.id.image_item);
                    TextView text=(TextView)view.findViewById(R.id.tv_item);

                    if(i==tab.getPosition()){//选中状态为绿色

                        //mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(TAB_IMAGES_PRESSED[i]));

                        icon.setImageDrawable(getResources().getDrawable(TAB_IMAGES_PRESSED[i]));
                        text.setTextColor(0xff45c01a);

                    }else {//未选中状态为灰色
                       // mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(TAB_IMAGES_NORMAL[i]));

                        icon.setImageDrawable(getResources().getDrawable(TAB_IMAGES_NORMAL[i]));
                        text.setTextColor(getResources().getColor(android.R.color.darker_gray));

                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab){

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab){

            }
        });

        //  mTabLayout.setTabTextColors(0xffcccccc,0xff45c01a);//改变字体颜色：未点击时为灰色0xffcccccc,点击后为绿色0xff45c01a

        //设置icon和文字,进行初始化
        /*  该部分是没有使用自定义子项布局时的初始化
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_weixin_pressed)).setText("微信"));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_address_normal)).setText("通讯录"));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_find_frd_normal)).setText("发现"));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_settings_normal)).setText("我"));
        */


        //使用自定义子项布局后的初始化，Tab的添加需放在监听的后面，不让第一次无法回调监听中的方法
        for(int i=0;i<4;i++){
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(getTabView(this,i)));
        }
    }

    /**
     * 该方法用于初始化界面，底部导航自定义布局
     * @param context 上下文
     * @param position 底部导航的子项位置所在
     * @return view 为底部导航的自定义子项布局
     */
    private static View getTabView(Context context, int position){
        View view= LayoutInflater.from(context).inflate(R.layout.item_bottom,null);
        TextView tvTitle=(TextView)view.findViewById(R.id.tv_item);
        tvTitle.setText(TAB_TITLE[position]);

        ImageView imgTab=(ImageView)view.findViewById(R.id.image_item);
            imgTab.setImageResource(TAB_IMAGES_NORMAL[position]);//其余三个界面初始化为灰色
        return  view;
    }
}
