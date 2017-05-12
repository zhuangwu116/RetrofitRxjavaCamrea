/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kuan.retrofitrxjavacamrea.mvp.uploadtasks;

import android.support.annotation.NonNull;
import android.util.Log;


import com.kuan.retrofitrxjavacamrea.bean.VisionDetRet;
import com.kuan.retrofitrxjavacamrea.httpservice.UploadImageService;

import java.io.File;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class UploadTasksPresenter implements UploadTasksContract.Presenter {
    private  UploadTasksContract.View mTasksView;
    public UploadTasksPresenter(@NonNull UploadTasksContract.View tasksView) {
        if(tasksView!=null){
            this.mTasksView=tasksView;
            mTasksView.setPresenter(this);
        }
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void uploadImage(String url, String path) {
        File file=new File(path);
        MultipartBody.Builder requestBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        requestBuilder.addFormDataPart("imagefile",file.getName(),RequestBody.create(MediaType.parse("image/*"), file));
        UploadImageService service=RxjavaRetfit(url).create(UploadImageService.class);
        Log.i("uploadImage","uploadImage");
        service.uploadImage(requestBuilder.build())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<VisionDetRet>() {
                   @Override
                   public void onCompleted() {

                   }

                   @Override
                   public void onError(Throwable throwable) {
                       mTasksView.Error();
                   }

                   @Override
                   public void onNext(VisionDetRet s) {
                       mTasksView.Success(s);
                   }
               });
    }
    public Retrofit RxjavaRetfit(String url){
              return new Retrofit.Builder()
                      .baseUrl(url)
                      .addConverterFactory(GsonConverterFactory.create())
                      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                      .build();
    }
}
