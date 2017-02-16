package com.kayo.mutirecyclerviewdemo;

import android.view.View;
import android.widget.TextView;

import com.kayo.mutiadapter.MutiHolder;

/**
 * Created by shilei on 17/2/16.
 * <pre>
 *
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
