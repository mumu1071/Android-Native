package com.yangjie.normal.demo;

import android.app.DownloadManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wuxiaolong.androidutils.library.DownloadUtil;
import com.yangjie.normal.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        StrictMode.VmPolicy.Builder localBuilder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(localBuilder.build());
    }
}
