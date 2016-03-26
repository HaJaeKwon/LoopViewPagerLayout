package com.jaekwon.loopviewpagerlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jaekwon.loopviewpagerlayout.lib.LoopViewPagerLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> slider_url = new ArrayList<>();
    List<Integer> slider_res = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoopViewPagerLayout layout = (LoopViewPagerLayout) findViewById(R.id.loopViewPagerLayout);

//        slider_url.add("https://raw.githubusercontent.com/HaJaeKwon/GnBangExam/master/app/src/main/res/drawable/room1_1.png");
//        slider_url.add("https://raw.githubusercontent.com/HaJaeKwon/GnBangExam/master/app/src/main/res/drawable/room2_1.png");
//        slider_url.add("https://raw.githubusercontent.com/HaJaeKwon/GnBangExam/master/app/src/main/res/drawable/room3_1.png");
//        layout.setSliders_url(slider_url);

        slider_res.add(R.drawable.background_1);
        slider_res.add(R.drawable.background_2);
        slider_res.add(R.drawable.background_3);
        layout.setSliders_res(slider_res);

        layout.setLeftClampImage(R.drawable.icon_clamp_right);
        layout.setLeftClampSize(100, 100);
        layout.setLeftClampGone();
        layout.setLeftClampVisible();
        layout.setRightClampImage(R.drawable.icon_clamp_left);
        layout.setRightClampSize(100, 100);

        layout.setWaterMarkImage(R.drawable.icon_watermark);
        layout.setWaterMarkSize(50, 100);
        layout.setWaterMarkGone();
    }
}
