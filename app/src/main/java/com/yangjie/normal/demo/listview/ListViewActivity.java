package com.yangjie.normal.demo.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangjie.normal.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ListViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_list);


        //初始化数据
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("item " + i);
        }

        recyclerView = findViewById(R.id.recycler_view);
        //设置LayoutManager为LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //设置Adapter
        recyclerView.setAdapter(new GeneralAdapter(this, datas));
        layoutManager.scrollToPosition(3);


    }

    class GeneralAdapter extends RecyclerView.Adapter<GeneralAdapter.MyViewHolder> {
        //当前上下文对象
        Context context;
        //RecyclerView填充Item数据的List对象
        List<String> datas;

        public GeneralAdapter(Context context, List<String> datas) {
            this.context = context;
            this.datas = datas;
        }

        //创建ViewHolder
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //实例化得到Item布局文件的View对象
            View v = View.inflate(context, R.layout.demo_item_list_recycler, null);
            //返回MyViewHolder的对象
            return new MyViewHolder(v);
        }

        //绑定数据
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.textView.setText(datas.get(position));
        }

        //返回Item的数量
        @Override
        public int getItemCount() {
            return datas.size();
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
            Log.i("yangjie", datas.get(position));
            super.onBindViewHolder(holder, position, payloads);
        }


        //继承RecyclerView.ViewHolder抽象类的自定义ViewHolder
        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public MyViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.text);
            }
        }
    }

    class Advertising {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

        //未上报
        Map<String,String> commitMap = new HashMap<>();

        //已上报
        Map<String,String> commitedMap = new HashMap<>();

        //清空

        public void commit(String item) {
            //校验是否已上报
            commitedMap.containsKey(item);
            //
            scheduledThreadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    //是否已上报

                    //再校验
//                    commitMap.containsKey()

                    System.out.println("delay 3 seconds");
                }
            }, 3, TimeUnit.SECONDS);
        }

    }


}
