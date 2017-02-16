package com.kayo.mutiadapter;

import android.support.annotation.LayoutRes;

/**
 * Created by shilei on 17/1/11.
 * <pre>
 *
 * </pre>
 */

public class MutiData implements IMutiData{
    /**
     * 指定 条目数据类型 一般指定为相应的布局文件ID
     */
    @LayoutRes
    private int itemUIID;
    private long itemId;

    public MutiData(@LayoutRes int itemUIID) {
        this.itemUIID = itemUIID;
    }

    /**
     * 这个方法是MutiRecyclerView的非常重要的方法，相关逻辑依托于此方法
     * 这里返回条目数据索要绑定的UI
     * 可根据自己的相应需求进行重写
     * @return 返回相应的UIID
     */
    @LayoutRes
    public int getItemUIID() {
        return itemUIID;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemUIID(@LayoutRes int itemUIID) {
        this.itemUIID = itemUIID;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
