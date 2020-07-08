package com.atguigu.stat;

public class DataEntity {

    private Double value;
    private String name;

    public DataEntity() {
    }

    public DataEntity(Double value, String name) {
        this.value = value;
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataEntity{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
