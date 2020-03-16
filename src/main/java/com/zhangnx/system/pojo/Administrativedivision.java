package com.zhangnx.system.pojo;

public class Administrativedivision {
    private Integer id;
    private String areaCode;
    private String areaName;
    private String areaAddr;
    private Integer parentId;
    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaAddr() {
        return areaAddr;
    }

    public void setAreaAddr(String areaAddr) {
        this.areaAddr = areaAddr;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
