package com.freestyle.thorn.tphotos;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.freestyle.thorn.tphotos.api.PhotoApi;
import com.freestyle.thorn.tphotos.bean.PhotoBean;
import com.freestyle.thorn.tphotos.loader.PhotoLoader;
import com.freestyle.thorn.tphotos.loader.PictureLoader;
import com.freestyle.thorn.tphotos.base.BaseActivity;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private Button btnNextButton;
    private ImageView ivPhoto;
    private Button btnNextPage;
    private List<String> photoUrlList;

    private PhotoLoader mPhotoLoader;
    private PictureLoader mPictureLoader;
    private PhotoApi mPhotoApi;
    private int clickCount = 0;

    private static final int INIT_STATE = 0;
    private static final int NEXT_STATE = 1;

    private int mPage;

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
        btnNextButton = findViewById(R.id.btn_NextPhoto);
        btnNextPage = findViewById(R.id.btn_NextPage);
        ivPhoto = findViewById(R.id.iv_Photo);

        btnNextButton.setOnClickListener(this);
        btnNextPage.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        photoUrlList = new ArrayList<>();
        mPhotoApi = new PhotoApi();
        mPictureLoader = new PictureLoader();
        mPage = 1;
        fetchNextPage(mPage,INIT_STATE);//初始化图片列表
        Logger.d(mPage);
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
        switch (view.getId()){
            case R.id.btn_NextPhoto:
                //业务逻辑：加载图片list中的对应图片到前端页面
                int j = clickCount%10;
                Logger.d(j);
//                mPhotoLoader.loadPhoto(ivPhoto,photoUrlList.get(j));
                mPictureLoader.load(ivPhoto,photoUrlList.get(j));
                clickCount++;
                Logger.d(clickCount);
                break;

            case R.id.btn_NextPage:
                mPage++;
                fetchNextPage(mPage,NEXT_STATE);
                mPictureLoader.load(ivPhoto,photoUrlList.get(0));
                clickCount = 0;
        }
    }

    private void fetchNextPage(int page,int state){
        switch (state){
            case INIT_STATE:
                new PhotoAsyncTask(1).execute();
                break;
            case NEXT_STATE:
                new PhotoAsyncTask(page).execute();
                break;
        }
    }


    class PhotoAsyncTask extends AsyncTask<Void,Void,ArrayList<PhotoBean.ResultsBean>>{
        int page;

        public PhotoAsyncTask(int page) {
            this.page = page ;
        }

        @Override
        protected void onPostExecute(ArrayList<PhotoBean.ResultsBean> resultsBeans) {
            super.onPostExecute(resultsBeans);
            Logger.d(resultsBeans);
            photoUrlList.clear();
            for (int i = 0;i<resultsBeans.size();i++){
                Logger.d(resultsBeans.size());
                Logger.d(resultsBeans.get(i).getUrl());
                photoUrlList.add(resultsBeans.get(i).getUrl());
            }

        }

        @Override
        protected ArrayList<PhotoBean.ResultsBean> doInBackground(Void... voids) {
            return mPhotoApi.getPhotos(page);
        }
    }
}
