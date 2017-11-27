package com.example.lihong.wechat;

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
 * Created by lihong on 2017/11/22.
 */

public class Fragment1 extends Fragment {
    private RecyclerView recyclerView;
    private List<String> stringList,list2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
         View view=inflater.inflate(R.layout.fragment1,container,false);

        //获取控件
         recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        //若没有LayoutManager，recyclerView就无法工作
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

         updateUi();

        return view;
    }

    private void updateUi(){
        //设置数据源
        stringList=new ArrayList<>();
        for(int i=0;i<100;i++){
            String str=new String("正标题##"+i);
            stringList.add(str);
        }
        list2=new ArrayList<>();
        for(int i=0;i<100;i++){
            String str=new String("副标题##"+i);
            list2.add(str);
        }


        //将数据源stringList设置到适配器中
        ChatAdapter adapter=new ChatAdapter(stringList,list2);
        recyclerView.setAdapter(adapter);

    }

    private class ChatHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;
        private TextView mTextView2;
        public ChatHolder(View itemView){
            super(itemView);
            mTextView= (TextView)itemView.findViewById(R.id.tv_list);
            mTextView2=itemView.findViewById(R.id.tv_list2);
        }
    }

    private class ChatAdapter extends RecyclerView.Adapter<ChatHolder>{
        private List<String> mStringList,list2;
        public ChatAdapter(List<String> stringList,List<String> list2){
            mStringList=stringList;
            this.list2=list2;
        }

        @Override
        public int getItemCount(){
            return stringList.size();
        }

        @Override
        public ChatHolder onCreateViewHolder(ViewGroup parent,int viewType){
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(R.layout.fragment1_list_item,parent,false);
            return new ChatHolder(view);
        }

        @Override
        public void onBindViewHolder(ChatHolder holder,int position){
            String str=mStringList.get(position);
            String str2=list2.get(position);
            holder.mTextView.setText(str.toString());
            holder.mTextView2.setText(str2.toString());
        }
    }

}
