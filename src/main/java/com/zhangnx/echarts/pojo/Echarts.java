package com.zhangnx.echarts.pojo;

public class Echarts {
    private Integer id;
    private String value;
    private String name;

    @Override
    public String toString() {
        return "Echarts{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
