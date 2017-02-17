package com.kayo.mutirecyclerviewdemo.holder;

import android.view.View;
import android.widget.TextView;

import com.kayo.mutiadapter.MutiHolder;
import com.kayo.mutirecyclerviewdemo.DemoData;
import com.kayo.mutirecyclerviewdemo.R;

/**
 * Created by shilei on 17/2/16.
 * <pre>
 * 样式1
 * </pre>
 *
 */

public class DemoHolder extends MutiHolder<DemoData>{

    TextView textView;
    public DemoHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.text);
    }

    @Override
    public void bindData(DemoData data) {
        textView.setText(data.getText());

    }
}
