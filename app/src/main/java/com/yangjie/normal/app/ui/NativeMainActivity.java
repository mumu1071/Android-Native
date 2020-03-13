package com.yangjie.normal.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yangjie.normal.R;
import com.yangjie.normal.app.mvp.main.MainModel;
import com.yangjie.normal.app.mvp.main.MainPresenter;
import com.yangjie.normal.app.mvp.main.MainView;
import com.yangjie.normal.app.mvp.other.MvpActivity;
import com.yangjie.normal.app.retrofit.ApiCallback;
import com.yangjie.normal.app.retrofit.RetrofitCallback;

import retrofit2.Call;


/**
 * 由Activity/Fragment实现View里方法，包含一个Presenter的引用
 * Created by WuXiaolong on 2015/9/23.
 * github:https://github.com/WuXiaolong/
 * 微信公众号：吴小龙同学
 * 个人博客：http://wuxiaolong.me/
 */
public class NativeMainActivity extends MvpActivity<MainPresenter> implements MainView {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);
        text = findViewById(R.id.text);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }


    @Override
    public void getDataSuccess(MainModel model) {
        //接口成功回调
        dataSuccess(model);
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(getString(R.string.net_error));

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button0:
                loadDataByRetrofit();
                break;
            case R.id.button1:
                loadDataByRetrofitRxJava();
                break;
            case R.id.button2:
                //请求接口
                //MVP+Retrofit+Rxjava
                mvpPresenter.loadDataByRetrofitRxjava("101310222");
                break;
        }
    }

    /**
     * 普通写法(Retrofit)
     */
    private void loadDataByRetrofit() {
        showProgressDialog();
        Call<MainModel> call = apiStores().loadDataByRetrofit("101190201");
        call.enqueue(new RetrofitCallback<MainModel>() {
            @Override
            public void onSuccess(MainModel model) {
                dataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                toastShow(msg);
            }

            @Override
            public void onThrowable(Throwable t) {
                toastShow(t.getMessage());
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
        addCalls(call);
    }

    /**
     * 普通写法(Retrofit+Rxjava)
     */
    private void loadDataByRetrofitRxJava() {
        showProgressDialog();
        addSubscription(
                apiStores().loadDataByRetrofitRxJava("101220602"),
                new ApiCallback<MainModel>() {

                    @Override
                    public void onSuccess(MainModel model) {
                        dataSuccess(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        toastShow(msg);
                    }

                    @Override
                    public void onFinish() {
                        dismissProgressDialog();
                    }
                });
    }

    private void dataSuccess(MainModel model) {
        MainModel.WeatherinfoBean weatherinfo = model.getWeatherinfo();
        String showData = getResources().getString(R.string.city) + weatherinfo.getCity()
                + getResources().getString(R.string.wd) + weatherinfo.getWD()
                + getResources().getString(R.string.ws) + weatherinfo.getWS()
                + getResources().getString(R.string.time) + weatherinfo.getTime();
        text.setText(showData);
    }
}
