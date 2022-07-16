package com.edu.zut.entity;

public class RoleUrl {
    private String roleId;

    private String urlId;

    public RoleUrl(String roleId, String urlId) {
        this.roleId = roleId;
        this.urlId = urlId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId == null ? null : urlId.trim();
    }
}