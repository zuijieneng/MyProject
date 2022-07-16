package com.edu.zut.entity;

import java.util.Date;

public class Directory {
    private Integer did;

    private String dname;

    private Integer dtype;

    private Date dtime;

    private Integer uid;

    private String dfu;

    private Integer dfid;

    private Integer dsize;

    private Integer dprivate;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }

    public Integer getDtype() {
        return dtype;
    }

    public void setDtype(Integer dtype) {
        this.dtype = dtype;
    }

    public Date getDtime() {
        return dtime;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getDfu() {
        return dfu;
    }

    public void setDfu(String dfu) {
        this.dfu = dfu == null ? null : dfu.trim();
    }

    public Integer getDfid() {
        return dfid;
    }

    public void setDfid(Integer dfid) {
        this.dfid = dfid;
    }

    public Integer getDsize() {
        return dsize;
    }

    public void setDsize(Integer dsize) {
        this.dsize = dsize;
    }

    public Integer getDprivate() {
        return dprivate;
    }

    public void setDprivate(Integer dprivate) {
        this.dprivate = dprivate;
    }

    @Override
    public String toString() {
        return "Directory{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                ", dtype=" + dtype +
                ", dtime=" + dtime +
                ", uid=" + uid +
                ", dfu='" + dfu + '\'' +
                ", dfid=" + dfid +
                ", dsize=" + dsize +
                ", dprivate=" + dprivate +
                '}';
    }
}