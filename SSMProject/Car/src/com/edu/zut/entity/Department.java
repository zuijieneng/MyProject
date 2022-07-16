package com.edu.zut.entity;

public class Department {
    private String departmentId;

    private String departmentName;

    private String departmentDescribe;

    public Department(String departmentId, String departmentName, String departmentDescribe) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentDescribe = departmentDescribe;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getDepartmentDescribe() {
        return departmentDescribe;
    }

    public void setDepartmentDescribe(String departmentDescribe) {
        this.departmentDescribe = departmentDescribe == null ? null : departmentDescribe.trim();
    }
}