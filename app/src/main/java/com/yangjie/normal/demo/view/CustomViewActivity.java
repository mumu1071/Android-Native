package com.yangjie.normal.demo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yangjie.normal.R;

public class CustomViewActivity extends AppCompatActivity {

    CustomScrollView customScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_view);

        customScrollView = findViewById(R.id.customScrollView);

        customScrollView.addView(new DrawView(this));
    }


}
