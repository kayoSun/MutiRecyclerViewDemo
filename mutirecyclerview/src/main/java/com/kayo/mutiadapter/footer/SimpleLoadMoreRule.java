package com.kayo.mutiadapter.footer;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kayo.mutiadapter.MutiData;
import com.kayo.mutiadapter.MutiHolder;
import com.kayo.mutiadapter.R;

/**
 * Created by shilei on 17/2/4.
 * <pre>
 *      基本样式加载更多规则
 *      如需实现相关加载更多的逻辑，自行实现 loadmore()方法 即可
 * </pre>
 */

public class SimpleLoadMoreRule extends LoadMoreRule<SimpleLoadMoreRule.LoadMoreData, SimpleLoadMoreRule.LoadMoreHolder> {

    @Override
    public int layoutId() {
        return R.layout.item_load_more;
    }

    @Override
    public LoadMoreHolder holder(ViewGroup parent, int layoutId) {
        Context context = parent.getContext();
        int id = layoutId;
        return new LoadMoreHolder(LayoutInflater.from(context).inflate(id, parent, false));
    }

    @Override
    public void convert(LoadMoreHolder holder, LoadMoreData data) {
        holder.bindData(data);
        loadMore();
    }

    public void loadMore(){}

    public static class LoadMoreHolder extends MutiHolder<LoadMoreData> {

        public LoadMoreHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(LoadMoreData data) {

        }

    }

    public static class LoadMoreData extends MutiData {

        public LoadMoreData(){
            super(R.layout.item_load_more);
        }
    }
}
