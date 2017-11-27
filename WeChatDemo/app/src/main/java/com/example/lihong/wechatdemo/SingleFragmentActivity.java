package com.example.lihong.wechatdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * Created by lihong on 2017/11/21.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.fragment_container);

        if(fragment==null){
            fragment=createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_title,menu);
        return true;
    }

}
