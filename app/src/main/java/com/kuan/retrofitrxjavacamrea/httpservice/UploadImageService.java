package com.kuan.retrofitrxjavacamrea.httpservice;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by zhuangwu on 17-5-4.
 */

public interface UploadImageService {
    @POST("/classify_upload_new")
    Observable<String> uploadImage(@PartMap Map<String, MultipartBody.Part> partMap);
}
