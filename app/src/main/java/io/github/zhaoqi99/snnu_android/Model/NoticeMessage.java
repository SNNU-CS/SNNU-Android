package io.github.zhaoqi99.snnu_android;

import java.io.Serializable;

public class NoticeMessage implements Serializable {
    private String Title;
    private String Date;
    private String Department;
    private String Type;
    private String Link;

    public NoticeMessage(String Title, String Date, String Link, String Type, String Department) {
        this.Date = Date;
        this.Title = Title;
        this.Link = Link;
        this.Type = Type;
        this.Department = Department;
    }

    public String GetTitle() {
        return this.Title;
    }

    public String GetLink() {
        return this.Link;
    }

    public String GetType() {
        return this.Type;
    }

    public String GetDate() {
        return this.Date;
    }

    public String GetDepartment() {
        return this.Department;
    }
}
