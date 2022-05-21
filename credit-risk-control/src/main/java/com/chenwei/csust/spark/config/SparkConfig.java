package com.chenwei.csust.spark.config;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.context.annotation.Configuration
public class SparkConfig {
    @Autowired
    HDFSConfig hdfsConfig;

    private SparkConf sparkConf = new SparkConf().setMaster("local[4]").setAppName("GetModelFromHDFS");

    private JavaStreamingContext javaStreamingContext = new JavaStreamingContext(getSparkConf(), Durations.seconds(100));

    private SparkContext context = JavaSparkContext.toSparkContext(getJavaStreamingContext().sparkContext());

    private Configuration configuration = context.hadoopConfiguration();

    private SparkSession sparkSession = SparkSession.builder().config(getSparkConf()).getOrCreate();;

    public SparkConf getSparkConf() {
        return sparkConf;
    }

    public JavaStreamingContext getJavaStreamingContext() {
        return javaStreamingContext;
    }

    public SparkContext getContext() {
        return context;
    }

    public Configuration getHadoopConfiguration() {
        configuration.set(hdfsConfig.getHdfsName(), hdfsConfig.getHdfsValue());
        return configuration;
    }

    public SparkSession getSparkSession() {
        return sparkSession;
    }
}
