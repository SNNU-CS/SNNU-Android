package io.github.zhaoqi99.snnu_android.Model;

import java.util.List;

public class GradeModel {
    public String getMsg() {
        return msg;
    }

    public Integer getStatus() {
        return status;
    }

    String msg;
    Integer status;

    public List<Data> getData() {
        return data;
    }

    List<Data> data;

    public  class Data{
        public String get名次() {
            return 名次;
        }

        public String get学分() {
            return 学分;
        }

        public String get成绩() {
            return 成绩;
        }

        public String get未通过原因() {
            return 未通过原因;
        }

        public String get英文课程名() {
            return 英文课程名;
        }

        public String get课堂平均分() {
            return 课堂平均分;
        }

        public String get课堂最低分() {
            return 课堂最低分;
        }

        public String get课堂最高分() {
            return 课堂最高分;
        }

        public String get课序号() {
            return 课序号;
        }

        public String get课程号() {
            return 课程号;
        }

        public String get课程名() {
            return 课程名;
        }

        public String get课程属性() {
            return 课程属性;
        }

        String 名次;
        String 学分;
        String 成绩;
        String 未通过原因;
        String 英文课程名;
        String 课堂平均分;
        String 课堂最低分;
        String 课堂最高分;
        String 课序号;
        String 课程号;
        String 课程名;
        String 课程属性;

    }
}
