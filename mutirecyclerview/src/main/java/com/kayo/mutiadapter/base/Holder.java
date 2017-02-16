package com.kayo.mutiadapter.base;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kayo.mutiadapter.MutiHolder;

/**
 * Created by shilei on 17/2/16.
 * <pre>
 *
 * </pre>
 */

public class Holder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;

    public Holder(View itemView) {
        super(itemView);
        views = new SparseArray<>();
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId 控件id
     * @return 相应控件
     */
    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        //noinspection unchecked
        return (T) view;
    }

    public Holder setText(@IdRes int viewId, @Nullable String text){
        if (!TextUtils.isEmpty(text)){
            TextView textView = getView(viewId);
            if (textView != null){
                textView.setText(text);
            }
        }else {
            Log.e("setText","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setTextColor(@IdRes int viewId,@ColorInt int color){
        TextView view = getView(viewId);
        if (view !=null){
            view.setTextColor(color);
        }else {
            Log.e("setTextColor","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setTextColorRes(@IdRes int viewId,@ColorRes int colorId){
        TextView view = getView(viewId);
        if (view != null){
            view.setTextColor(itemView.getContext().getResources().getColor(colorId));
        }else {
            Log.e("setTextColorRes","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    /**
     * 设置字体大小
     * @param viewId 控件id
     * @param size 字体尺寸 单位sp
     */
    public Holder setTextSize(@IdRes int viewId,int size){
        TextView view = getView(viewId);
        if (view != null){
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        }else {
            Log.e("setTextSize","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setBackgroudColor(@IdRes int viewId,@ColorInt int color){
        View view = getView(viewId);
        if (view != null){
            view.setBackgroundColor(color);
        }else {
            Log.e("setBackgroudColor","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setBackgroundRes(@IdRes int viewId,@DrawableRes int backgroundRes) {
        View view = getView(viewId);
        if (view != null){
            view.setBackgroundResource(backgroundRes);
        }else {
            Log.e("setBackgroundRes","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setImageResource(@IdRes int viewId,@DrawableRes int resId){
        ImageView view = getView(viewId);
        if (view != null){
            view.setImageResource(resId);
        }else {
            Log.e("setImageResource","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setImageBitmap(@IdRes int viewId,Bitmap bitmap){
        ImageView view = getView(viewId);
        if (view != null){
            view.setImageBitmap(bitmap);
        }else {
            Log.e("setImageBitmap","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setImageDrawable(@IdRes int viewId,Drawable drawable){
        ImageView view = getView(viewId);
        if (view != null){
            view.setImageDrawable(drawable);
        }else {
            Log.e("setImageDrawable","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setAlpha(@IdRes int viewId,float alpha){
        View view = getView(viewId);
        if (view != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                view.setAlpha(alpha);
            }else {
                // Pre-honeycomb hack to set Alpha value
                AlphaAnimation anim = new AlphaAnimation(alpha, alpha);
                anim.setDuration(0);
                anim.setFillAfter(true);
                view.startAnimation(anim);
            }
        } else {
            Log.e("setAlpha","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    /**
     * 设置 控件的显示状态  只有VISIBLE  和 GONE状态
     * @param viewId 控件ID
     * @param visible 显示状态
     */
    public Holder setVisibility(@IdRes int viewId,boolean visible){
        View view = getView(viewId);
        if (view != null){
            view.setVisibility(visible?View.VISIBLE:View.GONE);
        }else {
            Log.e("setVisibility","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setProgress(@IdRes int viewId, int progress) {
        ProgressBar view = getView(viewId);
        if (view != null) {
            view.setProgress(progress);
        }else {
            Log.e("setProgress","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }
    public Holder setProgress(@IdRes int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        if (view != null) {
            view.setMax(max);
            view.setProgress(progress);
        }else {
            Log.e("setProgress","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setProgressMax(@IdRes int viewId, int max) {
        ProgressBar view = getView(viewId);
        if (view != null) {
            view.setMax(max);
        }else {
            Log.e("setProgressMax","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setTag(@IdRes int viewId, Object tag) {
        View view = getView(viewId);
        if (view !=null){
            view.setTag(tag);
        }else {
            Log.e("setTag","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setTag(@IdRes int viewId, int key, Object tag) {
        View view = getView(viewId);
        if (view != null) {
            view.setTag(key, tag);
        }else {
            Log.e("setTag","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setOnClickListener(@IdRes int viewId,
                                         View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null){
            view.setOnClickListener(listener);
        }else {
            Log.e("setOnClickListener","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setOnTouchListener(@IdRes int viewId,
                                         View.OnTouchListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnTouchListener(listener);
        }else {
            Log.e("setOnTouchListener","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

    public Holder setOnLongClickListener(@IdRes int viewId,
                                             View.OnLongClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnLongClickListener(listener);
        }else {
            Log.e("setOnLongClickListener","控件id = "+viewId+"   控件为null,请自行查看验证。。。");
        }
        return this;
    }

}
