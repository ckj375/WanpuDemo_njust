package com.ckj.njustclassroom;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.umeng.analytics.MobclickAgent;

import cn.waps.AppConnect;
import cn.waps.AppListener;

public class MainActivity extends Activity {

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppConnect.getInstance(this);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConnect.getInstance(MainActivity.this).showOffers(MainActivity.this);
            }
        });

//        AppConnect.getInstance(this).initPopAd(this);
//        AppConnect.getInstance(this).setPopAdNoDataListener(new AppListener(){
//            @Override
//            public void onPopNoData() {
//                Log.e("ckjc", "插屏广告已经没有数据");
//            }
//        });
//        AppConnect.getInstance(this).showPopAd(this);

        AppConnect.getInstance(this).setBannerAdNoDataListener(new AppListener() {
            @Override
            public void onBannerNoData() {
                Log.e("ckjc", "Banner广告无数据");
            }
        });
        LinearLayout adlayout = (LinearLayout) findViewById(R.id.AdLinearLayout);
        AppConnect.getInstance(this).showBannerAd(this, adlayout);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppConnect.getInstance(this).close();
    }
}
