package com.yangjie.normal.demo.provider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yangjie.normal.R;
import com.yangjie.normal.demo.listview.ListViewActivity;


public class DemoContentProviderActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_demo_list_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DemoContentProviderActivity.this, ListViewActivity.class));
            }
        });


    }

    public void testProvider(){
        Uri boyUri = Uri.parse("content://com.zyc.hezuo.contentproviderdemo.MyFirstContentProvider/boy");

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "张三");
        //getContentResolver().insert(boyUri, contentValues);
        //getContentResolver().delete(boyUri, )
        Cursor boyCursor = getContentResolver().query(boyUri, new String[]{"_id", "name"}, null, null, null);
        if (boyCursor != null) {
            while (boyCursor.moveToNext()) {
                Log.e("yunchong", "ID:" + boyCursor.getInt(boyCursor.getColumnIndex("_id")) + "  name:" + boyCursor.getString(boyCursor.getColumnIndex("name")));
            }
            boyCursor.close();
        }

        Uri girlUri = Uri.parse("content://com.zyc.hezuo.contentproviderdemo.MyFirstContentProvider/girl");
        contentValues.clear();
        //contentValues.put("name", "李四");
        //getContentResolver().insert(girlUri, contentValues);
        Cursor girlCursor = getContentResolver().query(girlUri, new String[]{"_id", "name"}, null, null, null);
        if (girlCursor != null) {
            while (girlCursor.moveToNext()) {
                Log.e("yunchong", "ID:" + girlCursor.getInt(girlCursor.getColumnIndex("_id"))
                        + "  name:" + girlCursor.getString(girlCursor.getColumnIndex("name")));
            }
            girlCursor.close();
        }
    }
}
