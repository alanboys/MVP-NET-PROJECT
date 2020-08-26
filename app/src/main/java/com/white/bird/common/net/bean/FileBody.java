package com.white.bird.common.net.bean;

import java.util.List;

/**
 * author : ZYK
 * createTime   : 2020/7/16 10:57
 * function   :
 */

public class FileBody {
    private String date;
    private List<String> responsePath;

    public void setDate (String date) {
        this.date = date;
    }

    public void setResponsePath (List<String> responsePath) {
        this.responsePath = responsePath;
    }

    public String getDate () {
        return date;
    }

    public List<String> getResponsePath () {
        return responsePath;
    }
}
