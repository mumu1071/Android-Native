package com.yangjie.normal.demo.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TabHost;

import com.yangjie.normal.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentTabHostActivity extends FragmentActivity {
    private static final String TAG = "FragmentTabHostActivity";
    @BindView(R.id.tabContent)
    FrameLayout tabContent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;
    private String tabSpecs[] = {"首页", "消息", "好友", "广场"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_fragment_tab_host);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tabhost.setup(this, getSupportFragmentManager(), R.id.tabContent);
        for (int i = 0; i < tabSpecs.length; i++) {
            TabHost.TabSpec tabSpec = tabhost.newTabSpec(tabSpecs[i]).setIndicator(tabSpecs[i]);
            Bundle b = new Bundle();
            b.putString("data", tabSpecs[i]);
            tabhost.addTab(tabSpec, MyFirstFragment.class, b);
        }
        tabhost.setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
        super.onActivityResult(requestCode, resultCode, data);
    }
}