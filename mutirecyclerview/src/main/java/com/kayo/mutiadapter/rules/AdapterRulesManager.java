package com.kayo.mutiadapter.rules;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.kayo.mutiadapter.ColumnRule;
import com.kayo.mutiadapter.MutiData;
import com.kayo.mutiadapter.MutiHolder;
import com.kayo.mutiadapter.MutiRecyclerView;
import com.kayo.mutiadapter.footer.LoadMoreRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shilei on 17/2/3.
 * <pre>
 *      适配器规则管理器
 *      用法：
 *          step1:
 *              bindRecyclerView(MutiRecyclerView view)
 *          step2:
 *              setDatas(List<D> datas )
 *          step3:
 *              addRule(Rule rule) 添加规则，数据与条目数据绑定规则
 *          step4:
 *              show() 显示一下
 *          其他:
 *              如果需要加载更多的逻辑，添加脚即可，即：
 *              addFooter(LoadMoreRule rule)  {@link LoadMoreRule}
 * </pre>
 */

public class AdapterRulesManager<D extends MutiData> {

    private List<D> datas = new ArrayList<>();
    private SparseArray<Rule> ruleSparseArray =  new SparseArray<>();
//    private Map<Integer,Rule> ruleMap = new HashMap<>();

    private MutiRecyclerView recyclerView;
    private Adapter adapter;
    private int footerType;
    private boolean hasFooter;
    private boolean hasHeader;

    public AdapterRulesManager() {
        adapter = new Adapter(this);
    }

    public AdapterRulesManager bindRecyclerView(MutiRecyclerView view){
        recyclerView = view;
        return this;
    }

    public AdapterRulesManager addRule(Rule rule){
        int i = rule.layoutId();
//        if (!ruleMap.containsKey(i)){
//            ruleMap.put(i,rule);
//        }
        Rule rule1 = ruleSparseArray.get(i);
        if (rule1 == null){
            ruleSparseArray.put(i,rule);
        }
        return this;
    }

    public void addData(D data){
        datas.add(data);
        adapter.notifyDataSetChanged();
    }
    //noinspection unchecked
    public AdapterRulesManager setDatas(List<D> datas ){
        this.datas.clear();
        this.datas.addAll(datas);
        return this;
    }

    public AdapterRulesManager addDatas(List<D> datas ){
        if (datas != null){
            this.datas.addAll(datas);
            adapter.notifyDataSetChanged();
        }
        return this;
    }

    public MutiHolder getHolder(ViewGroup parent, int viewType){
        Rule rule = ruleSparseArray.get(viewType);
        return rule.holder(parent,rule.layoutId());
    }

    public void bindData(MutiHolder holder,int position){
        Rule rule = ruleSparseArray.get(getType(position));
        rule.convert(holder,getItem(position));
    }

    public int getCount(){
        int count = 0;
        if (hasFooter){
            count++;
        }
        if (hasHeader){
            count++;
        }
        count+= datas.size();
        return count;
    }

    public int getType(int position){
        if (hasFooter && position == getCount()-1){
            return footerType;
        }
        return datas.get(position).getItemUIID();
    }

    public MutiData getItem(int position){
        if (hasFooter && position == getCount()-1){
            return null;
        }
        return datas.get(position);
    }

    public long getId(int position){
        return 0;
    }

    public void onViewAttachedToWindow(MutiHolder holder) {

    }

    public void show(){
        recyclerView.setAdapter(adapter);
    }

    public AdapterRulesManager addFooter(LoadMoreRule rule){
        if (rule !=null){
            hasFooter = true;
            footerType = rule.layoutId();
            recyclerView.addColumnRule(new ColumnRule(rule.layoutId(),recyclerView.getColumn()));
            addRule(rule);
        }
        return this;
    }

    public void addHeader(){}

    /**
     * 插入数据
     * @param data 数据
     * @param position 插入位置
     */
    public void insertData(D data,int position){
        if (datas == null || datas.size() <position){
            return;
        }
        datas.add(position,data);
        adapter.notifyItemInserted(position);
    }

    /**
     * 移除相关位置的条目
     * @param position 要移除的位置
     */
    public void removeData(int position){
        if (datas == null || datas.size()-1<position){
            return;
        }
        datas.remove(position);
        adapter.notifyItemRemoved(position);
    }


    public Adapter getAdapter() {
        return adapter;
    }
}
