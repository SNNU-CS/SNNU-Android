package io.github.zhaoqi99.snnu_android;

import java.util.List;

public class GradeModel {
    String msg;
    Integer status;

    public List<Data> getData() {
        return data;
    }

    List<Data> data;

    public  class Data{
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
