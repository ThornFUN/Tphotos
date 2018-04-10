package com.freestyle.thorn.tphotos;

import android.support.annotation.Nullable;
import android.view.View;

import com.freestyle.thorn.tphotos.base.BaseActivity;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDataBeforeView() {
        super.initDataBeforeView();
        initLogger();//初始化Log工具
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    protected void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.IsLogShow;
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
