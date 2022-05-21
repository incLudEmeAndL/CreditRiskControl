package com.chenwei.csust.spark.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HDFSConfig {
    @Value("${hadoop.hdfs.name}")
    private String hdfsName;

    @Value("${hadoop.hdfs.value}")
    private String hdfsValue;

    @Value("${hadoop.hdfs.path}")
    private String path;

    @Value("${hadoop.hdfs.model1}")
    private String model1;

    @Value("${hadoop.hdfs.model2}")
    private String model2;

    public String getHdfsName() {
        return hdfsName;
    }

    public void setHdfsName(String hdfsName) {
        this.hdfsName = hdfsName;
    }

    public String getHdfsValue() {
        return hdfsValue;
    }

    public void setHdfsValue(String hdfsValue) {
        this.hdfsValue = hdfsValue;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getModel1() {
        return model1;
    }

    public void setModel1(String model1) {
        this.model1 = model1;
    }

    public String getModel2() {
        return model2;
    }

    public void setModel2(String model2) {
        this.model2 = model2;
    }
}
