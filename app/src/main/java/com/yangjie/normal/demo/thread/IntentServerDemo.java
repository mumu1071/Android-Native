package com.yangjie.normal.demo.thread;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 1：需要在mainfest中注册服务；<service android:name=".IntentServerDemo"/>
 * 2： Intent intent = new Intent(MainActivity.this, IntentServerDemo.class);
 * intent.putExtra("action","TASK1"); startService(intent);
 */
public class IntentServerDemo extends IntentService {

    /**
     * Error:has no zero argument constructor
     */
    public IntentServerDemo() {
        super("");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentServerDemo(String name) {
        super(name);
        Log.i("bugtags", ">>>IntentServerDemo==" + name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("bugtags", ">>>onCreate");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("bugtags", ">>>onHandleIntent=" + intent.getStringExtra("action"));
        SystemClock.sleep(5000);
    }

    @Override
    public void onDestroy() {
        Log.i("bugtags", ">>>onDestroy");
        super.onDestroy();
    }

}