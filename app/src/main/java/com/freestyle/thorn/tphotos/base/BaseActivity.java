package com.freestyle.thorn.tphotos.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Thorn on 2018/4/10 0010.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mContext = this;
        initDataBeforeView();
        initView();
        initData();
        initEvent();
    }




    //必须复写的方法
    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();

    //可选复写的方法
    protected void initDataBeforeView() {

    }
    protected  void initEvent(){

    }
}
