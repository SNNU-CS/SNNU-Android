package io.github.zhaoqi99.snnu_android.Model;

import java.util.Date;
import java.util.List;

public class BorrowInfoModel {
    private String msg;
    private List<Result> result;
    private int status;
    private boolean success;

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
    public List<Result> getResult() {
        return result;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean getSuccess() {
        return success;
    }
    public class Result {

        private String 书名;
        private Date 保留结束日期;
        private String 单册分馆;
        private String 取书地点;
        private String 著者;
        private String 预约者;
        public void set书名(String 书名) {
            this.书名 = 书名;
        }
        public String get书名() {
            return 书名;
        }

        public void set保留结束日期(Date 保留结束日期) {
            this.保留结束日期 = 保留结束日期;
        }
        public Date get保留结束日期() {
            return 保留结束日期;
        }

        public void set单册分馆(String 单册分馆) {
            this.单册分馆 = 单册分馆;
        }
        public String get单册分馆() {
            return 单册分馆;
        }

        public void set取书地点(String 取书地点) {
            this.取书地点 = 取书地点;
        }
        public String get取书地点() {
            return 取书地点;
        }

        public void set著者(String 著者) {
            this.著者 = 著者;
        }
        public String get著者() {
            return 著者;
        }

        public void set预约者(String 预约者) {
            this.预约者 = 预约者;
        }
        public String get预约者() {
            return 预约者;
        }

    }
}
