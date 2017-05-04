package com.kuan.retrofitrxjavacamrea.httpservice;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by zhuangwu on 17-5-4.
 */

public interface UploadImageService {
    @Multipart
    @POST
    Observable<String> uploadImage(@PartMap Map<String, MultipartBody.Part> partMap);
}
