package com.yangjie.normal.demo.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yangjie.normal.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_service_activity);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MyService.class);
        switch (v.getId()) {
            case R.id.btn_start:
                startService(intent);
//                bindService(intent);
                break;
            case R.id.btn_stop:
                stopService(intent);
                break;
        }
    }
}
