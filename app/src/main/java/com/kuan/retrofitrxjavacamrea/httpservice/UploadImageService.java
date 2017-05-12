package com.kuan.retrofitrxjavacamrea.httpservice;

import com.kuan.retrofitrxjavacamrea.bean.VisionDetRet;
import okhttp3.RequestBody;
import retrofit2.http.Body;

import retrofit2.http.POST;

import rx.Observable;

/**
 * Created by zhuangwu on 17-5-4.
 */

public interface UploadImageService {
    @POST("/classify_upload_new")
    Observable<VisionDetRet> uploadImage(@Body RequestBody body);
}
