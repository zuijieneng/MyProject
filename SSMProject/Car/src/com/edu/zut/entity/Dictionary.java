package com.edu.zut.entity;

public class Dictionary {
    private Integer dictionaryId;

    private String dictionaryName;

    private Integer dictionaryType;

    private Integer provinceId;

    public Integer getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName == null ? null : dictionaryName.trim();
    }

    public Integer getDictionaryType() {
        return dictionaryType;
    }

    public void setDictionaryType(Integer dictionaryType) {
        this.dictionaryType = dictionaryType;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}