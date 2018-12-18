package io.github.zhaoqi99.snnu_android;

import java.util.List;

public class LoginResult {
    public String getMsg() {
        return msg;
    }

    public Integer getStatus() {
        return status;
    }

    private String msg;
    private Integer status;
    private List<String> data;
}
