package com.kuan.retrofitrxjavacamrea.httpservice;



import com.kuan.retrofitrxjavacamrea.bean.DetailsInfoBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhuangwu on 17-5-13.
 */

public interface DetailsImageService {
    @GET("/query")
    public Observable<DetailsInfoBean> getDetailsImageInfo(@Query("id") String id);
}
