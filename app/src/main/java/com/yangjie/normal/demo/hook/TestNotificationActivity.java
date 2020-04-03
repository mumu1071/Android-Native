package com.yangjie.normal.demo.hook;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.yangjie.normal.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * HooK Notification
 */
public class TestNotificationActivity extends AppCompatActivity {

    private static final String TAG = "NotificationHookHelper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_hook_activity_test_notification);
    }

    public static void hookNotificationManager(final Context context) throws Exception {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService
                (Context.NOTIFICATION_SERVICE);

        Method getService = NotificationManager.class.getDeclaredMethod("getService");
        getService.setAccessible(true);
        // 第一步：得到系统的 sService
        final Object sOriginService = getService.invoke(notificationManager);

        Class iNotiMngClz = Class.forName("android.app.INotificationManager");
        // 第二步：得到我们的动态代理对象
        Object proxyNotiMng = Proxy.newProxyInstance(context.getClass().getClassLoader(), new
                Class[]{iNotiMngClz}, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Log.d(TAG, "invoke(). method:" + method);
                String name = method.getName();
                Log.d(TAG, "invoke: name=" + name);
                if (args != null && args.length > 0) {
                    for (Object arg : args) {
                        Log.d(TAG, "invoke: arg=" + arg);
                    }
                }
                Toast.makeText(context.getApplicationContext(), "检测到有人发通知了", Toast.LENGTH_SHORT)
                        .show();
                // 操作交由 sOriginService 处理，不拦截通知
                return method.invoke(sOriginService, args);
                // 拦截通知，什么也不做
                //                    return null;
                // 或者是根据通知的 Tag 和 ID 进行筛选
            }
        });
        // 第三步：偷梁换柱，使用 proxyNotiMng 替换系统的sService
        Field sServiceField = NotificationManager.class.getDeclaredField("sService");
        sServiceField.setAccessible(true);
        sServiceField.set(notificationManager, proxyNotiMng);

    }

    public static void notification(Context context, Bitmap bitmap, int smallId, CharSequence title, CharSequence content,
                                    CharSequence SubText, int id, PendingIntent pendingIntent) {
        String channelId = "subscribe";
        NotificationCompat.Builder builder=null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context,channelId);
        }else{
            builder = new NotificationCompat.Builder(context);
        }


        //设置小图标
        builder.setSmallIcon(smallId);
        //设置大图标
        builder.setLargeIcon(bitmap);
        //设置标题
        builder.setContentTitle(title);
        //设置通知正文
        builder.setContentText("这是正文，当前ID是：" + content);
        //设置摘要
        builder.setSubText("这是摘要"+SubText);
        //设置是否点击消息后自动clean
        builder.setAutoCancel(true);
        //显示指定文本
        builder.setContentInfo("Info");
        //与setContentInfo类似，但如果设置了setContentInfo则无效果
        //用于当显示了多个相同ID的Notification时，显示消息总数
        builder.setNumber(2);
        //通知在状态栏显示时的文本
        builder.setTicker("在状态栏上显示的文本");
        //设置优先级
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        //自定义消息时间，以毫秒为单位，当前设置为系统时间
        builder.setWhen(System.currentTimeMillis());
        //设置为一个正在进行的通知，此时用户无法清除通知
        builder.setOngoing(true);
        //设置消息的提醒方式，震动提醒：DEFAULT_VIBRATE     声音提醒：NotificationCompat.DEFAULT_SOUND
        //三色灯提醒NotificationCompat.DEFAULT_LIGHTS     以上三种方式一起：DEFAULT_ALL
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        //设置震动方式，延迟零秒，震动一秒，延迟一秒、震动一秒
        builder.setVibrate(new long[]{0, 1000, 1000, 1000});

        //        Intent intent = new Intent(this, ResultActivity.class);
        //        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        String channelName = "订阅消息";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager,channelId,channelName,importance);
        }
        Log.i(TAG, "notification: ");
        notificationManager.notify(id, builder.build());
    }


    @TargetApi(Build.VERSION_CODES.O)
    private static void  createNotificationChannel(NotificationManager notificationManager,String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        //        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }


}
