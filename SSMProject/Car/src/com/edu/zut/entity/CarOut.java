package com.edu.zut.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class CarOut {
    private String carOutId;

    private String carId;

    private String customerId;

    private String carCheckId;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date carOutStartTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date carOutEndTime;

    private BigDecimal carOutDayPrice;

    private BigDecimal carOutDeposit;

    private Float carOutOil;

    private String userId;

    public String getCarOutId() {
        return carOutId;
    }

    public void setCarOutId(String carOutId) {
        this.carOutId = carOutId == null ? null : carOutId.trim();
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId == null ? null : carId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getCarCheckId() {
        return carCheckId;
    }

    public void setCarCheckId(String carCheckId) {
        this.carCheckId = carCheckId == null ? null : carCheckId.trim();
    }

    public Date getCarOutStartTime() {
        return carOutStartTime;
    }

    public void setCarOutStartTime(Date carOutStartTime) {
        this.carOutStartTime = carOutStartTime;
    }

    public Date getCarOutEndTime() {
        return carOutEndTime;
    }

    public void setCarOutEndTime(Date carOutEndTime) {
        this.carOutEndTime = carOutEndTime;
    }

    public BigDecimal getCarOutDayPrice() {
        return carOutDayPrice;
    }

    public void setCarOutDayPrice(BigDecimal carOutDayPrice) {
        this.carOutDayPrice = carOutDayPrice;
    }

    public BigDecimal getCarOutDeposit() {
        return carOutDeposit;
    }

    public void setCarOutDeposit(BigDecimal carOutDeposit) {
        this.carOutDeposit = carOutDeposit;
    }

    public Float getCarOutOil() {
        return carOutOil;
    }

    public void setCarOutOil(Float carOutOil) {
        this.carOutOil = carOutOil;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}