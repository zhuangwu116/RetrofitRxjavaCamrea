package com.kuan.retrofitrxjavacamrea.mvp.details;

import com.kuan.retrofitrxjavacamrea.bean.DetailsInfoBean;
import com.kuan.retrofitrxjavacamrea.mvp.BasePresenter;
import com.kuan.retrofitrxjavacamrea.mvp.BaseView;


/**
 * Created by zhuangwu on 17-5-13.
 */

public interface DetailTasksContract {
    interface View extends BaseView<Presenter> {
        public void Success(DetailsInfoBean data);
        public void Error();
    }

    interface Presenter extends BasePresenter {
        public void getDetailInfos(String url,String id);
    }
}
