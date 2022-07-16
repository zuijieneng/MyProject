package com.edu.zut.entity;

public class User {
    private Integer uid;

    private String uname;

    private String usex;

    private String upwd;

    private String urealName;

    private Integer uscore;

    private Integer usize;

    private Integer ustatus;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex == null ? null : usex.trim();
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd == null ? null : upwd.trim();
    }

    public String getUrealName() {
        return urealName;
    }

    public void setUrealName(String urealName) {
        this.urealName = urealName == null ? null : urealName.trim();
    }

    public Integer getUscore() {
        return uscore;
    }

    public void setUscore(Integer uscore) {
        this.uscore = uscore;
    }

    public Integer getUsize() {
        return usize;
    }

    public void setUsize(Integer usize) {
        this.usize = usize;
    }

    public Integer getUstatus() {
        return ustatus;
    }

    public void setUstatus(Integer ustatus) {
        this.ustatus = ustatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", usex='" + usex + '\'' +
                ", upwd='" + upwd + '\'' +
                ", urealName='" + urealName + '\'' +
                ", uscore=" + uscore +
                ", usize=" + usize +
                ", ustatus=" + ustatus +
                '}';
    }
}