package com.yangjie.normal.demo.hook;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.yangjie.normal.R;


import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

/**
 * https://blog.csdn.net/gdutxiaoxu/article/details/81459830
 * 测试 hook demo
 */

public class HookMainActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_hook_activity_main);
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1://Hook 修改 View.OnClickListener 事件
                jump(this, TestOnClickActivity.class);
                break;
            case R.id.btn_2:
                try {
                    TestNotificationActivity.hookNotificationManager(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                testNotification();
                break;
            case R.id.btn_3:
                Toast.makeText(this, "未实现", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_4:
                Toast.makeText(this, "未实现这个", Toast.LENGTH_SHORT).show();
                break;

        }


    }

    /**
     * 测试 通知
     */
    private void testNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable
                .ic_launcher_background);
        Intent intent = new Intent(HookMainActivity.this, TestNotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(HookMainActivity.this, 0, intent,
                FLAG_UPDATE_CURRENT);
        TestNotificationActivity.notification(HookMainActivity.this, bitmap, R.mipmap.ic_launcher, "title",
                "content", "subText", 1, pendingIntent);

    }

    public static <T extends Activity> void jump(Context context, Class<T> clz) {
        Intent intent = new Intent(context, clz);
        if (false == (context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
