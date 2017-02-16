package com.kayo.mutirecyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kayo.mutiadapter.MutiAdapter;
import com.kayo.mutiadapter.MutiHolder;

/**
 * Created by shilei on 17/2/16.
 * <pre>
 *
 * </pre>
 */

public class DemoAdapter extends MutiAdapter<DemoData>{

    public DemoAdapter(Context context) {
        super(context);
    }

    @Override
    public MutiHolder onCreateMutiHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new DemoHolder(inflater.inflate(viewType,parent,false));
    }

    @Override
    public void onBindMutiData(MutiHolder holder, DemoData data, int position) {
        holder.bindData(data);
    }
}
