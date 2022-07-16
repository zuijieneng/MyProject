package com.edu.zut.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class CarIn {
    private String carInId;

    private String carOutId;

    private String carCheckId;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date carInTime;

    private Float carInOil;

    private BigDecimal carInMoney;

    private String carInFeedback;

    private String userId;

    private String carId;


    public CarIn(String carInId, String carOutId, String carCheckId, Date carInTime, Float carInOil, BigDecimal carInMoney, String carInFeedback, String userId, String carId) {
        this.carInId = carInId;
        this.carOutId = carOutId;
        this.carCheckId = carCheckId;
        this.carInTime = carInTime;
        this.carInOil = carInOil;
        this.carInMoney = carInMoney;
        this.carInFeedback = carInFeedback;
        this.userId = userId;
        this.carId = carId;
    }

    public String getCarInId() {
        return carInId;
    }

    public void setCarInId(String carInId) {
        this.carInId = carInId == null ? null : carInId.trim();
    }

    public String getCarOutId() {
        return carOutId;
    }

    public void setCarOutId(String carOutId) {
        this.carOutId = carOutId == null ? null : carOutId.trim();
    }

    public String getCarCheckId() {
        return carCheckId;
    }

    public void setCarCheckId(String carCheckId) {
        this.carCheckId = carCheckId == null ? null : carCheckId.trim();
    }

    public Date getCarInTime() {
        return carInTime;
    }

    public void setCarInTime(Date carInTime) {
        this.carInTime = carInTime;
    }

    public Float getCarInOil() {
        return carInOil;
    }

    public void setCarInOil(Float carInOil) {
        this.carInOil = carInOil;
    }

    public BigDecimal getCarInMoney() {
        return carInMoney;
    }

    public void setCarInMoney(BigDecimal carInMoney) {
        this.carInMoney = carInMoney;
    }

    public String getCarInFeedback() {
        return carInFeedback;
    }

    public void setCarInFeedback(String carInFeedback) {
        this.carInFeedback = carInFeedback == null ? null : carInFeedback.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId == null ? null : carId.trim();
    }

}