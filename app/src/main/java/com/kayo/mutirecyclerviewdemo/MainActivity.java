package com.kayo.mutirecyclerviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kayo.mutiadapter.ColumnRule;
import com.kayo.mutiadapter.MutiData;
import com.kayo.mutiadapter.MutiHolder;
import com.kayo.mutiadapter.MutiRecyclerView;
import com.kayo.mutiadapter.footer.SimpleLoadMoreRule;
import com.kayo.mutiadapter.rules.AdapterRulesManager;
import com.kayo.mutiadapter.rules.Rule;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MutiRecyclerView recyclerView;
    List<DemoData> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData(20);
        recyclerView = (MutiRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addColumnRule(new ColumnRule(R.layout.layout_item,1));
        DemoAdapter adapter = new DemoAdapter(this);
        adapter.setData(dataList);
        recyclerView.setAdapter(adapter);
        AdapterRulesManager<DemoData> manager = new AdapterRulesManager<>();
        manager.bindRecyclerView(recyclerView);
        manager.setDatas(dataList);
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
                holder.setText(R.id.text,data.getText())
                        .setBackgroudColor(R.id.text, Color.YELLOW);
            }
        });
        manager.addFooter(new SimpleLoadMoreRule(){
            @Override
            public void loadMore() {
                super.loadMore();
            }
        });
        manager.show();

    }

    private void initData(int count){
        for (int i = 0; i < count; i++) {
            DemoData data = new DemoData(R.layout.layout_item);
            data.setText("测试条目数据 == "+i);
            dataList.add(data);
        }
    }

}
