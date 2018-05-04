package com.freestyle.thorn.tphotos.api;

import com.freestyle.thorn.tphotos.bean.PhotoBean;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by pengj on 2018-4-26.
 * Github https://github.com/ThornFUN
 * Function:
 Step 1：通过HttpUrlConnection发起Get请求，然后获得后台返回的数据，此时是流形式的
 Step 2：我们需要写一个流转成字节数组的方法
 Step 3：将字节数组转成字符串后，得到的就是后台的给我们返回的数据了，接着要做的就 是写一个解析这一大串Json的方法了，我们需要获取Json里我们需要的数据，丢到Bean里
 Step 4：返回处理后的集合数据
 */

public class PhotoApi {

    private int count = 10;

    private static final String BASE_URL = "http://gank.io/api/data/福利/";

    Gson gson = new Gson();

    public ArrayList<PhotoBean.ResultsBean> getPhotos(int page){
        ArrayList<PhotoBean.ResultsBean> resultsBeans = new ArrayList<PhotoBean.ResultsBean>();
        ArrayList<PhotoBean> photoBeans = new ArrayList<PhotoBean>();
        String fetchURL = BASE_URL + count + "/" + page;
        try {
            URL mURL = new URL(fetchURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) mURL.openConnection();
            httpURLConnection.setConnectTimeout(2000);
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode == 200){
                com.orhanobut.logger.Logger.d("请求成功");
                InputStream inputStream = httpURLConnection.getInputStream();
                byte[] responseData = readFromStream(inputStream);
                String result = new String(responseData);

                PhotoBean photoBean = gson.fromJson(result,PhotoBean.class);
                resultsBeans = (ArrayList<PhotoBean.ResultsBean>) photoBean.getResults();
//                resultsBeans = photoBeans.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultsBeans;
    }
//
//    private ArrayList<PhotoBean> parseJson(String result) {
//        com.orhanobut.logger.Logger.d(result);
//
//
//        Logger.d(photoBean);
//        Logger.d(photoBean.getResults());
//
//
//
//        return photoBean;
//    }

    private byte[] readFromStream(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) !=  -1){
                byteArrayOutputStream.write(buffer,0,len);
            }
            inputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

}
