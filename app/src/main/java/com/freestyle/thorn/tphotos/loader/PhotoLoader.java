package com.freestyle.thorn.tphotos.loader;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;


/**
 * Created by Administrator on 2018/4/10 0010.
 * Github: https://github.com/ThornFUN
 * Function:图片加载业务类
 * SomethingElse:自己写了一半，写的有问题，出现错误，最后用的Runoob上面的原生代码
 */

public class PhotoLoader {
    private ImageView mImageView;
    private String mPhotoUrl;
    private byte[] mPhotoBytes;


    public void loadPhoto(ImageView ivPhoto, String photoUrl) {
        mImageView = ivPhoto;
        mPhotoUrl = photoUrl;

        Drawable mImageViewDrawable = mImageView.getDrawable();
        if (mImageViewDrawable != null && mImageViewDrawable instanceof BitmapDrawable) {
            Bitmap mBitmap = ((BitmapDrawable) mImageViewDrawable).getBitmap();
            if (mBitmap != null && mBitmap.isRecycled()) {
                //如果上一个 mBitmap 对象没有被回收，则回收这个对象，防止内存溢出
                mBitmap.recycle();
            }
            //TODO 开始通过URL获取 bitmap 对象，然后将 bitmap 对象和 android 的 imageView 绑定起来
            //TODO 实现方式肯定是通过 handler 来实现咯~~~

            Logger.d(mBitmap);
            Logger.d(mPhotoUrl);

        }
    }
}