package com.edu.zut.entity;

import com.edu.zut.exception.CustomerException;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Customer {
    private String customerId;

    private String customerName;

    private String customerSex;

    private String customerPhone;

    private String customerPid;

    private String customerJob;

    private String customerAddress;

    private String customerPhoto;

    private Integer customerStatus;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date customerTime;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        if(customerName.equals("")||customerName==null){throw new CustomerException("用户名不能为空！");
        }
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex == null ? null : customerSex.trim();
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
//        if(!customerPhone.matches("1[0-9]{10}")){throw new CustomerException("电话号码错误！");}
        this.customerPhone = customerPhone == null ? null : customerPhone.trim();
    }

    public String getCustomerPid() {
        return customerPid;
    }

    public void setCustomerPid(String customerPid) {
        this.customerPid = customerPid == null ? null : customerPid.trim();
    }

    public String getCustomerJob() {
        return customerJob;
    }

    public void setCustomerJob(String customerJob) {
        this.customerJob = customerJob == null ? null : customerJob.trim();
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress == null ? null : customerAddress.trim();
    }

    public String getCustomerPhoto() {
        return customerPhoto;
    }

    public void setCustomerPhoto(String customerPhoto) {
        this.customerPhoto = customerPhoto == null ? null : customerPhoto.trim();
    }

    public Integer getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(Integer customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Date getCustomerTime() {
        return customerTime;
    }

    public void setCustomerTime(Date customerTime) {
        this.customerTime = customerTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerSex='" + customerSex + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerPid='" + customerPid + '\'' +
                ", customerJob='" + customerJob + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerPhoto='" + customerPhoto + '\'' +
                ", customerStatus=" + customerStatus +
                ", customerTime=" + customerTime +
                '}';
    }
}