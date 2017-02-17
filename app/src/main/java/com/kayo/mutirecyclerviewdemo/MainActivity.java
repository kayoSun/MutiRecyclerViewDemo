package com.kayo.mutirecyclerviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kayo.mutiadapter.ColumnRule;
import com.kayo.mutiadapter.MutiRecyclerView;
import com.kayo.mutiadapter.footer.SimpleLoadMoreRule;
import com.kayo.mutiadapter.rules.AdapterRulesManager;
import com.kayo.mutiadapter.rules.Rule;
import com.kayo.mutirecyclerviewdemo.holder.DemoHolder;
import com.kayo.mutirecyclerviewdemo.holder.DemoHolder2;
import com.kayo.mutirecyclerviewdemo.holder.DemoHolder3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class MainActivity extends AppCompatActivity {
    int[] layoutId = new int[]{R.layout.layout_item,R.layout.layout_item2,R.layout.layout_item3};
    MutiRecyclerView recyclerView;
    List<DemoData> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData(20);
        recyclerView = (MutiRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addColumnRule(new ColumnRule(R.layout.layout_item,2));
        recyclerView.addColumnRule(new ColumnRule(R.layout.layout_item2,1));
        recyclerView.addColumnRule(new ColumnRule(R.layout.layout_item3,2));
//        DemoAdapter adapter = new DemoAdapter(this);
//        adapter.setData(dataList);
//        recyclerView.setAdapter(adapter);
        AdapterRulesManager<DemoData> manager = new AdapterRulesManager<>();
        manager.bindRecyclerView(recyclerView);
        manager.setDatas(dataList);
        //添加样式规则1
        manager.addRule(new Rule<DemoData,DemoHolder>() {
            @Override
            public int layoutId() {
                return R.layout.layout_item;
            }

            @Override
            public DemoHolder holder(ViewGroup parent, int layoutId) {
                return new DemoHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false));
            }

            @Override
            public void convert(DemoHolder holder, DemoData data) {
                holder.setText(R.id.text,data.getText());
            }
        });
        //添加样式规则2
        manager.addRule(new Rule<DemoData,DemoHolder2>(){
            @Override
            public int layoutId() {
                return R.layout.layout_item2;
            }

            @Override
            public DemoHolder2 holder(ViewGroup parent, int layoutId) {
                return new DemoHolder2(LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false));
            }

            @Override
            public void convert(DemoHolder2 holder, DemoData data) {

            }
        });
        //添加样式规则3
        manager.addRule(new Rule<DemoData, DemoHolder3>() {
            @Override
            public int layoutId() {
                return R.layout.layout_item3;
            }

            @Override
            public DemoHolder3 holder(ViewGroup parent, int layoutId) {
                return new DemoHolder3(LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false));
            }

            @Override
            public void convert(DemoHolder3 holder, DemoData data) {

            }
        });
        //添加脚布局
        manager.addFooter(new SimpleLoadMoreRule(){
            @Override
            public void loadMore() {
                super.loadMore();
            }
        });
        manager.show();

    }

    private void initData(int count){
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            DemoData data = new DemoData(R.layout.layout_item);
            data.setItemUIID(layoutId[random.nextInt(layoutId.length)]);
            data.setText("测试条目数据 == "+i);
            dataList.add(data);
        }
    }


}
