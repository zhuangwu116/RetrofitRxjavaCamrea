package com.kuan.retrofitrxjavacamrea.httpservice;



import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhuangwu on 17-5-13.
 */

public interface DetailsImageService {
    @GET("/query")
    public Observable<String> getDetailsImageInfo(@Query("id") String id);
}
