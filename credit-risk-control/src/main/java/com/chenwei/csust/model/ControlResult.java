package com.chenwei.csust.model;

import io.swagger.annotations.ApiModel;
import org.apache.spark.ml.linalg.DenseVector;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@ApiModel
public class ControlResult implements Serializable {
    private Double label;
    private double[] rawPrediction;
    private double[] probability;
    private Double prediction;

    public ControlResult() {
    }

    public ControlResult(Double label, double[] rawPrediction, double[] probability, Double prediction) {
        this.label = label;
        this.rawPrediction = rawPrediction;
        this.probability = probability;
        this.prediction = prediction;
    }


    public Double getLabel() {
        return label;
    }

    public void setLabel(Double label) {
        this.label = label;
    }

    public double[] getRawPrediction() {
        return rawPrediction;
    }

    public void setRawPrediction(Object object) {
        DenseVector rawPrediction = (DenseVector)object;
        this.rawPrediction = rawPrediction.toArray();
    }

    public double[] getProbability() {
        return probability;
    }

    public void setProbability(Object object) {
        DenseVector probability = (DenseVector)object;
        this.probability = probability.toArray();
    }

    public Double getPrediction() {
        return prediction;
    }

    public void setPrediction(Double prediction) {
        this.prediction = prediction;
    }
}
