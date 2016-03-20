package com.jaekwon.loopviewpagerlayout.lib;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016-02-29.
 */
public class LoopPagerAdapter extends PagerAdapter {

    public final static int URL = 0;
    public final static int RES = 1;

    Context mContext;
    LayoutInflater mLayoutInflater;

    int type;
    List<Object> slider;
    List<String> slider_url;
    List<Integer> slider_res;

    public LoopPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        type = URL;
    }

    @Override
    public int getCount() {
        if (type == URL) {
            return slider_url.size();
        } else {
            return slider_res.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View convertView = mLayoutInflater.inflate(R.layout.item_viewpager, container, false);

        container.addView(loadImage(convertView, position));

        return convertView;
    }

    private View loadImage(View convertView, final int position) {

        ImageView imageView = (ImageView) convertView.findViewById(R.id.img);

        final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.loading);
        progressBar.setVisibility(View.VISIBLE);

        if(type == URL) {
            Picasso.with(mContext)
                    .load(slider_url.get(position))
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.GONE);
                        }
                        @Override
                        public void onError() {
//                            Toast.makeText(mContext, "onError " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
        } else if(type == RES) {
            Picasso.with(mContext)
                    .load(slider_res.get(position))
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.GONE);
                        }
                        @Override
                        public void onError() {
//                            Toast.makeText(mContext, "onError " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setSlider_url(List<String> slider_url) {
        this.slider_url = slider_url;
    }

    public void setSlider_res(List<Integer> slider_res) {
        this.slider_res = slider_res;
    }
}