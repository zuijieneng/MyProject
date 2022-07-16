package com.edu.zut.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Car {
    private String carId;

    private String carLogo;

    private String carNumber;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date carTime;

    private Integer carZuo;

    private String carColor;

    private BigDecimal carPrice;

    private String carPhoto;

    private Integer carStatus;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId == null ? null : carId.trim();
    }

    public String getCarLogo() {
        return carLogo;
    }

    public void setCarLogo(String carLogo) {
        this.carLogo = carLogo == null ? null : carLogo.trim();
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public Date getCarTime() {
        return carTime;
    }

    public void setCarTime(Date carTime) {
        this.carTime = carTime;
    }

    public Integer getCarZuo() {
        return carZuo;
    }

    public void setCarZuo(Integer carZuo) {
        this.carZuo = carZuo;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor == null ? null : carColor.trim();
    }

    public BigDecimal getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(BigDecimal carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarPhoto() {
        return carPhoto;
    }

    public void setCarPhoto(String carPhoto) {
        this.carPhoto = carPhoto == null ? null : carPhoto.trim();
    }

    public Integer getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId='" + carId + '\'' +
                ", carLogo='" + carLogo + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", carTime=" + carTime +
                ", carZuo=" + carZuo +
                ", carColor='" + carColor + '\'' +
                ", carPrice=" + carPrice +
                ", carPhoto='" + carPhoto + '\'' +
                ", carStatus=" + carStatus +
                '}';
    }
}