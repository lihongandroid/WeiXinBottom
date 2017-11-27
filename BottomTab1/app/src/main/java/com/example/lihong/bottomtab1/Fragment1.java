package com.example.lihong.bottomtab1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihong on 2017/11/21.
 */

public class Fragment1 extends Fragment {

    private RecyclerView mChatRecyclerView;
    private ChatAdapter mChatAdapter;

    private List<String> strList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment1,container,false);

        mChatRecyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        updaterUI();

        return view;
    }

    private void updaterUI(){

        strList=new ArrayList<>();
        for(int i=0;i<100;i++){
            String str=new String("chat"+i);
            strList.add(str);
        }

        mChatAdapter=new ChatAdapter(strList);
        mChatRecyclerView.setAdapter(mChatAdapter);

    }

    private class ChatHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ChatHolder(View itemView){
            super(itemView);
            mTextView=(TextView)itemView;
        }
    }

    private class ChatAdapter extends RecyclerView.Adapter<ChatHolder>{
        private List<String> mStringList;

        public ChatAdapter(List<String> stringList){
            mStringList=stringList;
        }

        @Override
        public int getItemCount(){
            return mStringList.size();
        }

        @Override
        public ChatHolder onCreateViewHolder(ViewGroup parent,int viewGroup){
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(android.R.layout.simple_list_item_1,parent,false);
            return new ChatHolder(view);
        }


        @Override
        public void onBindViewHolder(ChatHolder holder,int position){
            String str=mStringList.get(position);
            holder.mTextView.setText(str.toString());
        }
    }
}
