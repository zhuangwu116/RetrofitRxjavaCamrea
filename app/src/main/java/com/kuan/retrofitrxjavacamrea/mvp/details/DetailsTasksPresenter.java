package com.kuan.retrofitrxjavacamrea.mvp.details;

import android.support.annotation.NonNull;

import com.kuan.retrofitrxjavacamrea.bean.DetailsInfoBean;
import com.kuan.retrofitrxjavacamrea.httpservice.DetailsImageService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhuangwu on 17-5-13.
 */

public class DetailsTasksPresenter implements DetailTasksContract.Presenter {
    private DetailTasksContract.View mvpView;
    public DetailsTasksPresenter(@NonNull DetailTasksContract.View view){
        if(view!=null){
            this.mvpView=view;
            this.mvpView.setPresenter(this);
        }
    }

    @Override
    public void getDetailInfos(String url, String id) {
        DetailsImageService service=RxjavaRetfit(url).create(DetailsImageService.class);
        service.getDetailsImageInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailsInfoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        mvpView.Error();
                    }

                    @Override
                    public void onNext(DetailsInfoBean s) {
                        mvpView.Success(s);
                    }
                });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
    public Retrofit RxjavaRetfit(String url){
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
