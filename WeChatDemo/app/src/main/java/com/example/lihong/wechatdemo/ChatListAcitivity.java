package com.example.lihong.wechatdemo;

import android.support.v4.app.Fragment;

/**
 * Created by lihong on 2017/11/21.
 */

public class ChatListAcitivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new ChatListFragment();
    }
}
