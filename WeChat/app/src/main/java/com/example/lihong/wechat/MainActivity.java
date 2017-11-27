package com.example.lihong.wechat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager mViewPager;
    private List<Fragment> fragmentList;
    private ImageView imageViewChat,imageViewAddress,imageViewFind,imageViewMe;
    private TextView tvChat,tvAddress,tvFind,tvMe;
    private Drawable drawableChat,drawableAddress,drawableFind,drawableMe;
    private LinearLayout linearLayouts[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLinearLayout();
    }

    private void initLinearLayout(){
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.ll_parent);
        linearLayouts=new LinearLayout[4];
        for(int i=0;i<linearLayouts.length;i++){
            linearLayouts[i]=(LinearLayout)linearLayout.getChildAt(i);//遍历父LineaLayout下的所有LinearLayout子节点
            linearLayouts[i].setOnClickListener(this);//为每个子LinearLayout设置点击监听事件
            linearLayouts[i].setTag(i);//为每个子LinearLayout设置标识符，以便点击该子LinearLayout时跳转到该页面
        }
    }

    private void initView(){
        //获取控件
        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        imageViewChat=(ImageView)findViewById(R.id.image_chat);
        imageViewAddress=(ImageView)findViewById(R.id.image_address);
        imageViewFind=(ImageView)findViewById(R.id.image_find);
        imageViewMe=(ImageView)findViewById(R.id.image_me);
        tvChat=(TextView)findViewById(R.id.tv_chat);
        tvAddress=(TextView)findViewById(R.id.tv_address);
        tvFind=(TextView)findViewById(R.id.tv_find);
        tvMe=(TextView)findViewById(R.id.tv_me);

       imageViewChat.setImageDrawable(getDrawable(R.drawable.tab_weixin_pressed));
        tvChat.setTextColor(0xff008800);
        
        //设置适配器数据源
        fragmentList=new ArrayList<>();
        Fragment1 fragment1=new Fragment1();
        Fragment2 fragment2=new Fragment2();
        Fragment3 fragment3=new Fragment3();
        Fragment4 fragment4=new Fragment4();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);

        //设置适配器数据
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentStatePagerAdapter adapter=new FragmentStatePagerAdapter(fragmentManager) {
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

        //设置滑动监听
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        imageViewChat.setImageDrawable(getDrawable(R.drawable.tab_weixin_pressed));
                        tvChat.setTextColor(0xff008800);
                        imageViewAddress.setImageDrawable(getDrawable(R.drawable.tab_address_normal));
                        tvAddress.setTextColor(Color.GRAY);
                        imageViewFind.setImageDrawable(getDrawable(R.drawable.tab_find_frd_normal));
                        tvFind.setTextColor(Color.GRAY);
                        imageViewMe.setImageDrawable(getDrawable(R.drawable.tab_settings_normal));
                        tvMe.setTextColor(Color.GRAY);
                        break;
                    case 1:
                        imageViewChat.setImageDrawable(getDrawable(R.drawable.tab_weixin_normal));
                        tvChat.setTextColor(Color.GRAY);
                        imageViewAddress.setImageDrawable(getDrawable(R.drawable.tab_address_pressed));
                        tvAddress.setTextColor(0xff008800);
                        imageViewFind.setImageDrawable(getDrawable(R.drawable.tab_find_frd_normal));
                        tvFind.setTextColor(Color.GRAY);
                        imageViewMe.setImageDrawable(getDrawable(R.drawable.tab_settings_normal));
                        tvMe.setTextColor(Color.GRAY);
                        break;
                    case 2:
                        imageViewChat.setImageDrawable(getDrawable(R.drawable.tab_weixin_normal));
                        tvChat.setTextColor(Color.GRAY);
                        imageViewAddress.setImageDrawable(getDrawable(R.drawable.tab_address_normal));
                        tvAddress.setTextColor(Color.GRAY);
                        imageViewFind.setImageDrawable(getDrawable(R.drawable.tab_find_frd_pressed));
                        tvFind.setTextColor(0xff008800);
                        imageViewMe.setImageDrawable(getDrawable(R.drawable.tab_settings_normal));
                        tvMe.setTextColor(Color.GRAY);
                        break;
                    case 3:
                        imageViewChat.setImageDrawable(getDrawable(R.drawable.tab_weixin_normal));
                        tvChat.setTextColor(Color.GRAY);
                        imageViewAddress.setImageDrawable(getDrawable(R.drawable.tab_address_normal));
                        tvAddress.setTextColor(Color.GRAY);
                        imageViewFind.setImageDrawable(getDrawable(R.drawable.tab_find_frd_normal));
                        tvFind.setTextColor(Color.GRAY);
                        imageViewMe.setImageDrawable(getDrawable(R.drawable.tab_settings_pressed));
                        tvMe.setTextColor(0xff008800);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view){
        //利用在initLinearLayout（）中设置的标识符跳转页面
        mViewPager.setCurrentItem((int)view.getTag());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_bar,menu);
        return true;
    }

}
