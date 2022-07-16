package com.edu.zut.entity;

public class MyFile {
    private Integer fid;

    private String furl;

    private String fmd5;

    private Integer fsize;

    public MyFile(Integer fid, String furl, String fmd5, Integer fsize) {
        this.fid = fid;
        this.furl = furl;
        this.fmd5 = fmd5;
        this.fsize = fsize;
    }

    public MyFile() {
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl == null ? null : furl.trim();
    }

    public String getFmd5() {
        return fmd5;
    }

    public void setFmd5(String fmd5) {
        this.fmd5 = fmd5 == null ? null : fmd5.trim();
    }

    public Integer getFsize() {
        return fsize;
    }

    public void setFsize(Integer fsize) {
        this.fsize = fsize;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "fid=" + fid +
                ", furl='" + furl + '\'' +
                ", fmd5='" + fmd5 + '\'' +
                ", fsize=" + fsize +
                '}';
    }
}