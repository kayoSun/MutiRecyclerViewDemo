package com.kayo.mutirecyclerviewdemo;

import android.support.annotation.LayoutRes;

import com.kayo.mutiadapter.MutiData;

/**
 * Created by shilei on 17/2/16.
 * <pre>
 *
 * </pre>
 */

public class DemoData extends MutiData{

    String text;
    public DemoData(@LayoutRes int itemType) {
        super(itemType);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
