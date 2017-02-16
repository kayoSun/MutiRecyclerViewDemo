package com.kayo.mutiadapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shilei on 17/1/11.
 * <pre>
 *      recyclerview
 *     数据条目展示
 *     用法： 可是当成基本的recyclerview使用
 *     普通用法：
 *          List<DemoData> dataList = new ArrayList<>();  //数据列表  DemoData需继承 {@link MutiData}
 *          recyclerView = (MutiRecyclerView) findViewById(R.id.recycler_view);
 *          recyclerView.addColumnRule(new ColumnRule(R.layout.layout_item,1));  //设置条目占位规则
 *          DemoAdapter adapter = new DemoAdapter(this);   //数据适配器，需继承 {@link MutiAdapter}
 *          adapter.setData(dataList);
 *          recyclerView.setAdapter(adapter);
 *     管理者用法：
 *        {@link com.kayo.mutiadapter.rules.AdapterRulesManager}
 *                  AdapterRulesManager<DemoData> manager = new AdapterRulesManager<>();
 *                    manager.bindRecyclerView(recyclerView);
 *                    manager.setDatas(dataList);
 *                    manager.addRule(new Rule() {
 *                           @Override
 *                           public int layoutId() {
 *                              return R.layout.layout_item;
 *                           }
 *
 *                           @Override
 *                           public MutiHolder holder(ViewGroup parent, int layoutId) {
 *                              return new DemoHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false));
 *                           }
 *
 *                           @Override
 *                           public void convert(MutiHolder holder, MutiData data) {
 *                              holder.bindData(data);
 *                           }
 *                           });
 *                            manager.addFooter(new SimpleLoadMoreRule(){
*                                  @Override
*                                  public void loadMore() {
*                                  super.loadMore();
*                                  }
 *                           });
 *                    manager.show();
 *
 * </pre>
 */

public class MutiRecyclerView extends RecyclerView {

    private MutiItmHelper mutiItmHelper;

    public MutiRecyclerView(Context context) {
        this(context,null);
    }

    public MutiRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MutiRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mutiItmHelper = new MutiItmHelper(this);
    }

    @Deprecated
    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
    }

    public void setAdapter(MutiAdapter adapter) {
        super.setAdapter(adapter);
    }

    /**
     * 设置条目的最大展示 列数或行数
     * @param column 列数或行数
     */
    public void setColumn(int column) {
        mutiItmHelper.setColumn(column);
    }

    /**
     * 条件调试展示规则
     * @param columnRule  规则
     */
    public void addColumnRule(ColumnRule columnRule) {
        mutiItmHelper.addColumnRule(columnRule);
    }

    public MutiItmHelper getMutiItmHelper() {
        return mutiItmHelper;
    }

    public int getColumn() {
        return mutiItmHelper.getColumn();
    }

    //MutiListView 条目 帮助类
    public static class MutiItmHelper {

        private List<ColumnRule> columnRules;
        private SparseIntArray rulesInt;
        private MutiRecyclerView parent;
        private RecyclerView.LayoutManager layoutManager;
        private int column = 1;

        private MutiItmHelper(MutiRecyclerView parent) {
            this.parent = parent;
        }

        public void bindParent(MutiRecyclerView parent) {
            this.parent = parent;
            setColumn(column);
        }

        private void addColumnRule(ColumnRule columnRule) {
            if (null == columnRule){
                return;
            }
            if (column < columnRule.getRule()){
                column = columnRule.getRule();
                setColumn(column);
            }
            if (null == columnRules) {
                columnRules = new ArrayList<>();
                setColumn(column);
            }
            columnRules.add(columnRule);
            if (null == rulesInt) {
                rulesInt = new SparseIntArray();
            }
            rulesInt.put(columnRule.getType(), columnRule.getRule());
        }

        private void setColumn(int column) {
            this.column = column;
            if (parent == null){
                return;
            }
            if (column <= 1) {
                layoutManager = new LinearLayoutManager(parent.getContext());
            } else {
                layoutManager = new GridLayoutManager(parent.getContext(), column);
                lookAtItem((GridLayoutManager) layoutManager);
            }
            parent.setLayoutManager(layoutManager);
        }

        private void lookAtItem(GridLayoutManager manager) {
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                          @Override
                                          public int getSpanSize(int position) {
                                              if (null == rulesInt){
                                                  return 1;
                                              }
                                              if (null == parent){
                                                  return 1;
                                              }
                                              if (null == parent.getAdapter()){
                                                  return 1;
                                              }
                                              int itemViewType = parent.getAdapter().getItemViewType(position);
                                              return rulesInt.get(itemViewType);
                                          }
                                      }
            );
        }

        private int getColumn() {
            return column;
        }
    }


}
