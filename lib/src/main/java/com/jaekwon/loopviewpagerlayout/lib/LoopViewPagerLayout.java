package com.jaekwon.loopviewpagerlayout.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016-03-15.
 */
public class LoopViewPagerLayout extends RelativeLayout {

    public static final int CENTER = 0;
    public static final int TOP = 1;
    public static final int BOTTOM = 2;
    public static final int CENTER_LEFT = 3;
    public static final int CENTER_RIGHT = 4;
    public static final int TOP_LEFT = 5;
    public static final int TOP_RIGHT = 6;
    public static final int BOTTOM_LEFT = 7;
    public static final int BOTTOM_RIGHT = 8;

    Context mContext;

    ImageView leftClamp, rightClamp, waterMark;
    LoopViewPager loopView;
    LoopPagerAdapter adapter;

    LayoutParams waterMarkParams;

    public LoopViewPagerLayout(Context context) {
        this(context, null, 0);
    }

    public LoopViewPagerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoopViewPagerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        loopView = new LoopViewPager(mContext);
        adapter = new LoopPagerAdapter(mContext);

        addView(loopView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        /*
            left clamp icon
         */
        LayoutParams leftClampParams = new LayoutParams(dp2px(50), dp2px(50));
        leftClampParams.addRule(ALIGN_PARENT_START);
        leftClampParams.addRule(CENTER_VERTICAL);

        leftClamp = new ImageView(context);
        leftClamp.setLayoutParams(leftClampParams);
        leftClamp.setImageResource(R.drawable.icon_clamp_left);
        leftClamp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loopView.setCurrentItem(loopView.getCurrentItem()-1);
            }
        });
        addView(leftClamp);
        /*
            right clamp icon
         */
        LayoutParams rightClampParams = new LayoutParams(dp2px(50), dp2px(50));
        rightClampParams.addRule(ALIGN_PARENT_END);
        rightClampParams.addRule(CENTER_VERTICAL);

        rightClamp = new ImageView(context);
        rightClamp.setLayoutParams(rightClampParams);
        rightClamp.setImageResource(R.drawable.icon_clamp_right);
        rightClamp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loopView.setCurrentItem(loopView.getCurrentItem()+1);
            }
        });
        addView(rightClamp);
        /*
            water mark
         */
        waterMarkParams = new LayoutParams(dp2px(150), dp2px(80));
        waterMarkParams.addRule(CENTER_IN_PARENT);

        waterMark = new ImageView(context);
        waterMark.setLayoutParams(waterMarkParams);
        waterMark.setImageResource(R.drawable.icon_watermark);
        addView(waterMark);
    }

    public void setRightClampSize(int dp_width, int dp_height) {
        rightClamp.setLayoutParams(new LayoutParams(dp2px(dp_width), dp2px(dp_height)));
    }
    public void setLeftClampSize(int dp_width, int dp_height) {
        leftClamp.setLayoutParams(new LayoutParams(dp2px(dp_width), dp2px(dp_height)));
    }
    public void setRightClampGone() {
        rightClamp.setVisibility(View.GONE);
    }
    public void setLeftClampGone() {
        leftClamp.setVisibility(View.GONE);
    }
    public void setRightClampVisible() {
        rightClamp.setVisibility(View.VISIBLE);
    }
    public void setLeftClampVisible() {
        leftClamp.setVisibility(View.VISIBLE);
    }
    public void setRightClampImage(int res) {
        Picasso.with(mContext).load(res).into(rightClamp);
    }
    public void setLeftClampImage(int res) {
        Picasso.with(mContext).load(res).into(leftClamp);
    }
    public void setRightClampImage(String url) {
        Picasso.with(mContext).load(url).into(rightClamp);
    }
    public void setLeftClampImage(String url) {
        Picasso.with(mContext).load(url).into(leftClamp);
    }

    public void setWaterMarkImage(int res) {
        Picasso.with(mContext).load(res).into(waterMark);
    }
    public void setWaterMarkImage(String url) {
        Picasso.with(mContext).load(url).into(waterMark);
    }
    public void setWaterMarkSize(int dp_width, int dp_height) {
        waterMarkParams.width = dp2px(dp_width);
        waterMarkParams.height = dp2px(dp_height);
//        waterMark.setLayoutParams(waterMarkParams);
    }
    public void setWaterMarkLocation(int flag) {
//        waterMarkParams.removeRule(CENTER_IN_PARENT);
        switch (flag) {
            case TOP:
                waterMarkParams.addRule(CENTER_HORIZONTAL|ALIGN_TOP);
                break;
            case CENTER:
                waterMarkParams.addRule(CENTER_IN_PARENT);
                break;
            case BOTTOM:
                waterMarkParams.addRule(CENTER_HORIZONTAL|ALIGN_BOTTOM);
                break;
        }
    }

    public void setSliders_url(List<String> url) {
        adapter.setType(LoopPagerAdapter.URL);
        adapter.setSlider_url(url);
        loopView.setAdapter(adapter);
    }
    public void setSliders_res(List<Integer> res) {
        adapter.setType(LoopPagerAdapter.RES);
        adapter.setSlider_res(res);
        loopView.setAdapter(adapter);
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    protected int sp2px(float sp) {
        final float scale = this.mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }
}
