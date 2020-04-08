package com.yangjie.normal.demo.hooktest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yangjie.normal.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 使用 Hook 修改 View.OnClickListener 事件
 */
public class TestOnClickActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TestOnClickActivity";
    private Button mBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_hook_activity_test_on_click);
        initView();
    }


    private void initView() {
        mBtn1 = (Button) findViewById(R.id.btn_1);
        mBtn1.setOnClickListener(this);
        try {
            hookOnClickListener(mBtn1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_1:
                Log.i(TAG, "onClick: btn_1");
                break;
        }
    }

    public void hookOnClickListener(View view) throws Exception {
        // 第一步：反射得到 ListenerInfo 对象
        Method getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
        getListenerInfo.setAccessible(true);
        Object listenerInfo = getListenerInfo.invoke(view);
        // 第二步：得到原始的 OnClickListener事件方法
        Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
        Field mOnClickListener = listenerInfoClz.getDeclaredField("mOnClickListener");
        mOnClickListener.setAccessible(true);
        View.OnClickListener originOnClickListener = (View.OnClickListener) mOnClickListener.get(listenerInfo);
        // 第三步：用Hook代理类 替换原始的 OnClickListener
        View.OnClickListener hookedOnClickListener = new HookedClickListenerProxy(originOnClickListener);
        mOnClickListener.set(listenerInfo, hookedOnClickListener);
    }

    class HookedClickListenerProxy implements View.OnClickListener {

        private View.OnClickListener origin;

        public HookedClickListenerProxy(View.OnClickListener origin) {
            this.origin = origin;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Hook Click Listener", Toast.LENGTH_SHORT).show();
            if (origin != null) {
                origin.onClick(v);
            }
        }

    }
}
