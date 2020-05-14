package com.yangjie.normal.demo.page;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yangjie.normal.R;

public class PageActivity extends AppCompatActivity {

    PageReader pageReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        pageReader = findViewById(R.id.page_reader);
    }
}
