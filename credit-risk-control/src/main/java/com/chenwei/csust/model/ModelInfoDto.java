package com.chenwei.csust.model;

import java.util.Date;

public class ModelInfoDto {
    private Integer id;

    private String name;

    private String path;

    private Double accuracy;

    private Double mse;

    private Double mae;

    private Double rmsesquared;

    private String type;

    private Date createdat;

    public ModelInfoDto(Integer id, String name, String path, Double accuracy, Double mse, Double mae, Double rmsesquared, String type, Date createdat) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.accuracy = accuracy;
        this.mse = mse;
        this.mae = mae;
        this.rmsesquared = rmsesquared;
        this.type = type;
        this.createdat = createdat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Double getMse() {
        return mse;
    }

    public void setMse(Double mse) {
        this.mse = mse;
    }

    public Double getMae() {
        return mae;
    }

    public void setMae(Double mae) {
        this.mae = mae;
    }

    public Double getRmsesquared() {
        return rmsesquared;
    }

    public void setRmsesquared(Double rmsesquared) {
        this.rmsesquared = rmsesquared;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }
}