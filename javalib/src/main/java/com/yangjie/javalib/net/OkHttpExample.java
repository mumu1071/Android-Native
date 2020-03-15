package com.yangjie.javalib.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author yangjie
 * @description:
 * @date :2020/3/14 9:13 PM
 */
public class OkHttpExample {
    OkHttpClient client = new OkHttpClient(); // 构建共享的Client

    //同步请求
    String run(String url) throws IOException {
        Request request = new Request.Builder() // 构建request
                .url(url)
                .build();
        // 构建Call，执行
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    //异步请求
    public void run() throws Exception {
        // 构建Request
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();
        // 构建Call，执行，回调接受处理

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    System.out.println(responseBody.string());
                }
            }
        });
    }


    public static void main(String[] args) throws IOException {
        OkHttpExample example = new OkHttpExample();
        String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }

}
