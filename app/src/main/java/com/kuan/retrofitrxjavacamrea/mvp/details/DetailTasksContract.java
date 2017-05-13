package com.kuan.retrofitrxjavacamrea.mvp.details;

import com.kuan.retrofitrxjavacamrea.bean.VisionDetRet;
import com.kuan.retrofitrxjavacamrea.mvp.BasePresenter;
import com.kuan.retrofitrxjavacamrea.mvp.BaseView;
import com.kuan.retrofitrxjavacamrea.mvp.uploadtasks.UploadTasksContract;

/**
 * Created by zhuangwu on 17-5-13.
 */

public interface DetailTasksContract {
    interface View extends BaseView<UploadTasksContract.Presenter> {
        public void Success(String data);
        public void Error();
    }

    interface Presenter extends BasePresenter {
        public void getDetailInfos(String url,String id);
    }
}
