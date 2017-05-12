package com.kuan.retrofitrxjavacamrea.bean;

import java.util.List;

/**
 * Created by zhuangwu on 2017/2/16.
 */

public  class VisionDetRet {

    /**
     * arrayList : [{"confidence":0.3308,"id":0,"label":"desktop computer"},{"confidence":0.2486,"id":1,"label":"notebook"},{"confidence":0.1296,"id":2,"label":"laptop"},{"confidence":0.0889,"id":3,"label":"computer keyboard"},{"confidence":0.0479,"id":4,"label":"space bar"}]
     * errorMsg : success
     * time : 0.673
     */

    private String errorMsg;
    private String time;
    private List<ArrayListBean> arrayList;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<ArrayListBean> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<ArrayListBean> arrayList) {
        this.arrayList = arrayList;
    }

    public static class ArrayListBean {
        /**
         * confidence : 0.3308
         * id : 0
         * label : desktop computer
         */

        private double confidence;
        private String id;
        private String label;

        public double getConfidence() {
            return confidence;
        }

        public void setConfidence(double confidence) {
            this.confidence = confidence;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
