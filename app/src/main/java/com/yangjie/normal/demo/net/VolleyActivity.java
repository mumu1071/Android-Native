package com.yangjie.normal.demo.net;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yangjie.normal.R;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class VolleyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


    }

    RequestQueue requestQueue = Volley.newRequestQueue(this);

    //Volley同步网络请求，要在线程中去请求
    public void testOne(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestFuture<String> future = RequestFuture.newFuture();
                StringRequest stringRequest = new StringRequest("http://ofmudsqae.bkt.clouddn.com/mynewqq1.text",future,future);
                requestQueue.add(stringRequest);
                try {
                    String request = future.get();
                    future.get(3000, TimeUnit.SECONDS);//添加请求超时

                    Log.e("------------>","同步"+request);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //Volley的正常请求，不用在子线程中去请求
    public void testTwo(View view){
        StringRequest request = new StringRequest("http://ofmudsqae.bkt.clouddn.com/mynewqq1.text", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("------------->","异步"+s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        requestQueue.add(request);
    }
}
