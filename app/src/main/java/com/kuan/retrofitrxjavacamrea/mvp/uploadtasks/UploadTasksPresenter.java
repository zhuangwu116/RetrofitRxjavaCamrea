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

import com.kuan.retrofitrxjavacamrea.httpservice.UploadImageService;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observer;
import rx.Subscriber;
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
    public void uploadImage(String url, List<String> paths) {
        int i=0;
        Map<String,MultipartBody.Part> mapFile=new HashMap<>();
        for(String path:paths){
            File file=new File(path);
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("files", file.getName(), requestFile);
            mapFile.put(String.valueOf(i),filePart);
            i++;
        }
        UploadImageService service=RxjavaRetfit(url).create(UploadImageService.class);
       service.uploadImage(mapFile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Subscriber<String>() {
                   @Override
                   public void onCompleted() {

                   }

                   @Override
                   public void onError(Throwable throwable) {

                   }

                   @Override
                   public void onNext(String s) {

                   }
               });
    }
    public Retrofit RxjavaRetfit(String url){
              return new Retrofit.Builder()
                      .baseUrl(url)
                      .addConverterFactory(ScalarsConverterFactory.create())
                      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                      .build();
    }
}
