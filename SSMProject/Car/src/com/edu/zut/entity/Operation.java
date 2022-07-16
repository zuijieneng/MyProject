package com.edu.zut.entity;

import java.util.List;

public class Operation {
    private String urlId;

    private String urlPath;

    private String urlName;

    private List<User> list; //多对多的关系

    public Operation(String urlId, String urlPath, String urlName) {
        this.urlId = urlId;
        this.urlPath = urlPath;
        this.urlName = urlName;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId == null ? null : urlId.trim();
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath == null ? null : urlPath.trim();
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName == null ? null : urlName.trim();
    }


}