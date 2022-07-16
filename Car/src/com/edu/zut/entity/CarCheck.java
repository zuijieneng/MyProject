package com.edu.zut.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CarCheck {
    private String carCheckId;

    private String carId;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date carCheckTime;

    private String carDescribe;

    private String userId;

    private Integer carCheckStatus;

    public String getCarCheckId() {
        return carCheckId;
    }

    public void setCarCheckId(String carCheckId) {
        this.carCheckId = carCheckId == null ? null : carCheckId.trim();
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId == null ? null : carId.trim();
    }

    public Date getCarCheckTime() {
        return carCheckTime;
    }

    public void setCarCheckTime(Date carCheckTime) {
        this.carCheckTime = carCheckTime;
    }

    public String getCarDescribe() {
        return carDescribe;
    }

    public void setCarDescribe(String carDescribe) {
        this.carDescribe = carDescribe == null ? null : carDescribe.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getCarCheckStatus() {
        return carCheckStatus;
    }

    public void setCarCheckStatus(Integer carCheckStatus) {
        this.carCheckStatus = carCheckStatus;
    }

    public CarCheck(String carCheckId, String carId, Date carCheckTime, String carDescribe, String userId, Integer carCheckStatus) {
        this.carCheckId = carCheckId;
        this.carId = carId;
        this.carCheckTime = carCheckTime;
        this.carDescribe = carDescribe;
        this.userId = userId;
        this.carCheckStatus = carCheckStatus;
    }
}