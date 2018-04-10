package com.freestyle.thorn.tphotos.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Thorn on 2018/4/10 0010.
 */

public abstract class BaseFragment extends Fragment{
    protected BaseActivity mActivity;//这里不理解为什么一定要强转成BaseActivity，意义是什么？？？

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) this.getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLyoutId(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
    }

    protected abstract int getLyoutId();

    protected abstract void initView(View view);

    protected abstract void initData(Bundle savedInstanceState);
}
