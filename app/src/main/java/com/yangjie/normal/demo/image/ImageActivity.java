package com.yangjie.normal.demo.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.yangjie.normal.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class ImageActivity extends AppCompatActivity {

    ImageView imageView;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            imageView.setImageBitmap((Bitmap) msg.obj);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_image);
        imageView= (ImageView) findViewById(R.id.imageview_test);
        setBitmapFromNet();

    }

    public  Bitmap setBitmapFromDisk(){
        BitmapFactory.Options options=new BitmapFactory.Options();
        //设置只加载图片的格式尺寸信息到内存，不加载具体的图片字节。
        options.inJustDecodeBounds=true;
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.demo_image_test,options);
        //获取图片的高度和宽度
        int  height=options.outHeight;
        int  width=options.outWidth;
        //获取图片的类型
        String imageType = options.outMimeType;
        Log.d("imageTest",height+"    "+width+"   "+imageType);
        System.out.println(height+"    "+width+"   "+imageType);
        //长&&宽压缩的比例,内存占用的比例关系是平方倍
        options.inSampleSize=2;
        options.inJustDecodeBounds=false;
        bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.demo_image_test,options);
        return bitmap;
    }

    public void setBitmapFromNet(){
        Thread t =new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url=new URL("[http://img.taopic.com/uploads/allimg/130501/240451-13050106450911.jpg](http://img.taopic.com/uploads/allimg/130501/240451-13050106450911.jpg)");
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    System.out.println("    code :"+connection.getResponseCode());
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    //设置只加载图片的格式尺寸信息到内存，不加载具体的图片字节。
                    options.inJustDecodeBounds=true;
                    //获取图片的高度和宽度
                    InputStream is=connection.getInputStream();
                    Bitmap bitmap=BitmapFactory.decodeStream(is,null,options);
                    int  height=options.outHeight;
                    int  width=options.outWidth;
                    //获取图片的类型
                    String imageType = options.outMimeType;
                    Log.d("imageTest",height+"    "+width+"   "+imageType);
                    System.out.println(height+"    "+width+"   "+imageType);

                    connection= (HttpURLConnection) url.openConnection();
                    options.inJustDecodeBounds=false;
                    options.inSampleSize=2;
                    //获取图片的高度和宽度
                    bitmap=BitmapFactory.decodeStream(connection.getInputStream(),null,options);
                    Message m=new Message();
                    m.obj=bitmap;
                    handler.sendMessage(m);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

}

